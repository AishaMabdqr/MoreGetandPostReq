package com.example.moregetandpostreq

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @POST("/test/")
    fun addNames (@Body ItemsData : Names) : Call<Names>

    @GET("/test/")
    fun getNames() : Call<List<GetNames.Users>>

}