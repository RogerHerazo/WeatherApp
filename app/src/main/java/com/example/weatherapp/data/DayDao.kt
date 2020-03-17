package com.example.weatherapp.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.weatherapp.VolleySingleton
import org.json.JSONObject

class DayDao private constructor(var context: Context) {

    private val days = MutableLiveData<List<DTemp>>()
    private val daysList= mutableListOf<DTemp>()

    private var queue : RequestQueue

    init {
        queue = VolleySingleton.getInstance(context).requestQueue
    }

    companion object {
        @Volatile
        private var INSTANCE : DayDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DayDao(context).also {INSTANCE = it}
            }
    }

    fun addDays(city: String) {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObject(city))
    }

    fun getDays() = days

    fun getJsonObject(city: String) : JsonObjectRequest {
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=$city&units=metric&appid=9bef5f86715e6e9f366b795066a4e58b"

        return JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response -> parseObjectG(response) },
            Response.ErrorListener { Log.d("Error", "Rip") }
        )
    }

    private fun parseObjectG(response: JSONObject) {
        var list = DayFields.getCityDays(response)

        daysList.clear()
        for (i in 0 until list.size) {
            val cityDay = list[i]
            daysList.add(cityDay)
        }

        days.value = daysList
    }


}