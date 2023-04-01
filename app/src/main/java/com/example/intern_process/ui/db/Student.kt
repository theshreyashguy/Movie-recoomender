package com.example.intern_process.ui.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val Cast: String,

    val Director: String,

    val Genres: String,

    val IMDBID: String,

    val Rating: String,

    val Runtime: String,


    val Summary: String,

    val Title: String,

    val Writers: String,
    val rollNo: String
)


