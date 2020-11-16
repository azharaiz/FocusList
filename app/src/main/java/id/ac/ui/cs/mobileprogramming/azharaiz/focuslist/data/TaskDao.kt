package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE uid = :taskId")
    fun getOneTask(taskId: IntArray): Task

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}