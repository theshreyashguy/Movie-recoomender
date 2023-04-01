package com.example.intern_process.ui.db

import androidx.room.*

@Dao
interface StudentDao {
    @Query("SELECT * FROM student_table")
    fun getAll(): List<Student>

    /* @Query("SELECT * FROM student_table WHERE uid IN (:userIds)")
     fun loadAllByIds(userIds: IntArray): List<Student>*/



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student) : Void

    @Query("DELETE FROM student_table")
    suspend fun deleteAll()
}