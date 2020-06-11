package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
//全获取Context的方式
class SunnyWeatherApplication : Application() {

    companion object{
        const val TOKEN = "OARU9re29XYfZNg4"//令牌值放在这儿，方便之后的获取
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}