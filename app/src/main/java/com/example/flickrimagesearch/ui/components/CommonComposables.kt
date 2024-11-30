package com.example.flickrimagesearch.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.flickrimagesearch.ui.theme.Typography

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick, modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ButtonText(text, modifier = modifier)
    }
}

@Composable
private fun ButtonText(
    text: String,
    style: TextStyle = Typography.bodyMedium,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = style,
        modifier = modifier
    )
}

@Composable
fun CustomText(
    text: String, style: TextStyle = Typography.bodyMedium,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = style,
        modifier = modifier
    )
}

@Composable
fun ShareButton(
    text: String,
    onShare: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onShare,
        modifier = modifier.fillMaxWidth().padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = text
        )
        CustomText(text, modifier = Modifier.padding(start = 8.dp))
    }
}