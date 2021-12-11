package com.example.firstlesson.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.firstlesson.MainActivity
import com.example.firstlesson.R
import com.example.firstlesson.databinding.FragmentMusicScreenBinding
import com.example.firstlesson.models.Song
import com.example.firstlesson.presenter.MusicPresenter
import com.example.firstlesson.view.MusicView

class MusicScreenFragment : Fragment(), MusicView {
    private lateinit var binding: FragmentMusicScreenBinding

    private lateinit var mActivity: FragmentActivity
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let { mActivity = it }
        mainActivity = mActivity as MainActivity

    }

    private val presenter by lazy {
        MusicPresenter(this, binder = mainActivity.binder)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicScreenBinding.inflate(inflater, container, false)
        mainActivity.binder?.setIntentActionListener(presenter)


        val songId = arguments?.getInt("SONG_ID")
        val songFromNotif = requireArguments().getInt("SONG")
        Log.e("TAG", "from notification $songFromNotif")
        Log.e("TAG", "from list $songId")
        if (songFromNotif != 0) {
            presenter.getMusicById(songFromNotif)
        }else{

            if (songId != null) {
                presenter.getMusicById(songId)
            }
        }

//        if(songFromNotif !=null ){
//            presenter.getMusicById(songFromNotif)
//        }

        binding.nextBtn.setOnClickListener {
            presenter.nextSong()
        }

        binding.pauseBtn.setOnClickListener {
            presenter.pauseSong()
        }

        binding.prevBtn.setOnClickListener {
            presenter.prevSong()
        }

        return binding.root
    }

    override fun setMusic(song: Song) {
        binding.coverIv.setImageResource(song.cover)
        binding.songTitle.text = song.audioTitle
    }

    override fun setPauseButton() {
        binding.pauseBtn.setImageResource(R.drawable.ic_pause)
    }

    override fun setPlayButton() {
        binding.pauseBtn.setImageResource(R.drawable.ic_play)
    }
}
