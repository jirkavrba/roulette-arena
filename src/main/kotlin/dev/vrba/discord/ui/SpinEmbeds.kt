package dev.vrba.discord.ui

import dev.kord.common.entity.ButtonStyle
import dev.kord.rest.builder.component.ActionRowBuilder
import dev.kord.rest.builder.message.MessageBuilder
import dev.kord.rest.builder.message.actionRow
import dev.kord.rest.builder.message.embed
import dev.kord.x.emoji.Emojis
import dev.vrba.discord.domain.DOUBLE_ZERO
import dev.vrba.discord.domain.RED_NUMBERS
import dev.vrba.discord.domain.ZERO

const val ROULETTE_PNG = "https://raw.githubusercontent.com/jirkavrba/roulette-arena/main/src/main/resources/roulette.png"
const val ROULETTE_COLUMN_PNG = "https://raw.githubusercontent.com/jirkavrba/roulette-arena/main/src/main/resources/roulette-column.png"
const val ROULETTE_DOZEN_PNG = "https://raw.githubusercontent.com/jirkavrba/roulette-arena/main/src/main/resources/roulette-dozen.png"

fun MessageBuilder.rootBetEmbed() {
    embed {
        title = "Select what you want to bet on"
        image = ROULETTE_PNG
    }
}

fun MessageBuilder.rootBetButtons() {
    actionRow {
        button("bet:dozen", "${Emojis.greenCircle.unicode} Dozen")
        button("bet:column", "${Emojis.yellowCircle.unicode} Column")
        button("bet:low-high", "${Emojis.purpleCircle.unicode} Low / High")
        button("bet:even-odd", "${Emojis.blueCircle.unicode} Even / Odd")
    }

    actionRow {
        button("bet:red", "Red", ButtonStyle.Danger)
        button("bet:black", "Black", ButtonStyle.Secondary)
        button("bet:numbers", "${Emojis.`1234`.unicode} Numbers", ButtonStyle.Primary)
    }
}

fun MessageBuilder.dozenBetsEmbed() {
    embed {
        title = "Select your desired dozen"
        description = "To cancel the bet, simply dismiss this message"
        image = ROULETTE_DOZEN_PNG
    }
}

fun MessageBuilder.dozenBetsButtons() {
    actionRow {
        button("bet:dozen:1", "1 - 12", ButtonStyle.Danger)
        button("bet:dozen:13", "13 - 24", ButtonStyle.Success)
        button("bet:dozen:25", "25 - 36", ButtonStyle.Primary)
    }
}

fun MessageBuilder.columnBetsEmbed() {
    embed {
        title = "Select your desired column"
        description = "To cancel the bet, simply dismiss this message"
        image = ROULETTE_COLUMN_PNG
    }
}

fun MessageBuilder.columnBetsButtons() {
    actionRow {
        button("bet:column:1", "1 - 34", ButtonStyle.Danger)
        button("bet:column:2", "2 - 35", ButtonStyle.Success)
        button("bet:column:3", "3 - 36", ButtonStyle.Primary)
    }
}

fun MessageBuilder.lowHighBetsEmbed() {
    embed {
        title = "Select your desired bet"
        description = "To cancel the bet, simply dismiss this message"
        image = ROULETTE_PNG
    }
}

fun MessageBuilder.lowHighBetsButtons() {
    actionRow {
        button("bet:low", "Low: 1 - 18", ButtonStyle.Secondary)
        button("bet:high", "High: 19 - 36", ButtonStyle.Secondary)
    }
}

fun MessageBuilder.oddEvenBetsEmbed() {
    embed {
        title = "Select your desired bet"
        description = "To cancel the bet, simply dismiss this message"
        image = ROULETTE_PNG
    }
}

fun MessageBuilder.oddEvenBetsButtons() {
    actionRow {
        button("bet:odd", "Odd", ButtonStyle.Secondary)
        button("bet:even", "Even", ButtonStyle.Secondary)
    }
}

fun MessageBuilder.numberBetsEmbed() {
    embed {
        title = "Select your desired bet"
        description = "To cancel the bet, simply dismiss this message"
        image = ROULETTE_PNG
    }
}

fun MessageBuilder.numberBetsButton() {
    actionRow {
        button("bet:number:$ZERO", "0", ButtonStyle.Success)
        button("bet:number:$DOUBLE_ZERO", "00", ButtonStyle.Success)
        button("bet:numbers:selection", "1 - 36", ButtonStyle.Secondary)
    }
}

fun MessageBuilder.numberDozenBetsEmbed() {
    embed {
        title = "Select your desired dozen"
        description = "To cancel the bet, simply dismiss this message"
        image = ROULETTE_DOZEN_PNG
    }
}

fun MessageBuilder.numberDozenBetsButton() {
    actionRow {
        button("bet:numbers:dozen:1", "1 - 12", ButtonStyle.Danger)
        button("bet:numbers:dozen:13", "13 - 24", ButtonStyle.Success)
        button("bet:numbers:dozen:25", "25 - 36", ButtonStyle.Primary)
    }
}

fun MessageBuilder.numberSelectionBetsEmbed() {
    embed {
        title = "Select your desired number"
        description = "To cancel the bet, simply dismiss this message"
        image = ROULETTE_PNG
    }
}

fun MessageBuilder.numberSelectionBetsButtons(numbers: List<Int>) {
    val rows = numbers.chunked(3)

    rows.forEach { row ->
        actionRow {
            row.forEach { number ->
                val style = if (number in RED_NUMBERS) ButtonStyle.Danger else ButtonStyle.Secondary
                button("bet:number:$number", number.toString(), style)
            }
        }
    }
}

fun ActionRowBuilder.button(
    id: String,
    label: String,
    style: ButtonStyle = ButtonStyle.Secondary,
) {
    interactionButton(style, id) { this.label = label }
}
