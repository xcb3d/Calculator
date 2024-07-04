package com.example.calculator


import androidx.compose.ui.graphics.Color
import com.example.calculator.ui.theme.ButtonBlue
import com.example.calculator.ui.theme.ButtonPink
import com.example.calculator.ui.theme.ButtonYellow

sealed class ActionType(val symbol: String, val buttonColor: Color) {
    data class Number(val number: Int) : ActionType(number.toString(), ButtonBlue)
    data class Const(val number: String) : ActionType(number.toString(), ButtonBlue)
    data class Operator(val operator: Operators) : ActionType(operator.symbol, ButtonPink)

    object Clear : ActionType("AC", ButtonPink)
    object Delete : ActionType("←", ButtonBlue)
    object Decimal : ActionType(".", ButtonBlue)
    object Calculate : ActionType("=", ButtonYellow)
    object Percentage: ActionType("%", ButtonPink)


}
sealed class Operators (val symbol: String){
    object Addition : Operators("+")
    object Subtraction : Operators("-")
    object Multiplication : Operators("*")
    object Division : Operators("÷")
    object Power : Operators("^")

}