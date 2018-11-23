package com.almasb.lang

import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

class ExpTest {

    @Test
    fun `Test basic`() {
        val line = "3 + 5 + 7"

        val exp: Exp = Parser().parse(Lexer().tokenize(line))

        assertThat(exp.eval().toInt(), `is`(3 + 5 + 7))
    }

    @Test
    fun `Test complex add`() {
        val line = "3 + 5 + 7 + 15 + 3 + 75"

        val exp: Exp = Parser().parse(Lexer().tokenize(line))

        assertThat(exp.eval().toInt(), `is`(3 + 5 + 7 + 15 + 3 + 75))
    }

    @Test
    fun `Test + -`() {
        val line = "3 + 5 - 7 + 15 - 3 - 15"

        val exp: Exp = Parser().parse(Lexer().tokenize(line))

        assertThat(exp.eval().toInt(), `is`(3 + 5 - 7 + 15 - 3 - 15))
    }

    @Test
    fun `Test + - mul and div`() {
        val line = "3 + 5 * 7 + 15 - 15 / 5"

        val exp: Exp = Parser().parse(Lexer().tokenize(line))

        assertThat(exp.eval().toInt(), `is`(3 + 5 * 7 + 15 - 15 / 5))
    }

    @Test
    fun `Test complex + - mul and div`() {
        val line = "3 * 5 + 2 * 7 + 15 - 15 / 5 / 3 + 6 / 2 - 2 + 1 * 4 * 6"

        val exp: Exp = Parser().parse(Lexer().tokenize(line))

        assertThat(exp.eval().toInt(), `is`(3 * 5 + 2 * 7 + 15 - 15 / 5 / 3 + 6 / 2 - 2 + 1 * 4 * 6))
    }

    @Test
    fun `Test complex + - mul and div with paren`() {
        val line = "3 * ( 5 + 2 ) * 7 + ( 15 - 15 ) / 5 / 3 + ( 6 / 2 ) - 2 + 1 * 4 * 6"

        val exp: Exp = Parser().parse(Lexer().tokenize(line))

        assertThat(exp.eval().toInt(), `is`(3 * ( 5 + 2 ) * 7 + ( 15 - 15 ) / 5 / 3 + ( 6 / 2 ) - 2 + 1 * 4 * 6))
    }

    @Test
    fun `Test complex + - mul and div with paren recursive`() {
        val line = "3 * ( ( 5 + 2 ) ) * 7 + ( 15 - 15 ) / 5 / 3 + ( 6 / 2 ) - 2 + 1 * 4 * 6"

        val exp: Exp = Parser().parse(Lexer().tokenize(line))

        assertThat(exp.eval().toInt(), `is`(3 * ( ( 5 + 2 ) ) * 7 + ( 15 - 15 ) / 5 / 3 + ( 6 / 2 ) - 2 + 1 * 4 * 6))
    }

    @Test
    fun `Test complex + - mul and div with paren inside`() {
        val line = "3 * ( 155 - ( 5 + 2 ) ) * 7 + ( 15 - 15 ) / 5 / 3 + ( 6 / 2 ) - 2 + 1 * 4 * 6"

        val exp: Exp = Parser().parse(Lexer().tokenize(line))

        assertThat(exp.eval().toInt(), `is`(3 * ( 155 - ( 5 + 2 ) ) * 7 + ( 15 - 15 ) / 5 / 3 + ( 6 / 2 ) - 2 + 1 * 4 * 6))
    }

    @Test
    fun `Test vars`() {
        val line = "-5 + x + -13 * y / z + 3"

        val env = Env({ })
        env.memory["x"] = "3"
        env.memory["y"] = "2"
        env.memory["z"] = "-13"

        val exp: Exp = Parser(env).parse(Lexer().tokenize(line))

        assertThat(exp.eval().toInt(), `is`(-5 + 3 + -13 * 2 / -13 + 3))
    }
}