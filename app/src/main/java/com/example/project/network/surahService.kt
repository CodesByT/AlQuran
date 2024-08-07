package com.example.project.network


import com.example.project.models.Surah
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SurahService{

    @GET("{surahNumber}/urdu.asad")
    suspend fun getSurah(
        @Path("surahNumber") number: Int,
    ):Surah

    @GET("{surahNumber}/en.asad")
    suspend fun getSurahWithEnglishTranslation(
        @Path("surahNumber") number: Int,
    ):Surah


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
