package com.example.intern_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object callingobj {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://task.auditflo.in").addConverterFactory(GsonConverterFactory.create()).build()
    }
    val apiinterface by lazy {
        retrofit.create(Apiinterface::class.java)

    }
    val apiinterface1 by lazy {
        retrofit.create(Apiinterface2::class.java)

    }
}