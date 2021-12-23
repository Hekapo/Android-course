package com.example.data.storage

import android.os.Bundle
import com.example.data.storage.model.ToDoModel
import com.example.data.utils.Constants.TODO_ID

class BundleIdStorage : ToDoItemStorage {

    companion object {
        private val bundle = Bundle()
    }

    override fun saveId(toDoModel: ToDoModel) = bundle.putLong(TODO_ID, toDoModel.id)

    override fun getId() = bundle.getLong(TODO_ID)

    override fun clearStorage() = bundle.clear()
}
