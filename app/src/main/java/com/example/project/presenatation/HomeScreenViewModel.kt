package com.example.project.presentation

import androidx.compose.runtime.mutableStateOf
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun getSurah(surahNumber: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = SurahService.getInstance()// API call here
                _surah.value = response.getSurah(surahNumber)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}

