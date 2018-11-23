package com.almasb.lang

import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

class LexerTest {

    @Test
    fun `Test basic`() {
        val line = "3 + 5 + 7"

        assertThat(Lexer().tokenize(line), contains(
                Token(TokenType.NUMBER, "3"),
                Token(TokenType.OPERATOR, "+"),
                Token(TokenType.NUMBER, "5"),
                Token(TokenType.OPERATOR, "+"),
                Token(TokenType.NUMBER, "7")
        ))
    }

    @Test
    fun `Test negative`() {
        val line = "-5 + 1 + -13"

        assertThat(Lexer().tokenize(line), contains(
                Token(TokenType.NUMBER, "-5"),
                Token(TokenType.OPERATOR, "+"),
                Token(TokenType.NUMBER, "1"),
                Token(TokenType.OPERATOR, "+"),
                Token(TokenType.NUMBER, "-13")
        ))
    }

    @Test
    fun `Test all`() {
        val line = "-5 + x + -13 * y"

        assertThat(Lexer().tokenize(line), contains(
                Token(TokenType.NUMBER, "-5"),
                Token(TokenType.OPERATOR, "+"),
                Token(TokenType.VARIABLE, "x"),
                Token(TokenType.OPERATOR, "+"),
                Token(TokenType.NUMBER, "-13"),
                Token(TokenType.OPERATOR, "*"),
                Token(TokenType.VARIABLE, "y")
        ))
    }
}