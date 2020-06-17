package io.github.chhabra_dhiraj.to_do.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.chhabra_dhiraj.to_do.domain.ToDo

@Entity(tableName = "to_do_table")
data class DatabaseToDo constructor(
    @PrimaryKey(autoGenerate = true)
    val toDoId: Int,
    val title: String,
    val completed: Boolean
)

fun List<DatabaseToDo>.asDomainModel(): List<ToDo> {
    return map {
        ToDo(
            toDoId = it.toDoId,
            title = it.title,
            completed = it.completed
        )
    }
}