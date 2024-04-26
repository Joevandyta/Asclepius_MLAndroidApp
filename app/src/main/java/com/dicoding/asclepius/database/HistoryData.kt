package com.dicoding.asclepius.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class HistoryData(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "result")
    var result: String? = null,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "label")
    var label: String? = null,
    ):Parcelable