package dev.vrba.discord.domain

sealed interface Bet {
    val numbers: Set<Int>
    val multiplier: Int
}

class SingleNumberBet(
    number: Int,
) : Bet {
    override val numbers: Set<Int> = setOf(number)
    override val multiplier = 1
}

class DozenBet(
    base: Int,
) : Bet {
    override val numbers: Set<Int> = BASE_NUMBERS.filter { (it % 3) == (base % 3) }.toSet()
    override val multiplier: Int = 1
}

class ColumnBet(
    base: Int,
) : Bet {
    override val numbers: Set<Int> = (base..(base + 11)).toSet()
    override val multiplier: Int = 1
}
