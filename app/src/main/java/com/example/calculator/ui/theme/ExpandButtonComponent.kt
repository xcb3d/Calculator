package com.example.calculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.R

@Composable
fun ExpandButtonComponent(
    modifier: Modifier,
    onClick: () -> Unit,
    imageRes: Int
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(ButtonWhite)
            .clickable(onClick = onClick)
            .then(modifier),
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize(fraction = 0.8f)
                    .clip(MaterialTheme.shapes.medium)
                    .background(ButtonWhite),
                content = {
                    Icon(
                        painter = painterResource(imageRes),
                        contentDescription = "Expand icon",
                        tint = Orange
                    )
                }
            )
        })
}
