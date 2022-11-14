package com.jeckonly.designsystem.composable.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R

@Composable
fun PlusButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable { onClick() }
            .size(68.dp),
        shape = RoundedCornerShape(25),
        color = MaterialTheme.colorScheme.primary,
        contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
        shadowElevation = 12.dp
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = null,
                modifier = Mdf
                    .size(30.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewPlusButton() {
    PlusButton(
        onClick = {},
    )
}