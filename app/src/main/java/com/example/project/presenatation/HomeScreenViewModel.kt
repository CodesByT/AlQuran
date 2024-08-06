package com.example.project.presenatation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.Ayah
import com.example.project.models.Data
import com.example.project.models.Edition
import com.example.project.models.Surah
import com.example.project.network.SurahService
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {


    var surah by mutableStateOf<Surah?>(null)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun getSurah(surahNumber: Int){
        viewModelScope.launch {
            isLoading = true
            val apiService = SurahService.getInstance()
            try {
                val surahData = apiService.getSurah(surahNumber)
                surah = surahData
                errorMessage = null
            }catch (e:Exception){
                errorMessage = e.message
                surah = null
            }finally {
                isLoading = false
            }

        }
    }
//    fun getSurah(surahNumber : Int){
//        viewModelScope.launch {
//            val apiService = SurahService.getInstance()
//
//            try{
//                val surahData = apiService.getSurah(surahNumber)
//                surah = surahData
//            }catch(e: Exception){
//                errorMessage = e.message.toString()
//            }
//        }
//    }
}