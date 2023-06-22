package com.example.proffera.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "procurement")
@Parcelize
data class ProcurementEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "projectName")
    var projectName: String,

    @ColumnInfo(name = "winnerVendor")
    var winnerVendor: String,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "projectCost")
    var projectCost: String,

    @ColumnInfo(name = "projectDescription")
    var projectDescription: String,

    @ColumnInfo(name = "projectStatus")
    var projectStatus: String,

    @ColumnInfo(name = "projectDuration")
    var projectDuration: String,

    @ColumnInfo(name = "is_bookmarked")
    var isBookmarked: Boolean = false
): Parcelable
