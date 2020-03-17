package com.example.weatherapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CityViewModel (application: Application) : AndroidViewModel(application) {

    private var cityDao : CityDao

    init {
        cityDao = CityDao.getInstance(this.getApplication())
    }

    fun addCities() {
        cityDao.addCities()
    }

    fun getCities(): MutableLiveData<List<DCity>> {
        return cityDao.getCities()
    }
}