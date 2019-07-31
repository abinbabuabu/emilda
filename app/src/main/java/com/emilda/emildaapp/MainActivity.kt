package com.emilda.emildaapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.emilda.emildaapp.Dataclass.PrintData
import com.emilda.emildaapp.Interfaces.APIService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val BASE_URL = "https://api.printnode.com"
//        val CONTENT_TYPE = "pdf_uri"
//
//        val USERNAME = "xAckyuoEzl72VkDUvceYylNL_n0A3GR26G6d96aU_n4"
//        val HEADER_NAME = "Content-Type"
//        val HEADER_VALUE = "application/json"
//
//
//
//
//        val interceptor = Interceptor { chain ->
//            val url = chain.request().url().newBuilder().username(USERNAME).build()
//            val request = chain.request()
//                .newBuilder()
//                .url(url)
//                .addHeader(HEADER_NAME, HEADER_VALUE)
//                .build()
//            chain.proceed(request)
//        }
//
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//
//        val apiClient =
//            OkHttpClient().newBuilder()
//                .addInterceptor(BasicAuthentication(USERNAME, ""))
//                .addInterceptor(logging)
//                .build()
//
//
//        val builder = Retrofit.Builder()
//            .client(apiClient)
//            .baseUrl(BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
//
//
//        val retrofit = builder.build()
//
//        val client = retrofit.create(APIService::class.java)
//        //val call = client.print(printData)
//
//      //  call.enqueue(object : Callback<Int> {
//            override fun onFailure(call: Call<Int>, t: Throwable) {
//                Log.d("xy", t.message.toString())
//            }
//
//            override fun onResponse(call: Call<Int>, response: Response<Int>) {
//                Log.d("xy", response.code().toString())
//            }
//
//        })
//
//
//    }
}
