package com.sciencekom.mysecondapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Customer(
    var name:String,
    var domicile:String,
    var gender:String,
    var image:Int
):Parcelable
