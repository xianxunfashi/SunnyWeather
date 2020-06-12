package com.sunnyweather.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun getDailyWeather(lng:String,lat:String) =
        weatherService.getDailyWeather(lng,lat).await()

    suspend fun getRealtimeWeather(lng: String,lat: String) =
        weatherService.getRealtimeWeather(lng,lat).await()

    private val placeService = ServiceCreator.create(PlaceServie::class.java)//创建了一个PlaceService接口的动态处理对象

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()//定义了一个searchPlaces()函数，发起城市搜索数据请求，await()挂起函数

    private suspend fun <T> Call<T>.await(): T{
        return suspendCoroutine { continuation ->//协程技术实现Retrofit回调
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}