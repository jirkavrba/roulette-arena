package dev.vrba.discord.domain

sealed interface Bet {
    val numbers: Set<Int>
    val multiplier: Int
}

class SingleNumberBet(
    number: Int,
) : Bet {
    override val numbers: Set<Int> = setOf(number)
    override val multiplier = 35
}

data object ZeroBet : Bet {
    override val numbers: Set<Int> = setOf(ZERO)
    override val multiplier = 35
}

data object DoubleZeroBet : Bet {
    override val numbers: Set<Int> = setOf(DOUBLE_ZERO)
    override val multiplier = 35
}

class DozenBet(
    base: Int,
) : Bet {
    override val numbers: Set<Int> = (base..(base + 11)).toSet()
    override val multiplier: Int = 3
}

class ColumnBet(
    base: Int,
) : Bet {
    override val numbers: Set<Int> = BASE_NUMBERS.filter { (it % 3) == (base % 3) }.toSet()
    override val multiplier: Int = 3
}

data object LowNumberBet : Bet {
    override val numbers: Set<Int> = LOW_NUMBERS.toSet()
    override val multiplier: Int = 2
}

data object HighNumberBet : Bet {
    override val numbers: Set<Int> = HIGH_NUMBERS.toSet()
    override val multiplier: Int = 2
}

data object EvenNumberBet : Bet {
    override val numbers: Set<Int> = EVEN_NUMBERS.toSet()
    override val multiplier: Int = 2
}

data object OddNumberBet : Bet {
    override val numbers: Set<Int> = ODD_NUMBERS.toSet()
    override val multiplier: Int = 2
}

data object RedNumberBet : Bet {
    override val numbers: Set<Int> = RED_NUMBERS.toSet()
    override val multiplier: Int = 2
}

data object BlackNumberBet : Bet {
    override val numbers: Set<Int> = BLACK_NUMBERS.toSet()
    override val multiplier: Int = 2
}
