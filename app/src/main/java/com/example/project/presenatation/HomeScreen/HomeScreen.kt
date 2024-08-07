package com.example.project.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.Utils.fontFamily1
import com.example.project.presenatation.HomeScreen.HomeScreenViewModel
import com.example.project.presenatation.components.AyahList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {

    val state by viewModel.surah.collectAsState()
    val state2 by viewModel.surah2.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val ErrorMessage by viewModel.errorMessage.collectAsState()
    var errorMessage by remember{ mutableStateOf(ErrorMessage) }

    var sn by remember { mutableStateOf("") }
    var surahNumber by remember { mutableStateOf<Int?>(null) }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.padding(0.dp),
        topBar = {
            Text(
                "Al Quran",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = 50.dp, bottom = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontFamily = fontFamily1
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxHeight()
                .background(Color.White)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            Box(
                modifier = Modifier.padding(bottom = 10.dp)
                    .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp))
                    .background(Color(0x2FC5C2C2)),

            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp,end = 10.dp),
                    value = sn,
                    label = {Text("Surah Number ")},
                    onValueChange = {
                        sn = it
                        surahNumber = sn.trim().toIntOrNull()
                        Log.d("test1", "$surahNumber")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            surahNumber?.let {
                                if (it in 1..114) {
                                    viewModel.getSurah(surahNumber!!)
                                } else {
                                    Toast.makeText(context, "Invalid Number!!", Toast.LENGTH_LONG).show()
                                    surahNumber = null
                                }
                            } ?: run {
                                Toast.makeText(context, "Invalid Number!!", Toast.LENGTH_LONG).show()
                                surahNumber = null
                            }
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            surahNumber?.let {
                                if (it in 1..114) {
                                    viewModel.getSurah(surahNumber!!)
                                } else {
                                    Toast.makeText(context, "Invalid Number!!", Toast.LENGTH_LONG).show()
                                    surahNumber = null
                                }
                            } ?: run {
                                Toast.makeText(context, "Invalid Number!!", Toast.LENGTH_LONG).show()
                                surahNumber = null
                            }
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }) {
                            Icon(
                                modifier = Modifier,
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search",
                                tint = Color.Black
                            )
                        }
                    }
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().background(Color.White)
            ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(color = Color(0xFF339E5F), modifier = Modifier.padding(top = 50.dp))
                }
                state != null -> {
                    AyahList(state,state2)
                }
                errorMessage != null -> {
                    Toast.makeText(context, "Error Loading Data: $errorMessage", Toast.LENGTH_LONG).show()
                    Log.d("SAYAPA", "$errorMessage")
                    errorMessage = null
                }
            }
        }
        }
    }
}