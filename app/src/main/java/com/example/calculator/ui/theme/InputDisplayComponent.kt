package com.example.calculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.CalculatorViewModel
import java.time.format.TextStyle

@Composable
internal fun InputDisplayComponent(state: CalculatorViewModel.ViewState) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(BackgroundColor)
            .padding(
                vertical = MaterialTheme.spacing.md,
                horizontal = MaterialTheme.spacing.sm
            )
    ) {
        Text(text = state.result,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.End,
            maxLines = 1,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun InputDisplayComponentPreview() {
    CalculatorTheme {
        InputDisplayComponent(state = CalculatorViewModel.ViewState("1 + 100 = 101"))
    }
}