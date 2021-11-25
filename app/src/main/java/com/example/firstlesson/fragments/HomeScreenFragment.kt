package com.example.firstlesson.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.firstlesson.alarm.CustomNotificationChannel
import com.example.firstlesson.databinding.FragmentHomeScreenBinding
import com.example.firstlesson.presenter.HomeScreenPresenter
import com.example.firstlesson.view.HomeScreenView

class HomeScreenFragment : Fragment(), HomeScreenView {

    private lateinit var binding: FragmentHomeScreenBinding
    private var channel: CustomNotificationChannel? = null
    private val presenter by lazy {
        HomeScreenPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        channel = CustomNotificationChannel()

        channel?.createChannel(context)

        binding.ivClock.setOnClickListener {
            presenter.onTimeClicked()

        }
        binding.btnStart.setOnClickListener {
            presenter.setAlarm(context)
        }

        binding.btnStop.setOnClickListener {
            presenter.stopAlarm(context)

        }

        return binding.root
    }

    override fun setTimeText(time: String) {
        binding.time.text = time
    }

    override fun showTimePicker() {
        presenter.showTimePicker(fragmentManager = childFragmentManager)
    }

    override fun showToast(context: Context) {
        Toast.makeText(context, "Alarm started", Toast.LENGTH_SHORT).show()
    }

    override fun hideToast(context: Context) {
        Toast.makeText(context, "Alarm stopped", Toast.LENGTH_SHORT).show()
    }

    override fun setClickableIv() {
        binding.ivClock.isClickable = true
    }

    override fun setNotClickableIv() {
        binding.ivClock.isClickable = false
    }

    override fun onDestroy() {
        super.onDestroy()
        channel = null
    }
}
