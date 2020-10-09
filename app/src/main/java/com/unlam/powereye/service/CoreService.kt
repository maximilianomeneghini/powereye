package com.unlam.powereye.service

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.unlam.powereye.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

open class CoreService(context: Context) {
    companion object {
        const val READ_TIMEOUT = 120L
        const val CONNECT_TIMEOUT = 120L
    }

    protected var retrofit: Retrofit

    init {
        retrofit = createRetrofit()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(
            MoshiConverterFactory.create()
        ).addCallAdapterFactory(
            CoroutineCallAdapterFactory()
        ).client(createOkHttpClient()).build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor).connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).followRedirects(true).
            addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type","application/json")
                    .build()
                chain.proceed(newRequest)
            }
        return builder.build()
    }

}

