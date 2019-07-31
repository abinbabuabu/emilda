package com.emilda.emildaapp

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthentication( username: String, password: String) : Interceptor {

    var credentials = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenReq = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(authenReq)
    }
}