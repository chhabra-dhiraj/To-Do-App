package io.github.chhabra_dhiraj.to_do.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
    @androidx.room.Query("select * from to_do_table")
    fun getToDos(): LiveData<List<DatabaseToDo>>

    @Insert
    suspend fun insertToDo(databaseToDo: DatabaseToDo)

    @Query("update to_do_table set completed=:completed where toDoId=:toDoId")
    suspend fun updateToDoCompletionStatusById(completed: Boolean, toDoId: Int)

    @Delete
    suspend fun deleteToDo(databaseToDo: DatabaseToDo)
}

@Database(entities = [DatabaseToDo::class], version = 1, exportSchema = false)
abstract class ToDosDatabase : RoomDatabase() {
    abstract val toDoDao: ToDoDao
}

private lateinit var INSTANCE: ToDosDatabase

fun getDatabase(context: Context): ToDosDatabase {
    synchronized(ToDosDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ToDosDatabase::class.java,
                "to_do_database"
            ).build()
        }
    }
    return INSTANCE
}