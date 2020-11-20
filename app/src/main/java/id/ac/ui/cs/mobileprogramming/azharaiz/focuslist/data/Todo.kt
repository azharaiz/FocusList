package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val status: Boolean,
    val duration: Int?,
    val date: Date?
) : Parcelable