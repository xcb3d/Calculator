package com.example.calculator


import androidx.compose.ui.graphics.Color
import com.example.calculator.ui.theme.ButtonWhite
import com.example.calculator.ui.theme.ButtonPink
import com.example.calculator.ui.theme.ButtonYellow
import com.example.calculator.ui.theme.Orange

sealed class ActionType(val symbol: String, val buttonColor: Color,val textColor: Color) {
    data class Number(val number: Int) : ActionType(number.toString(), ButtonWhite, Color.Black)
    data class Const(val number: String) : ActionType(number.toString(), ButtonWhite, Color.Black)
    data class Operator(val operator: Operators) : ActionType(operator.symbol, ButtonWhite,Orange)

    object Clear : ActionType("AC", ButtonWhite,Orange)
    object Delete : ActionType("←", ButtonWhite,Orange)
    object Decimal : ActionType(".", ButtonWhite, Color.Black)
    object Calculate : ActionType("=", Orange, Color.White)
    object Percentage: ActionType("%", ButtonWhite,Orange)


}
sealed class Operators (val symbol: String){
    object Addition : Operators("+")
    object Subtraction : Operators("-")
    object Multiplication : Operators("*")
    object Division : Operators("÷")
    object Power : Operators("^")

}