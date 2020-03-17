package com.example.weatherapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class DayViewModel (application: Application) : AndroidViewModel(application) {

    private var daysDao : DayDao

    init {
        daysDao = DayDao.getInstance(this.getApplication())
    }

    fun addCityDays(city : String) {
        daysDao.addDays(city)
    }

    fun getCityDays(): MutableLiveData<List<DTemp>> {
        return daysDao.getDays()
    }
}
