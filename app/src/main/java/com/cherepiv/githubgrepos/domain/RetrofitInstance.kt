package com.cherepiv.githubgrepos.domain

import com.cherepiv.gighubgrepos.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.github.com/"

    val apiClient: ApiClient = getRetrofit().create(ApiClient::class.java)

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private fun getOkHttpClient()=  OkHttpClient.Builder()

    private fun getRetrofit() : Retrofit {
        if (retrofit == null && BuildConfig.DEBUG) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient().addInterceptor(getLoggingInterceptor()).build())
                    .build()
        } else if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}
