package com.demo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.entity.Country
import com.demo.service.CountriesService
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    var countryService  = CountriesService.getCountryService()
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception : ${throwable.localizedMessage}")
    }
    var job : Job ? = null

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
           var response =  countryService.getCountries()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    countries.value = response.body()
                }else{
                    onError(response.message())
                }
            }
        }
    }

    private fun onError(message: String) {
        countryLoadError.value = message
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}