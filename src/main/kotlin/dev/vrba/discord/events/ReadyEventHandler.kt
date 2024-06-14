package dev.vrba.discord.events

import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.x.emoji.Emojis
import jakarta.inject.Singleton

@Singleton
class ReadyEventHandler {
    suspend fun handle(event: ReadyEvent) {
        val hand = Emojis.callMe.unicode
        val sunglasses = Emojis.sunglasses.unicode

        event.kord.editPresence { playing("roulette $sunglasses $hand") }
    }
}
