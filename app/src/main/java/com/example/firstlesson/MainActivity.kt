package com.example.firstlesson

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstlesson.databinding.VkComScreenBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: VkComScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VkComScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.editLayout.setOnClickListener {
            with(binding) {

                if (mood.text.toString() == ""
                    || placeStudy.text.toString() == ""
                    || placeWork.text.toString() == ""
                ) {
                    Toast.makeText(applicationContext, "Fill all fields", Toast.LENGTH_LONG).show()

                } else {
                    mood.hint = mood.text
                    placeStudy.hint = placeStudy.text
                    placeWork.hint = placeWork.text

                    mood.isEnabled = false
                    mood.isCursorVisible = false

                    placeStudy.isEnabled = false
                    placeStudy.isCursorVisible = false

                    placeWork.isEnabled = false
                    placeWork.isCursorVisible = false
                    Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()

                }
            }


        }


    }
}
