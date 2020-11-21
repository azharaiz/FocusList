package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Insert
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTodo()

    @Query("SELECT * FROM todo_table WHERE status=0")
    fun readAllTodos(): LiveData<List<Todo>>
}