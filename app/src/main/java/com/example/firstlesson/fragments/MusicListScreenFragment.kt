package com.example.firstlesson.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.firstlesson.MainActivity
import com.example.firstlesson.R
import com.example.firstlesson.adapter.SongAdapter
import com.example.firstlesson.databinding.FragmentMusicListScreenBinding
import com.example.firstlesson.models.Song
import com.example.firstlesson.presenter.MusicListPresenter
import com.example.firstlesson.view.MusicListView

class MusicListScreenFragment : Fragment(),
    MusicListView, SongAdapter.OnSongClickListener {

    private lateinit var binding: FragmentMusicListScreenBinding
    private var songList = emptyList<Song>()
    private val adapter by lazy {
        SongAdapter(songList)
    }
    private val musicPresenter = MusicListPresenter(this)

    private lateinit var mActivity: FragmentActivity
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let { mActivity = it }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicListScreenBinding.inflate(inflater, container, false)
        mainActivity = mActivity as MainActivity
        musicPresenter.getSongs()


        setRecyclerview()
        return binding.root
    }

    private fun setRecyclerview() {
        binding.rvEpisodes.adapter = adapter
        adapter.setOnItemClickListener(this)
    }

    override fun setSongs(list: List<Song>) {
        songList = list
    }

    override fun navigateTo(destination: Int, bundle: Bundle) {
        binding.root.findNavController().navigate(destination, bundle)
    }

    override fun setOnclick(song: Song) {
        mainActivity.binder?.let { musicPresenter.play(song, it) }
        val bundle = Bundle()
        bundle.putInt("SONG_ID", song.id)
        musicPresenter.navigate(R.id.action_musicListScreenFragment_to_musicScreenFragment, bundle)
    }

}
