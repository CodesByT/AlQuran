package com.example.project.presenatation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.project.models.Surah
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.EnumSet.range

@Composable
fun HomeScreen( vm: HomeScreenViewModel){

    var sn by remember { mutableStateOf("") }
    var surahNumber by remember { mutableIntStateOf(0) }
    val surah by remember { mutableStateOf(vm.surah) }
    val c = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
      modifier = Modifier.padding(5.dp),
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                value = sn,
                onValueChange = {
                    sn = it
                    surahNumber = sn.toInt()
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // CALL THE API TO
                        if (surahNumber in 1..114){
                            vm.getSurah(surahNumber = surahNumber )
                        }else{
                            Toast.makeText(c,"Invalid Number!!",Toast.LENGTH_LONG).show()
                        }
                        keyboardController?.hide()

                    }
                )
            )
            Text("Surah ${surah.data.englishName}")
            surah.data.ayahs.forEach{ ayah ->
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = ayah.text.toString(),
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }
    }
}