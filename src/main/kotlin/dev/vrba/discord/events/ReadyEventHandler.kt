package dev.vrba.discord.events

import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.vrba.discord.commands.Command
import dev.vrba.discord.extensions.deriveLogger
import jakarta.inject.Singleton

@Singleton
class ReadyEventHandler(
    private val commands: List<Command>,
) {
    private val logger = deriveLogger()

    suspend fun handle(event: ReadyEvent) {
        val client = event.kord

        client.updateBotPresence()
        client.registerSlashCommands(commands)
    }

    private suspend fun Kord.updateBotPresence() {
        logger.info("Updating bot presence")

        val hand = dev.kord.x.emoji.Emojis.callMe.unicode
        val sunglasses = dev.kord.x.emoji.Emojis.sunglasses.unicode

        editPresence { playing("roulette $sunglasses $hand") }
    }

    private suspend fun Kord.registerSlashCommands(commands: List<Command>) {
        logger.info("Registering slash commands")

        commands.forEach { it.register(this) }
    }
}
