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

    class CityDao private constructor(var context: Context) {

        private val cities = MutableLiveData<List<DCity>>()
        private val citiesList = mutableListOf<DCity>()

        private var queue: RequestQueue

        init {
            queue = VolleySingleton.getInstance(context).requestQueue
        }

        companion object {
            @Volatile
            private var INSTANCE: CityDao? = null

            fun getInstance(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: CityDao(context).also { INSTANCE = it }
                }
        }

        fun addCities() {
            VolleySingleton.getInstance(context).addToRequestQueue(getJsonObject())
        }

        fun getCities() = cities

        fun getJsonObject(): JsonObjectRequest {
            val apiKey = "9bef5f86715e6e9f366b795066a4e58b"
            val url =
                "https://api.openweathermap.org/data/2.5//group?id=3689147,3688689,3688465,3687925,3687238,3685533,3674962,3672486,3668605,3666304&units=metric&appid=$apiKey"

            return JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response -> parseObjectG(response) },
                Response.ErrorListener { Log.d("Error", "Rip") }
            )
        }

        private fun parseObjectG(response: JSONObject) {
            var list = CityFields.getCities(response)

            citiesList.clear()
            for (i in 0 until list.size) {
                val city = list[i]
                citiesList.add(city)
            }

            cities.value = citiesList
        }
    }
