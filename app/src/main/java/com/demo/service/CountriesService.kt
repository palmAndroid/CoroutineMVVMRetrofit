package com.demo.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountriesService {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    fun getCountryService() : CountriesAPI  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesAPI::class.java)

}