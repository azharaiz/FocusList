package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("SELECT * FROM todo_table")
    fun readAllTodos(): LiveData<List<Todo>>
}