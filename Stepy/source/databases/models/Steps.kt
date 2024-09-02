package com.steps.tracker.machine.analyzer.databases.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.steps.tracker.machine.analyzer.databases.Converters
import java.util.Date

@Entity(tableName = "Steps")
class Steps(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var step: Long = 0,
    @TypeConverters(Converters::class)
    var startTime: Date,
    var activeTime: Long = 0,
    var calo: Double = 0.0,
    var distance: Double = 0.0, // km
    var isPush: Boolean = false
)

