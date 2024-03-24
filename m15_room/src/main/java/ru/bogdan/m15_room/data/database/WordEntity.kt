package ru.bogdan.m15_room.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey
    @ColumnInfo("word")
    val word: String,
    @ColumnInfo("counter")
    var counter: Int = 1
)