package com.example.project.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.models.Surah

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val state by viewModel.surah.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var sn by remember { mutableStateOf("") }
    var surahNumber by remember { mutableStateOf<Int?>(null) }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier.padding(0.dp),
        topBar = {
            Text(
                " Al Quran ",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().background(Color.White).padding(top = 20.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                    .background(Color.White)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp,top = 10.dp),
                    value = sn,

                    onValueChange = {
                        sn = it
                        surahNumber = sn.trim().toIntOrNull()
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            surahNumber?.let { number ->
                                if (number in 1..114) {
                                    viewModel.getSurah(number)
                                } else {
                                    Toast.makeText(context, "Invalid Number!!", Toast.LENGTH_LONG).show()
                                }
                            }
                            keyboardController?.hide()
                        }
                    )
                )
            }
            when {
                isLoading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                state != null -> {
                    AyahList(state)
                }
                errorMessage != null -> {
                    Toast.makeText(context, "Error Loading Data: $errorMessage", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
@Composable
fun AyahList(state: Surah?){
    Box(modifier = Modifier.background(Color.White).padding(bottom = 60.dp)){
        Column() {
            Text(
                "${state?.data?.name}",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF339E5F))
                    .padding(top = 15.dp),
                style = TextStyle(color = Color.White)
            )
            Text(
                "( ${state?.data?.englishNameTranslation} )",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF339E5F))
                    .padding(bottom = 10.dp),
                style = TextStyle(color = Color.White)
            )
        }
    }



    state?.data?.ayahs?.forEach { ayah ->
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                    containerColor = Color.White,
            ),
        ) {
            Text(
                text = ayah.text,
                modifier = Modifier
                    .padding(start = 10.dp),
                textAlign = TextAlign.Right,
                fontSize = 14.sp,
                lineHeight = 15.sp,


            )
        }
        Divider(modifier = Modifier
            .background(Color.White)
            .padding(15.dp))
    }
}
