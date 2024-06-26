package dev.vrba.discord;

import io.kotest.matchers.string.shouldContain
import io.kotest.core.spec.style.BehaviorSpec
import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class RouletteArenaCommandSpec: BehaviorSpec({

    given("roulette-arena") {
        val ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)

        `when`("invocation with -v") {
            val baos = ByteArrayOutputStream()
            System.setOut(PrintStream(baos))

            val args = arrayOf("-v")
            PicocliRunner.run(RouletteArenaCommand::class.java, ctx, *args)

            then("should display greeting") {
                baos.toString() shouldContain "Hi!"
            }
        }

        `when`("other") {
            // add more tests...
        }

        ctx.close()
    }
})