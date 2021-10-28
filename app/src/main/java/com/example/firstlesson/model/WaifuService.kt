package com.example.firstlesson.model

import com.example.firstlesson.model.WaifuService.DATA.ABOUT
import com.example.firstlesson.model.WaifuService.DATA.AGES
import com.example.firstlesson.model.WaifuService.DATA.IMAGES
import com.example.firstlesson.model.WaifuService.DATA.NAMES


object WaifuService {

    private var waifuList = mutableListOf<Waifu>()

    init {
        IMAGES.shuffle()
        NAMES.shuffle()
        AGES.shuffle()
        ABOUT.shuffle()

        waifuList = (1..30).map {
            Waifu(
                id = it,
                name = NAMES[it % NAMES.size],
                imageUrl = IMAGES[it % IMAGES.size],
                age = AGES[it % AGES.size],
                about = ABOUT[it % ABOUT.size]

            )
        }.toMutableList()

    }

    fun getAllWaifus(): MutableList<Waifu> {
        return waifuList

    }

    object DATA {
        val IMAGES = mutableListOf(
            "https://i.waifu.pics/5At1P4A.jpg",
            "https://i.waifu.pics/k-akF2p.jpg",
            "https://i.waifu.pics/cG2o0Hs.jpg",
            "https://i.waifu.pics/hFkV1Ny.jpg",
            "https://i.waifu.pics/u~qTv5c.jpg",
            "https://i.waifu.pics/36onaHT.jpg",
        )
        val NAMES = mutableListOf(
            "Sakura",
            "Miku Nakano",
            "Tatsumaki",
            "Mai Sakurajima",
            "Erza Scarlet",
            "Asuna Yuuki",
        )
        val AGES = mutableListOf(
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
        )
        val ABOUT = mutableListOf(
            "A model, an actress, and a student, and someone who can turn invisible, Mai is an attractive young girl who is the protagonist of Seishun Buta Yarou wa Bunny Girl Senpai no Yume wo Minai.",
            "The tritagonist of the webcomic turned manga/anime series, One Punch Man, Tatsumaki a.k.a. Tornado of Terror is an S Class Rank 2 Esper and a professional hero from the Hero Association.",
            "The Quintessential Quintuplets is an anime series following the lives of five sisters who have terrible grades but have some really complex personalities.",
            "Essentially one of the strongest characters in the Fairy Tail universe who quite literally has the ability to become a hero and also go to the dark side.",
            "Amongst amazingly talented cooks and chefs on the show is Alice Nakiri. A pedigree chef belonging to the famed Nakiri Family from the series.",
            "Another clumsy but strong character on our list is Sheele from Akame Ga Kill! She is an assassin and a member of the Night Raid.",
        )
    }
}
