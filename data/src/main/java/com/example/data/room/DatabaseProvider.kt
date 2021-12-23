package com.example.data.room

import com.example.data.room.dao.ToDoDAO

interface DatabaseProvider {
    fun provideDataBase(): ToDoDatabase
    fun provideDao(): ToDoDAO
}
