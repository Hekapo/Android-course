package com.example.firstlesson

class Rectangle(name: String, private val side: Double) : Shape(name), Square {
    override fun calculateSquare(): Double {
        return side * side

    }
}
