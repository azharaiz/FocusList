package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "reward_table")
data class Reward(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String
) : Parcelable