package com.almasb.lang

sealed class Exp {

    abstract fun eval(): Number
}

class Val(val value: String) : Exp() {
    override fun eval(): Number {
        return value.toInt()
    }

    override fun toString(): String {
        return value
    }
}

class Add(val left: Exp, val right: Exp) : Exp() {
    override fun eval(): Number {
        return left.eval().toInt() + right.eval().toInt()
    }

    override fun toString(): String {
        return left.toString() + "+" + right.toString()
    }
}

class Sub(val left: Exp, val right: Exp) : Exp() {
    override fun eval(): Number {
        return left.eval().toInt() - right.eval().toInt()
    }

    override fun toString(): String {
        return left.toString() + "-" + right.toString()
    }
}

class Mul(val left: Exp, val right: Exp) : Exp() {
    override fun eval(): Number {
        return left.eval().toInt() * right.eval().toInt()
    }

    override fun toString(): String {
        return left.toString() + "*" + right.toString()
    }
}

class Div(val left: Exp, val right: Exp) : Exp() {
    override fun eval(): Number {
        return left.eval().toInt() / right.eval().toInt()
    }

    override fun toString(): String {
        return left.toString() + "/" + right.toString()
    }
}