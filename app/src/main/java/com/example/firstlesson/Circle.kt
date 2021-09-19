package com.example.firstlesson

class Circle(name: String, private val radius: Double) : Shape(name), Radius {
    override fun calculateRadius():Double {
        return radius * radius + Math.PI
    }

}
