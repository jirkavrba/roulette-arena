package dev.vrba.discord.ui

import dev.kord.common.entity.ButtonStyle
import dev.kord.rest.builder.component.ActionRowBuilder
import dev.kord.rest.builder.message.MessageBuilder
import dev.kord.rest.builder.message.actionRow
import dev.kord.rest.builder.message.embed
import dev.kord.x.emoji.Emojis

const val ROULETTE_PNG = "https://raw.githubusercontent.com/jirkavrba/roulette-arena/main/src/main/resources/roulette.png"

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

fun ActionRowBuilder.button(
    id: String,
    label: String,
    style: ButtonStyle = ButtonStyle.Secondary,
) {
    interactionButton(style, id) { this.label = label }
}
