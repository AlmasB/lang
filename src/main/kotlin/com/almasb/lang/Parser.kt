package com.almasb.lang

import com.almasb.lang.TokenType.*
import java.lang.RuntimeException
import java.util.*

class Parser {

    fun parse(tokens: List<Token>): Exp {
        val expStack: Deque<Exp> = ArrayDeque()
        val operatorStack: Deque<String> = ArrayDeque()

        val wrappedTokens = listOf(Token(OPERATOR, "(")) + tokens + Token(OPERATOR, ")")

        for (token in wrappedTokens) {

            when (token.type) {
                NUMBER -> {
                    expStack.addLast(Val(token.value))
                }

                OPERATOR -> {

                    when (token.value) {
                        "(" -> operatorStack.addLast(token.value)

                        ")" -> {
                            while (operatorStack.isNotEmpty()
                                    && operatorStack.peekLast() != "(") {

                                val right = expStack.removeLast()
                                val left = expStack.removeLast()

                                val op = operatorStack.removeLast()

                                expStack.addLast(buildExp(op, left, right))
                            }

                            // '('
                            operatorStack.removeLast()
                        }

                        else -> {
                            while (operatorStack.isNotEmpty()
                                    && operatorStack.peekLast().operatorPrecedence() >= token.value.operatorPrecedence()) {

                                val right = expStack.removeLast()
                                val left = expStack.removeLast()

                                val op = operatorStack.removeLast()

                                expStack.addLast(buildExp(op, left, right))
                            }

                            operatorStack.addLast(token.value)
                        }
                    }
                }
            }
        }

        return expStack.removeLast()
    }

    private fun buildExp(op: String, left: Exp, right: Exp): Exp {
        return when (op) {
            "+" -> Add(left, right)
            "-" -> Sub(left, right)
            "*" -> Mul(left, right)
            "/" -> Div(left, right)
            else -> throw RuntimeException("Unknown op: $op")
        }
    }
}

private fun String.operatorPrecedence(): Int {
    return when (this) {
        "*", "/" -> 100
        "+", "-" -> 1
        else -> 0
    }
}
