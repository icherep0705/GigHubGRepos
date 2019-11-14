package com.cherepiv.gighubgrepos.domain

import com.cherepiv.gighubgrepos.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance{
//    private val context: Context? = null

    companion object {

        private val client: ApiClient? = null
        private var retrofit: Retrofit? = null
        private var loggingInterceptor: HttpLoggingInterceptor? = null
        private var okHttpClient: OkHttpClient.Builder? = null
        private const val BASE_URL = "https://api.github.com/"

        //    @Override
        //    public void onCreate() {
        //        super.onCreate();
        //
        //
        //        Context context = this;
        //    }

        val apiClient: ApiClient
            get() {
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
                    retrofit!!.create(ApiClient::class.java)
                }
                return retrofit!!.create(ApiClient::class.java)
            }

        fun getLoggingInterceptor(): HttpLoggingInterceptor {
            if (loggingInterceptor == null) {
                loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor!!.setLevel(HttpLoggingInterceptor.Level.BODY)
            }

            return loggingInterceptor!!
        }

        //    public static Cache getCache(){
        //        File cacheFile = new File()
        //    }

        fun getOkHttpClient(): OkHttpClient.Builder {
            if (okHttpClient == null) {
                okHttpClient = OkHttpClient.Builder()
            }
            return okHttpClient!!
        }
    }
}
