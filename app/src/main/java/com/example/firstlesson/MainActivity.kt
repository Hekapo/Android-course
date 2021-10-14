package com.example.firstlesson

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstlesson.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1) отправляет неявный интент в систему(на ваш выбор)
        // и обрабатывает результат с помощью onActivityResult.
        // Ответ можете вывести на экран используя Snackbar.
        binding.takePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        binding.sendMessageBtn.setOnClickListener {
            val message = binding.edText.text.toString()
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(intent)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            binding.image.setImageURI(uri)
        }
    }
}
