package com.example.weatherapp.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DCity(
                val nombre: String,
                val temperatura: String
                ) : Parcelable {
}
