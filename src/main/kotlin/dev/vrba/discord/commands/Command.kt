package dev.vrba.discord.commands

import dev.kord.core.Kord

interface Command {
    suspend fun register(client: Kord)
}
