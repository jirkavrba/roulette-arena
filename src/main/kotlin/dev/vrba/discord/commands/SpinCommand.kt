package dev.vrba.discord.commands

import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.vrba.discord.extensions.deriveLogger
import dev.vrba.discord.ui.rootBetButtons
import dev.vrba.discord.ui.rootBetEmbed
import jakarta.inject.Singleton

private const val COMMAND_NAME = "spin"

@Singleton
class SpinCommand : Command {
    private val logger = deriveLogger()

    override suspend fun register(client: Kord) {
        logger.info("Registering the /$COMMAND_NAME command")

        client.createGlobalChatInputCommand(COMMAND_NAME, "Place your bet and spin the wheel!")
        client.on<ChatInputCommandInteractionCreateEvent> {
            if (interaction.invokedCommandName != COMMAND_NAME) return@on

            interaction.respondEphemeral {
                rootBetEmbed()
                rootBetButtons()
            }
        }
    }
}
