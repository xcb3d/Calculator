package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.BackgroundColor
import com.example.calculator.ui.theme.CalculatorButtonComponent
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.ExpandButtonComponent
import com.example.calculator.ui.theme.InputDisplayComponent
import com.example.calculator.ui.theme.spacing

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state =  viewModel.viewState.collectAsState(initial = CalculatorViewModel.ViewState("0")).value
                ScreenCalculator(state) {
                    viewModel.dispatch(it)
                }
            }
        }
    }
}

@Composable
private fun ScreenCalculator(state: CalculatorViewModel.ViewState, dispatcher: (ActionType) -> Unit) {
    Surface(
        color = BackgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.lg,
                    vertical = MaterialTheme.spacing.sm,
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                InputDisplayComponent(state)
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.lg))
                CalculatorButtonGridLayout(dispatcher)
            }
        }
    }
}

@Composable
private fun CalculatorButtonGridLayout(dispatcher: (ActionType) -> Unit) {
//    val buttons = listOf(
//        "AC", "^", "%", "÷",
//        "7", "8", "9", "*",
//        "6", "5", "4", "-",
//        "3", "2", "1", "+",
//        "e", "0", ".", "="
//    )
    var isExpand by remember {
        mutableStateOf(false)
    }
    val size = if (isExpand) {
        5
    } else {
        4
    }
    val imageRes = if (isExpand) {
        R.drawable.scale_down
    } else {
        R.drawable.slace_up
    }
    val buttons = if (isExpand) {
        listOf(
            ActionType.Special("2nd"),
            ActionType.Special("deg"),
            ActionType.Special("sin"),
            ActionType.Special("cos"),
            ActionType.Special("tan"),
            ActionType.Special("^"),
            ActionType.Special("lg"),
            ActionType.Special("ln"),
            ActionType.Special("("),
            ActionType.Special(")"),
            ActionType.Special("√"),
            ActionType.Clear,
            ActionType.Delete,
            ActionType.Percentage,
            ActionType.Operator(Operators.Division),
            ActionType.Special("!"),
            ActionType.Number(7),
            ActionType.Number(8),
            ActionType.Number(9),
            ActionType.Operator(Operators.Multiplication),
            ActionType.Special(" ¹ ⁄ ˣ"),
            ActionType.Number(4),
            ActionType.Number(5),
            ActionType.Number(6),
            ActionType.Operator(Operators.Subtraction),
            ActionType.Special("π"),
            ActionType.Number(1),
            ActionType.Number(2),
            ActionType.Number(3),
            ActionType.Operator(Operators.Addition),
            ActionType.Expand,
            ActionType.Const("e"),
            ActionType.Number(0),
            ActionType.Decimal,
            ActionType.Calculate

            )
    } else {
        listOf(
            ActionType.Clear,
            ActionType.Delete,
            ActionType.Percentage,
            ActionType.Operator(Operators.Division),
            ActionType.Number(7),
            ActionType.Number(8),
            ActionType.Number(9),
            ActionType.Operator(Operators.Multiplication),
            ActionType.Number(4),
            ActionType.Number(5),
            ActionType.Number(6),
            ActionType.Operator(Operators.Subtraction),
            ActionType.Number(1),
            ActionType.Number(2),
            ActionType.Number(3),
            ActionType.Operator(Operators.Addition),
            ActionType.Expand,
            ActionType.Number(0),
            ActionType.Decimal,
            ActionType.Calculate,

            )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(size),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        content = {
        items(buttons) {
            if (it == ActionType.Expand) {
                ExpandButtonComponent(
                    modifier = Modifier.aspectRatio(1f),
                    onClick = {
                        isExpand = !isExpand
                    },
                    imageRes = imageRes
                )
            } else {
                CalculatorButtonComponent(
                    colorButton = it.buttonColor,
                    colorText = it.textColor,
                    symbol = it.symbol,
//                onClick = {},
                    modifier = Modifier.aspectRatio(1f)
                ) {
                    dispatcher(it)
                }
            }
        }
    })
}

