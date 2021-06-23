package com.carlosjimz87.funwithflags.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

private val interceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

val LoggerInterceptorClient = OkHttpClient.Builder().addInterceptor(interceptor).build()