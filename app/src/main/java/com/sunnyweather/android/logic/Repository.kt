package com.sunnyweather.android.logic

import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.RuntimeException

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO){//自动构建并返回一个LiveDa对象
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure<List<Place>>(RuntimeException("Response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)//将包装的结果发射出去，类似于调用LiveData的setValue()方法来通知数据变化。
    }
}