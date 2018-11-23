package com.almasb.lang

import java.util.*

class Token(val type: TokenType,
            val value: String) {

    override fun equals(other: Any?): Boolean {
        if (other !is Token)
            return false

        return other.type == type && other.value == value
    }

    override fun hashCode(): Int {
        return Objects.hash(type, value)
    }

    override fun toString(): String {
        return "$value : $type"
    }
}

enum class TokenType {
    NUMBER, OPERATOR, VARIABLE;

    companion object {
        fun from(value: String): TokenType {
            return when (value) {
                "+", "-", "*", "/", "(", ")" -> OPERATOR
                else -> {
                    if (value.contains("[a-z]".toRegex())) VARIABLE else NUMBER
                }
            }
        }
    }
}