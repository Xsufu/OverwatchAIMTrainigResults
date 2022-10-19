package com.xolary.overwatchaimtrainigresults.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,
    @ColumnInfo(name = "difficulty")
    val opponentDifficulty: String,
    @ColumnInfo(name = "day")
    val numberOfDay: Int,
    @ColumnInfo(name = "class")
    val characterClass: String,
    @ColumnInfo(name = "name")
    val characterName: String,
    @ColumnInfo(name = "time")
    val spentTime: Int,
    @ColumnInfo(name = "kills")
    val kills: Int,
    @ColumnInfo(name = "accuracy")
    val accuracy: Int
)
