package dev.vrba.discord.extensions

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified Context : Any> Context.deriveLogger(): Logger = LoggerFactory.getLogger(Context::class.java)
