package cn.com.woong.readhub.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass


/**
 * ApiManager
 * Created by woong on 18/4/23.
 */
class ApiManager {
    private val mRetrofitServiceHashMap = mutableMapOf<KClass, Retrofit>()

    private val mApiRetrofit: Retrofit
    private val cachedApis = ConcurrentHashMap()

    init {
        // init okhttp 3 logger
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        // init cache
        val client = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(55, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .build()

        mApiRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        mRetrofitServiceHashMap.put(ReadhubApiService::class.java, mApiRetrofit)
    }

    fun <T> addService(clz: Class<T>) {
        mRetrofitServiceHashMap.put(clz, mApiRetrofit)
    }

    fun <T> getService(clz: Class<T>): T? {
        val obj = cachedApis.get(clz)
        if (obj != null) {
            return obj
        } else {
            val retrofit = mRetrofitServiceHashMap.get(clz)
            if (retrofit != null) {
                val service = retrofit!!.create(clz)
                cachedApis.put(clz, service)
                return service
            } else {
                return null
            }
        }
    }

    companion object {
        private val BASE_URL = "https://api.readhub.me/"
    }
}