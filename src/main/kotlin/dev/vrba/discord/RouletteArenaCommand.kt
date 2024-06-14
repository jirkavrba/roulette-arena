package dev.vrba.discord

import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine.Command
import picocli.CommandLine.Option

@Command(
    name = "roulette-arena",
    description = ["..."],
    mixinStandardHelpOptions = true,
)
class RouletteArenaCommand : Runnable {
    @Option(
        names = ["-t", "--token"],
        description = ["Discord token that the bot should run with"],
        required = true,
    )
    private lateinit var token: String

    override fun run() {
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PicocliRunner.run(RouletteArenaCommand::class.java, *args)
        }
    }
}
