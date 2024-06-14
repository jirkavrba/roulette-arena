package dev.vrba.discord

import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import dev.vrba.discord.events.ReadyEventHandler
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class DiscordBot(
    private val readyEventHandler: ReadyEventHandler,
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.qualifiedName)

    fun run(token: String) {
        runBlocking {
            logger.info("Starting discord bot")

            val client = Kord(token)

            client.on<ReadyEvent> { readyEventHandler.handle(this) }
            client.login()
        }
    }
}
