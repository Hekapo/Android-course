package com.example.firstlesson

class Circle(name: String, private val radius: Double) : Shape(name), Area{
    override fun calculateArea():Double {
        return radius * radius * Math.PI
    }

}
