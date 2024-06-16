package dev.vrba.discord.domain

import dev.kord.x.emoji.Emojis
import java.security.SecureRandom
import kotlin.random.asKotlinRandom
import kotlin.text.Typography.nbsp

const val ZERO = 0
const val DOUBLE_ZERO = 100

val BASE_NUMBERS = (1..36)
val ALL_NUMBERS = BASE_NUMBERS + ZERO + DOUBLE_ZERO
val EVEN_NUMBERS = BASE_NUMBERS.filter { it % 2 == 0 }
val ODD_NUMBERS = BASE_NUMBERS.filter { it % 2 != 0 }
val RED_NUMBERS = EVEN_NUMBERS
val BLACK_NUMBERS = ODD_NUMBERS
val LOW_NUMBERS = (1..18)
val HIGH_NUMBERS = (19..36)

fun takeRandomNumber(): Int = ALL_NUMBERS.random(SecureRandom().asKotlinRandom())

fun Int.asDisplayNumber() =
    when (this) {
        ZERO -> "${Emojis.greenCircle.unicode}${nbsp}0"
        DOUBLE_ZERO -> "${Emojis.greenCircle.unicode}${nbsp}00"
        else -> "${if (this in RED_NUMBERS) Emojis.redCircle.unicode else Emojis.blackCircle.unicode}${nbsp}$this"
    }
