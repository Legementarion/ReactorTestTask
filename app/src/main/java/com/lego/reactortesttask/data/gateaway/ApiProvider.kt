package com.lego.reactortesttask.data.gateaway

import com.lego.reactortesttask.BuildConfig
import com.lego.reactortesttask.utils.API_KEY
import com.lego.reactortesttask.utils.HTTP
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

interface ApiProvider {
    fun <Api> provide(apiClazz: Class<Api>): Api
}

class RetrofitApiProvider(baseUrl: String, private val locale: Locale) : ApiProvider {

    companion object {
        const val READ_TIMEOUT_SECONDS = 60L
        const val CONNECTION_TIMEOUT_SECONDS = 60L
        const val WRITE_TIMEOUT_SECONDS = 60L
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(HTTP + baseUrl)
            .client(provideOkHttpClient())
            .build()
    }

    private val cashedApi: MutableMap<String, Any> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    override fun <Api> provide(apiClazz: Class<Api>): Api {
        return cashedApi.getOrPut(apiClazz.name) {
            return@getOrPut retrofit.create(apiClazz) as Any
        } as Api
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(headerIntercept())
            .addInterceptor(apiKeyIntercept())
            .addInterceptor(HttpLoggingInterceptor().setLevel(level))
            .build()
    }


    private fun headerIntercept() = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("Accept-Language", locale.language)
            .method(original.method(), original.body())
            .build()

        chain.proceed(request)
    }

    private fun apiKeyIntercept() = Interceptor { chain ->
        val original = chain.request()

        val url = original
            .url()
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()

        chain.proceed(request)
    }


}



