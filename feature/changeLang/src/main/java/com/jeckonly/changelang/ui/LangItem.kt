package com.jeckonly.changelang.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.noIndicationClickable

@Composable
fun LangItem(
    selectedLang: String,
    langCode: String,
    langText: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.noIndicationClickable {
            onClick(langCode)
        },
        shape = RoundedCornerShape(20),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = if (selectedLang == langText) 1.dp else 0.dp,
        border = if (selectedLang == langText) BorderStroke(
            2.dp,
            MaterialTheme.colorScheme.primary
        ) else BorderStroke(0.dp, Color.Transparent)
    ) {
        Row(modifier = Mdf.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(contentAlignment = Alignment.Center) {
                RadioButton(
                    selected = selectedLang == langText,
                    onClick = null,
                    enabled = false,
                    colors = RadioButtonDefaults.colors(
                        disabledSelectedColor = MaterialTheme.colorScheme.primary,
                        disabledUnselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 25.dp)
                )
            }
            Text(
                text = langText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End,
                modifier = Mdf
                    .weight(1f)
            )
            Spacer(modifier = Mdf.width(20.dp))
        }
    }
}