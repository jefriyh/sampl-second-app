package com.sciencekom.mysecondapp.helper

import android.content.Context
import android.content.SharedPreferences

class PrefHelper(context:Context) {
    val PREF_NAME = "USER_ACCOUNT"
    var pref : SharedPreferences
    val editor:SharedPreferences.Editor

    init{
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    fun set(key:String, value: String){
        editor.putString(key, value)
        editor.apply()
    }

    fun set(key:String, value: Boolean){
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getString(key: String):String?{
        return pref.getString(key,"")
    }

    fun getBoolean(key: String):Boolean{
        return pref.getBoolean(key, false)
    }

    fun clear(){
        editor.clear().apply()
    }

}