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

    var surah: Surah by mutableStateOf(
        Surah(
            code = 0,
            data = Data(
                ayahs = emptyList(),
                edition = Edition("","","","","","",""),
                englishName = "",
                englishNameTranslation = "",
                name = "",
                number = 0,
                numberOfAyahs = 0,
                revelationType = ""
            ),
            status = ""
        )
    )
    var errorMessage: String by mutableStateOf("")
    fun getSurah(surahNumber : Int){
        viewModelScope.launch {
            val apiService = SurahService.getInstance()
            try{
                val surahData = apiService.getSurah(surahNumber)
                surah = surahData
            }catch(e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}