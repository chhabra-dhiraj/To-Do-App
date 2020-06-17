package io.github.chhabra_dhiraj.to_do.domain

import io.github.chhabra_dhiraj.to_do.database.DatabaseToDo

data class ToDo(
    val toDoId: Int,
    val title: String,
    var completed: Boolean
)

fun ToDo.asDatabaseModel(): DatabaseToDo {
    return DatabaseToDo(
        toDoId = toDoId,
        title = title,
        completed = completed
    )
}