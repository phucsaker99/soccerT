package com.example.soccert.di

import com.example.soccert.BuildConfig
import com.example.soccert.service.AlarmManagerService
import com.example.soccert.utils.ApiConfig
import com.example.soccert.utils.KoinConfig.SEARCH_CLIENT_NAME
import com.example.soccert.utils.KoinConfig.SEARCH_RETROFIT_NAME
import com.example.soccert.utils.KoinConfig.SOCCER_CLIENT_NAME
import com.example.soccert.utils.KoinConfig.SOCCER_RETROFIT_NAME
import okhttp3.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val netWorkModule = module {

    fun initHttpSoccerClient(): OkHttpClient {
        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder().url(
                    original.url.newBuilder()
                        .addQueryParameter(ApiConfig.API_SOCCER_KEY, BuildConfig.API_SOCCER_KEY)
                        .build()
                ).build()
                return chain.proceed(request)
            }
        }
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun initSoccerRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL_SOCCER)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }

    fun initHttpSearchClient(): OkHttpClient {
        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder().url(
                    original.url.newBuilder()
                        .addQueryParameter(
                            ApiConfig.API_SEARCH_NEWS_KEY,
                            BuildConfig.API_SEARCH_KEY
                        )
                        .build()
                ).build()
                return chain.proceed(request)
            }
        }
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun initSearchRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL_SEARCH_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single(named(SOCCER_CLIENT_NAME)) { initHttpSoccerClient() }
    single(named(SOCCER_RETROFIT_NAME)) { initSoccerRetrofit(get(named(SOCCER_CLIENT_NAME))) }
    single(named(SEARCH_CLIENT_NAME)) { initHttpSearchClient() }
    single(named(SEARCH_RETROFIT_NAME)) { initSearchRetrofit(get(named(SEARCH_CLIENT_NAME))) }
}
