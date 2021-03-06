package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

/*
将所有数据模型类都定义在了RealtimeResponse的内部，这样可以放置出现和其他接口的数据模型类有同名冲突的情况
 */
data class RealtimeResponse(val status:String,val result: Result){

    data class Result(val realtime:Realtime)

    data class Realtime(val skycon:String,val temperature: Float,
    @SerializedName("air_quality") val airQuality:AirQuality)

    data class AirQuality(val aqi:AQI)

    data class AQI(val chn:Float)
}