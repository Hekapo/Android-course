package com.example.firstlesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firstlesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment(HomeScreenFragment())

        binding.item1.setOnClickListener {

            replaceFragment(HomeScreenFragment())
        }

        binding.item2.setOnClickListener {
            replaceFragment(NotificationScreenFragment())
        }

        binding.item3.setOnClickListener {
            replaceFragment(MessagesScreenFragment())
        }
        binding.item4.setOnClickListener {
            replaceFragment(SettingsScreenFragment())
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )

            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
    }
}
