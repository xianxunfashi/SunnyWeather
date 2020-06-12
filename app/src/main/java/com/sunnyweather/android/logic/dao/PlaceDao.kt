package com.sunnyweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.Place

object PlaceDao {

    //用于将Place对象存储到SharedPreferences文件中，这里使用了一个技巧，我们先通过GSON将Place对象转成一个JSON字符串，然后就可以用字符串存储的方式保存数据了。
    fun savePlace(place: Place){
        sharedPreferences().edit {
            putString("place",Gson().toJson(place))
        }
    }

    //先将JSON字符串从SharedPreferences文件中读取出来，然后再通过GSON将JSON字符串解析成Place对象返回。
    fun getSavedPlace():Place{
        val placeJson = sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }

    //判断是否有数据已被储存
    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = SunnyWeatherApplication.context.
            getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}