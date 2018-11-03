package com.example.wahyupermadi.kotlinsubmission2.api

import com.example.wahyupermadi.kotlinsubmission2.model.MatchsReponse
import com.example.wahyupermadi.kotlinsubmission2.model.DetailMatchsDetail
import com.example.wahyupermadi.kotlinsubmission2.model.TeamResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("eventsnextleague.php?id=4328")
    fun getNextMatch(): Call<MatchsReponse>

    @GET("lookupevent.php")
    fun getDetailNextMatch(@Query("id") id: String): Call<DetailMatchsDetail>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") id: String) : Call<TeamResponse>

    @GET("eventspastleague.php?id=4328")
    fun getPastMatch(): Call<MatchsReponse>
}