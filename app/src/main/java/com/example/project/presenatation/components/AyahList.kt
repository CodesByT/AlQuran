package com.example.project.presenatation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.models.Surah

@Composable
fun AyahList(state: Surah?, state2: Surah?) {
    val urduAyahs = state?.data?.ayahs ?: emptyList()
    val englishAyahs = state2?.data?.ayahs ?: emptyList()

    Column {
        SurahName(state) // Display surah name from state

        for (i in urduAyahs.indices) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
            ) {
                Column {
                    Text(
                        text = "[${urduAyahs[i].manzil} : ${urduAyahs[i].numberInSurah}]",
                        modifier = Modifier.padding(start = 15.dp,bottom = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,

                        )
                    // Display Urdu Ayah if available
                    if (i < urduAyahs.size) {

                        Text(
                            text = buildAnnotatedString {
                                append(urduAyahs[i].text)
                                withStyle(style = SpanStyle(color = Color(0xFF339E5F))) {
                                    append(" ۞")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp),
                            textAlign = TextAlign.Right,
                            fontSize = 20.sp,
                            lineHeight = 30.sp,
                            style = androidx.compose.ui.text.TextStyle(
                                textDirection = TextDirection.Rtl
                            )
                        )
                    }

                    // Display English Ayah if available
                    if (i < englishAyahs.size) {
                        Text(
                            text = englishAyahs[i].text,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp)
                                .padding(top = 10.dp), // Add spacing between Urdu and English texts
                            textAlign = TextAlign.Left,
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = TextStyle(color = Color(0xFF339E5F))
                        )
                    }
                }
            }
            Divider(
                modifier = Modifier
                    .background(Color.White)
                    .padding(top = 15.dp, bottom = 15.dp)
            )
        }
    }
}
