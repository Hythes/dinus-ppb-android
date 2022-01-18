package com.hanif.lantam.bridge

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RetrofitConfig {
    private fun setConnection(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        // Start UnsafeOKHttpClient
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }
            override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory

        val unsafeOkHttpClient =  OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .addInterceptor(interceptor)
            .hostnameVerifier { _, _ -> true }.build()

        //End UnsafeOkHttpClient

        return Retrofit.Builder()
            .baseUrl("https://api-android.hanifsetyananda.my.id/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(unsafeOkHttpClient)
            .build()

    }
    val apiService = setConnection().create(ApiInterface::class.java)
}