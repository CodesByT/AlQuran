package com.example.project.network

import android.icu.util.TimeUnit
import com.example.project.models.Surah
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SurahService{

    @GET("{surahNumber}/urdu.asad")
    suspend fun getSurah(
        @Path("surahNumber") number: Int,
    ):Surah

//    @GET("en.asad")
//    suspend fun getSurah():Surah


    companion object{
        private const val BASE_URL = "https://api.alquran.cloud/v1/surah/"
        private var apiService: SurahService? = null

        fun getInstance(): SurahService{
            if (apiService == null){

                apiService = Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SurahService::class.java)

            }
            return apiService!!
        }
    }
}
