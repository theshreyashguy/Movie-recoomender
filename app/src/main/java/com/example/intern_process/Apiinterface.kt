package com.example.intern_app

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Apiinterface {
    @GET("/1.json")
   suspend fun getData() : Response<Apiresponse1>

}