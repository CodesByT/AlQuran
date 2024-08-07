package com.example.project.presenatation.HomeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.Surah
import com.example.project.network.SurahService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val _surah = MutableStateFlow<Surah?>(null)
    val surah: StateFlow<Surah?> = _surah

    private val _surah2 = MutableStateFlow<Surah?>(null)
    val surah2: StateFlow<Surah?> = _surah2

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun getSurah(surahNumber: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _surah.value = null
            _errorMessage.value = null
            try {
                val response = SurahService.getInstance()// API call here
                _surah.value = response.getSurah(surahNumber)
                _surah2.value = response.getSurahWithEnglishTranslation(surahNumber)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

}

