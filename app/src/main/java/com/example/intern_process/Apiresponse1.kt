package com.example.intern_app

import com.google.gson.annotations.SerializedName


data class Apiresponse1(
    @SerializedName("Movie List") val movie_list: List<Movie>
)