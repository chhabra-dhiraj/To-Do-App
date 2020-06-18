package io.github.chhabra_dhiraj.to_do.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import io.github.chhabra_dhiraj.to_do.database.DatabaseToDo
import io.github.chhabra_dhiraj.to_do.database.ToDoDao
import io.github.chhabra_dhiraj.to_do.database.asDomainModel
import io.github.chhabra_dhiraj.to_do.domain.ToDo
import io.github.chhabra_dhiraj.to_do.domain.asDatabaseModel

class ToDoRepository(private val toDoDao: ToDoDao) {

    val toDos: LiveData<List<ToDo>> = Transformations.map(toDoDao.getToDos()) {
        it.asDomainModel()
    }

    suspend fun insertToDo(newToDoText: String) {
        toDoDao.insertToDo(DatabaseToDo(0, newToDoText, false))
    }

    suspend fun updateCompleted(completed: Boolean, toDoId: Int) {
        toDoDao.updateToDoCompletionStatusById(completed, toDoId)
    }

    suspend fun deleteToDo(toDo: ToDo) {
        toDoDao.deleteToDo(toDo.asDatabaseModel())
    }

}