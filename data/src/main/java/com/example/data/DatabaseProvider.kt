package com.example.data

import android.content.Context
import com.example.data.room.ToDoDatabase
import com.example.data.room.dao.ToDoDAO

interface DatabaseProvider {
    fun provideDataBase(): ToDoDatabase
    fun provideDao(): ToDoDAO
}
