package com.almasb.lang

import com.almasb.lang.TokenType.*
import java.util.*

class Parser {

    private val expStack: Deque<Exp> = ArrayDeque()
    private val operatorStack: Deque<String> = ArrayDeque()

    fun parse(tokens: List<Token>): Exp {

        for (token in tokens) {

            when (token.type) {
                NUMBER -> {
                    expStack.addLast(Val(token.value))

                }

                OPERATOR -> {

                    if (operatorStack.isEmpty()) {
                        operatorStack.addLast(token.value)
                    } else {

                        while (operatorStack.isNotEmpty() && operatorStack.peekLast().precedes(token.value)) {

                            val right = expStack.removeLast()
                            val left = expStack.removeLast()

                            val op = operatorStack.removeLast()

                            when (op) {
                                "+" -> expStack.addLast(Add(left, right))
                                "-" -> expStack.addLast(Sub(left, right))
                                "*" -> expStack.addLast(Mul(left, right))
                                "/" -> expStack.addLast(Div(left, right))
                            }
                        }

                        operatorStack.addLast(token.value)
                    }
                }
            }

//            println("Token is $token and State:")
//            println(expStack)
//            println(operatorStack)
        }

        while (operatorStack.isNotEmpty()) {

            //val right = Val(token.value)
            val right = expStack.removeLast()
            val left = expStack.removeLast()

            val op = operatorStack.removeLast()

            when (op) {
                "+" -> expStack.addLast(Add(left, right))
                "-" -> expStack.addLast(Sub(left, right))
                "*" -> expStack.addLast(Mul(left, right))
                "/" -> expStack.addLast(Div(left, right))
            }
        }

        return expStack.removeLast()
    }
}

private fun String.precedes(other: String): Boolean {
    if (this == "*" || this == "/")
        return true

    if (other == "*" || other == "/")
        return false

    return true
}