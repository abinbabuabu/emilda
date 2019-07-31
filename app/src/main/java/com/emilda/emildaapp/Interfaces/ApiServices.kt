package com.emilda.emildaapp.Interfaces

import com.emilda.emildaapp.Dataclass.PrintData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("printjobs")
    fun print(@Body printData: PrintData):Call<Int>
}