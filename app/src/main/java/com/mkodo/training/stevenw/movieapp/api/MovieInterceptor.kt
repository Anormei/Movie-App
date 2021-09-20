package com.mkodo.training.stevenw.movieapp.api

import okhttp3.Interceptor
import okhttp3.Response

class MovieInterceptor(private val apiKey: String, private val language: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalHttpUrl = request.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("language", language)
            .build()

        val requestBuilder = request.newBuilder().url(url)
        val newRequest = requestBuilder
            .build();

        return chain.proceed(newRequest)
    }
}