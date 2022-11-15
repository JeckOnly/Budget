package com.jeckonly.designsystem.composable.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.designsystem.R

@Composable
fun BgtNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    id: Int,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier.size(24.dp).clickable {
              onClick()
        },
        tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewBgtNavigationBarItem() {
    BgtNavigationBarItem(selected = false, onClick = {  }, id = R.drawable.home)
}