package com.steps.tracker.machine.analyzer.databases.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.steps.tracker.machine.analyzer.databases.Converters

import java.util.Date

@Entity(tableName = "Weights")
class Weights (
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null ,
    var weight : Float? = null,
    @TypeConverters(Converters::class)
    var updateTime : Date? = null
)