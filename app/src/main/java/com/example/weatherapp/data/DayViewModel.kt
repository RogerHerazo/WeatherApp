package com.example.weatherapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class DayViewModel (application: Application) : AndroidViewModel(application) {

    private var dayDao : DayDao

    init {
        dayDao = DayDao.getInstance(this.getApplication())
    }

    fun addCityDays(city : String) {
        dayDao.addDays(city)
    }

    fun getCityDays(): MutableLiveData<List<DTemp>> {
        return dayDao.getDays()
    }
}
