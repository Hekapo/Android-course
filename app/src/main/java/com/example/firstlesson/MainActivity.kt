package com.example.firstlesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bigCircle = Circle("bigCircle", 20.0)
        println(bigCircle.name + bigCircle.calculateArea())

        val bigRectangle = Rectangle("bigRectangle", 20.0)
        println(bigRectangle.name + bigRectangle.calculateSquare())


    }
}
