package io.github.chhabra_dhiraj.to_do.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.chhabra_dhiraj.to_do.database.getDatabase
import io.github.chhabra_dhiraj.to_do.domain.ToDo
import io.github.chhabra_dhiraj.to_do.repository.ToDoRepository
import kotlinx.coroutines.*

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val repository: ToDoRepository

    val toDos: LiveData<List<ToDo>>

    init {
        val toDoDao = getDatabase(application).toDoDao
        repository = ToDoRepository(toDoDao)
        toDos = repository.toDos
    }

    fun insertToDo(newToDoText: String) = viewModelScope.launch {
        repository.insertToDo(newToDoText)
    }

    fun updateCompleted(completed: Boolean, toDoId: Int) = viewModelScope.launch {
        repository.updateCompleted(completed, toDoId)
    }

    fun deleteToDo(toDo: ToDo) = viewModelScope.launch {
        repository.deleteToDo(toDo)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}