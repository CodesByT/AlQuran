package com.example.project.presenatation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.models.Surah


@Composable
fun SurahName(state: Surah?) {
    Box(modifier = Modifier.background(Color.White).padding(bottom = 60.dp)){
        Column() {
            Text(
                "${state?.data?.name}",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF339E5F))
                    .padding(top = 0.dp),
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
}
