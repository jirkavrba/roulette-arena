package dev.vrba.discord.commands

import dev.kord.common.entity.ButtonStyle
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.asChannelOf
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.behavior.interaction.updateEphemeralMessage
import dev.kord.core.entity.channel.TextChannel
import dev.kord.core.entity.interaction.ButtonInteraction
import dev.kord.core.event.interaction.ButtonInteractionCreateEvent
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.rest.builder.message.actionRow
import dev.vrba.discord.domain.Bet
import dev.vrba.discord.domain.BlackNumberBet
import dev.vrba.discord.domain.ColumnBet
import dev.vrba.discord.domain.DOUBLE_ZERO
import dev.vrba.discord.domain.DoubleZeroBet
import dev.vrba.discord.domain.DozenBet
import dev.vrba.discord.domain.EvenNumberBet
import dev.vrba.discord.domain.HighNumberBet
import dev.vrba.discord.domain.LowNumberBet
import dev.vrba.discord.domain.OddNumberBet
import dev.vrba.discord.domain.RedNumberBet
import dev.vrba.discord.domain.SingleNumberBet
import dev.vrba.discord.domain.ZERO
import dev.vrba.discord.domain.ZeroBet
import dev.vrba.discord.domain.takeRandomNumber
import dev.vrba.discord.extensions.deriveLogger
import dev.vrba.discord.ui.button
import dev.vrba.discord.ui.columnBetsButtons
import dev.vrba.discord.ui.columnBetsEmbed
import dev.vrba.discord.ui.dozenBetsButtons
import dev.vrba.discord.ui.dozenBetsEmbed
import dev.vrba.discord.ui.lowHighBetsButtons
import dev.vrba.discord.ui.lowHighBetsEmbed
import dev.vrba.discord.ui.numberBetsButton
import dev.vrba.discord.ui.numberBetsEmbed
import dev.vrba.discord.ui.numberDozenBetsButton
import dev.vrba.discord.ui.numberDozenBetsEmbed
import dev.vrba.discord.ui.numberSelectionBetsButtons
import dev.vrba.discord.ui.numberSelectionBetsEmbed
import dev.vrba.discord.ui.oddEvenBetsButtons
import dev.vrba.discord.ui.oddEvenBetsEmbed
import dev.vrba.discord.ui.rootBetButtons
import dev.vrba.discord.ui.rootBetEmbed
import dev.vrba.discord.ui.spinResultEmbed
import jakarta.inject.Singleton

private const val COMMAND_NAME = "spin"

@Singleton
class SpinCommand : Command {
    private val logger = deriveLogger()

    override suspend fun register(client: Kord) {
        logger.info("Registering the /$COMMAND_NAME command")

        client.createGlobalChatInputCommand(COMMAND_NAME, "Place your bet and spin the wheel!")

        registerSlashCommandHandler(client)
        registerButtonInteractionHandler(client)
    }

    private suspend fun registerSlashCommandHandler(client: Kord) {
        client.on<ChatInputCommandInteractionCreateEvent> {
            if (interaction.invokedCommandName != COMMAND_NAME) return@on

            interaction.respondEphemeral {
                rootBetEmbed()
                rootBetButtons()
            }
        }
    }

    private suspend fun registerButtonInteractionHandler(client: Kord) {
        client.on<ButtonInteractionCreateEvent> {
            val id = interaction.componentId

            when {
                id == "ui:spin-again" -> showRootBetMenu(interaction)
                id == "bet:dozen" -> showDozenBetMenu(interaction)
                id == "bet:column" -> showColumnBetMenu(interaction)
                id == "bet:low-high" -> showLowHighBetMenu(interaction)
                id == "bet:odd-even" -> showOddEvenBetMenu(interaction)
                id == "bet:numbers" -> showNumberBetMenu(interaction)
                id == "bet:numbers:selection" -> showNumberDozenBetMenu(interaction)

                id.startsWith("bet:numbers:dozen:") -> {
                    val base = id.removePrefix("bet:numbers:dozen:").toInt()
                    val numbers = DozenBet(base).numbers.sorted()

                    showNumberSelectionBetMenu(interaction, numbers)
                }

                id == "bet:odd" -> performSpin(interaction, OddNumberBet)
                id == "bet:even" -> performSpin(interaction, EvenNumberBet)
                id == "bet:low" -> performSpin(interaction, LowNumberBet)
                id == "bet:high" -> performSpin(interaction, HighNumberBet)
                id == "bet:red" -> performSpin(interaction, RedNumberBet)
                id == "bet:black" -> performSpin(interaction, BlackNumberBet)

                id.startsWith("bet:dozen:") -> performSpin(interaction, DozenBet(id.removePrefix("bet:dozen:").toInt()))
                id.startsWith("bet:column:") -> performSpin(interaction, ColumnBet(id.removePrefix("bet:column:").toInt()))

                // These two cases need to be handled specially
                id == "bet:number:$ZERO" -> performSpin(interaction, ZeroBet)
                id == "bet:number:$DOUBLE_ZERO" -> performSpin(interaction, DoubleZeroBet)
                id.startsWith("bet:number:") -> performSpin(interaction, SingleNumberBet(id.removePrefix("bet:number:").toInt()))
            }
        }
    }

    private suspend fun showRootBetMenu(interaction: ButtonInteraction) {
        interaction.respondEphemeral {
            rootBetEmbed()
            rootBetButtons()
        }
    }

    private suspend fun showDozenBetMenu(interaction: ButtonInteraction) {
        interaction.updateEphemeralMessage {
            dozenBetsEmbed()
            dozenBetsButtons()
        }
    }

    private suspend fun showColumnBetMenu(interaction: ButtonInteraction) {
        interaction.updateEphemeralMessage {
            columnBetsEmbed()
            columnBetsButtons()
        }
    }

    private suspend fun showLowHighBetMenu(interaction: ButtonInteraction) {
        interaction.updateEphemeralMessage {
            lowHighBetsEmbed()
            lowHighBetsButtons()
        }
    }

    private suspend fun showOddEvenBetMenu(interaction: ButtonInteraction) {
        interaction.updateEphemeralMessage {
            oddEvenBetsEmbed()
            oddEvenBetsButtons()
        }
    }

    private suspend fun showNumberBetMenu(interaction: ButtonInteraction) {
        interaction.updateEphemeralMessage {
            numberBetsEmbed()
            numberBetsButton()
        }
    }

    private suspend fun showNumberDozenBetMenu(interaction: ButtonInteraction) {
        interaction.updateEphemeralMessage {
            numberDozenBetsEmbed()
            numberDozenBetsButton()
        }
    }

    private suspend fun showNumberSelectionBetMenu(
        interaction: ButtonInteraction,
        numbers: List<Int>,
    ) {
        interaction.updateEphemeralMessage {
            numberSelectionBetsEmbed()
            numberSelectionBetsButtons(numbers)
        }
    }

    private suspend fun performSpin(
        interaction: ButtonInteraction,
        bet: Bet,
    ) {
        val number = takeRandomNumber()
        val won = number in bet.numbers

        interaction.updateEphemeralMessage {
            embeds = mutableListOf()
            components = mutableListOf()
            content = "Feel free to dismiss this message"
        }

        interaction.channel.asChannelOf<TextChannel>().createMessage {
            spinResultEmbed(interaction, bet, number, won)
            actionRow {
                button("ui:spin-again", "Spin Again!", ButtonStyle.Primary)
            }
        }
    }
}
