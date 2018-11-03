package com.example.wahyupermadi.kotlinsubmission2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        val baseURL : String = "https://www.thesportsdb.com/api/v1/json/1/"
        var retrofit: Retrofit? = null

        val client: Retrofit
            get(){
                if(retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
                return retrofit!!
            }
    }
}