package com.example.firstlesson

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstlesson.databinding.ActivityTwoBinding


class ActivityTwo : AppCompatActivity() {

    private lateinit var binding: ActivityTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //2) получает неявный интент(на ваш выбор)
        // и показывает полученные данные на экране.
        // Лучше задать все атрибуты для интент фильтра(action, category, data)
        val action = intent.action
        if (action != null) {
            val message = intent.getStringExtra(Intent.EXTRA_TEXT)
            binding.textView.text = message
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        }


    }
}
