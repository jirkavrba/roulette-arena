package dev.vrba.discord.domain

sealed interface Bet {
    val title: String
    val numbers: Set<Int>
    val multiplier: Int
}

class SingleNumberBet(
    number: Int,
) : Bet {
    override val title = "Single number: $number"
    override val numbers = setOf(number)
    override val multiplier = 35
}

data object ZeroBet : Bet {
    override val title = "0"
    override val numbers = setOf(ZERO)
    override val multiplier = 35
}

data object DoubleZeroBet : Bet {
    override val title = "00"
    override val numbers = setOf(DOUBLE_ZERO)
    override val multiplier = 35
}

class DozenBet(
    base: Int,
) : Bet {
    override val title = "Dozen $base - ${base + 11}"
    override val numbers = (base..(base + 11)).toSet()
    override val multiplier = 3
}

class ColumnBet(
    base: Int,
) : Bet {
    override val title = "Column $base"
    override val numbers = BASE_NUMBERS.filter { (it % 3) == (base % 3) }.toSet()
    override val multiplier: Int = 3
}

data object LowNumberBet : Bet {
    override val title = "Low numbers"
    override val numbers = LOW_NUMBERS.toSet()
    override val multiplier = 2
}

data object HighNumberBet : Bet {
    override val title = "High number"
    override val numbers = HIGH_NUMBERS.toSet()
    override val multiplier = 2
}

data object EvenNumberBet : Bet {
    override val title = "Even number"
    override val numbers = EVEN_NUMBERS.toSet()
    override val multiplier = 2
}

data object OddNumberBet : Bet {
    override val title = "Odd number"
    override val numbers = ODD_NUMBERS.toSet()
    override val multiplier = 2
}

data object RedNumberBet : Bet {
    override val title = "Red number"
    override val numbers = RED_NUMBERS.toSet()
    override val multiplier = 2
}

data object BlackNumberBet : Bet {
    override val title = "Black number"
    override val numbers = BLACK_NUMBERS.toSet()
    override val multiplier = 2
}
