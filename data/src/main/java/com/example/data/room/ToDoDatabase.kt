package com.example.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.room.dao.ToDoDAO
import com.example.data.room.entity.ToDo
import com.example.data.room.entity.ToDoFts

@Database(entities = [ToDo::class, ToDoFts::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDAO(): ToDoDAO

    companion object {

        private const val DATABASE_NAME = "todo.db"

        @Volatile
        private var instance: ToDoDatabase? = null
        private val LOCK = Any()

        /*
        * Можно написть и так:
        * operator fun invoke(context: Context) = instance ?: buildDatabase(context).also { instance = it }
        *
        * Это более простой способо реализации без проверки на синхронизации потоков. Но может сложиться такая ситуация,
        * когда 2 разных потока придут сюда и создадут 2 разных объекта и у вас уже не синглтоновый объект
        */
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, ToDoDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}
