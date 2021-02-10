package com.demo.service

import com.demo.entity.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountriesAPI {
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries() : Response<List<Country>>
}