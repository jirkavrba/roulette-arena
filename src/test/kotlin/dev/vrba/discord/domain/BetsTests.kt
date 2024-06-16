package dev.vrba.discord.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly

class BetsTests :
    StringSpec({

        "Column should return correct numbers" {
            ColumnBet(1).numbers shouldContainExactly
                setOf(
                    1,
                    4,
                    7,
                    10,
                    13,
                    16,
                    19,
                    22,
                    25,
                    28,
                    31,
                    34,
                )

            ColumnBet(2).numbers shouldContainExactly
                setOf(
                    2,
                    5,
                    8,
                    11,
                    14,
                    17,
                    20,
                    23,
                    26,
                    29,
                    32,
                    35,
                )

            ColumnBet(3).numbers shouldContainExactly
                setOf(
                    3,
                    6,
                    9,
                    12,
                    15,
                    18,
                    21,
                    24,
                    27,
                    30,
                    33,
                    36,
                )
        }

        "Dozen should return correct numbers" {
            DozenBet(1).numbers shouldContainExactly
                setOf(
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10,
                    11,
                    12,
                )

            DozenBet(13).numbers shouldContainExactly
                setOf(
                    13,
                    14,
                    15,
                    16,
                    17,
                    18,
                    19,
                    20,
                    21,
                    22,
                    23,
                    24,
                )

            DozenBet(25).numbers shouldContainExactly
                setOf(
                    25,
                    26,
                    27,
                    28,
                    29,
                    30,
                    31,
                    32,
                    33,
                    34,
                    35,
                    36,
                )
        }
    })
