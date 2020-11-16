package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "task_title") val taskTitle: String,
    @ColumnInfo(name = "task_status") val taskStatus: Boolean,
    @ColumnInfo(name = "task_description") val taskDescription: String?,
    @ColumnInfo(name = "task_is_sync") val taskIsSync: Boolean
)