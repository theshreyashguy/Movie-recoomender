package com.example.intern_process.ui.db

import android.app.Application

class Dataapplication1: Application() {

    val db by lazy {
        AppDatabase.getDatabase(this)
    }
}