package com.example.firstlesson.repository

import com.example.firstlesson.R
import com.example.firstlesson.models.Song

object SongRepositoryImpl : SongRepository {


    override fun getSongs(): List<Song> {
        return songs
    }

    override fun nextSong(currentSong: Song): Song {
        val songId = (currentSong.id + 1) % songs.size
        return songs[songId]
    }

    override fun prevSong(currentSong: Song): Song {
        var songId = 0
        if (currentSong.id >= 0) {
            val last = songs.size + currentSong.id
            songId = (last - 1) % songs.size

        }
        return songs[songId]
    }

    override fun getSongById(id: Int): Song {
        return songs.first { it.id == id }
    }
}

private var songs = mutableListOf(
    Song(
        0,
        R.drawable.jojo_pillar_men,
        R.raw.awaken,
        "Jojo's Bizarre Adventure - Awaken",

        ),
    Song(
        1,
        R.drawable.run,
        R.raw.phonk_for_running,
        "Phonk for running",
    ),
    Song(
        2,
        R.drawable.winx_gf,
        R.raw.winks,
        "Winx together 6+",
    ),
    Song(
        3,
        R.drawable.billy_herrington,
        R.raw.gruppa_krovi,
        "Gruppa krovi - Viktor Coy (feat. Billy) ",
    ),
    Song(
        4,
        R.drawable.joker,
        R.raw.dulo_morgen,
        "DULO - MORGENSHTERN",

        ),
    Song(
        5,
        R.drawable.john_cena,
        R.raw.social_credits_song,
        "Social CREDITS Song Xina (feat. John Cena)"
    )

)

