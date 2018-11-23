package com.almasb.lang

class Lexer {

    fun tokenize(line: String): List<Token> {
        return line.split(" +".toRegex())
                .map { Token(TokenType.from(it), it) }
    }
}