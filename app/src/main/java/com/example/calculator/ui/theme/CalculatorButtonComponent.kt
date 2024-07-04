package com.example.calculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun CalculatorButtonComponent(
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color,
    symbol: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(color)
            .clickable(onClick = onClick)
            .then(modifier),
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize(fraction = 0.8f)
                    .shadow(10.dp, MaterialTheme.shapes.medium, clip = true, spotColor = Color.Red)
                    .clip(MaterialTheme.shapes.medium)
                    .background(color),
                content = {
                    Text(text = symbol,
                        color = Color.White)
                }
            )
        })
}

@Preview(showSystemUi = true)
@Composable
private fun CalculatorButtonComponentPreview() {
    CalculatorTheme {
        CalculatorButtonComponent(modifier = Modifier.size(100.dp),color = ButtonPink, symbol = "1", onClick = {})
    }
}

//@Preview(showSystemUi = true)
@Composable
fun TestShadow(){
    Button(
        onClick = { /* TODO */ },
        modifier = Modifier
            .padding(8.dp)
            .shadow(elevation = 4.dp, ambientColor = Color.Black)
    ) {
        Text("Click Me")
    }
}
