package com.example.weatherapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DTemp(
            val dia: String,
            val temperatura: String
            ) : Parcelable {

}