package com.example.calculator
import android.icu.text.DecimalFormat
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlin.math.pow

@Suppress("IMPLICIT_CAST_TO_ANY")
class CalculatorViewModel: ViewModel() {

    private val state: MutableStateFlow<State> = MutableStateFlow(State())
    internal val viewState = state
        .map { state ->
            val num1 = state.num1.ifEmpty { "0" }
            val operator = state.operator?.symbol.orEmpty()
            val num2 = state.num2
            ViewState("$num1 $operator $num2")
        }

    private fun onOperatorClicked(operator: Operators) {
        val currentState = state.value

        if (currentState.num1.isNotEmpty() && currentState.operator == null) {
            state.value = currentState.copy(operator = operator)
        }
    }

    private fun onDeleteClicked() {
        val currentState = state.value
        if (currentState.operator == null){
            state.value = currentState.copy(num1 = currentState.num1.dropLast(1))
        } else if (currentState.num2.isEmpty()){
            state.value = currentState.copy(operator = null)
        } else {
            state.value = currentState.copy(num2 = currentState.num2.dropLast(1))
        }
    }


    private fun onDecimalClicked() {
        val currentState = state.value

        if (currentState.operator == null && !currentState.num1.contains(".") && currentState.num2.isEmpty()){
            state.value = currentState.copy(num1 = currentState.num1 + ".")
        } else if (currentState.operator != null && !currentState.num2.contains(".") && currentState.num2.isNotEmpty()){
            state.value = currentState.copy(num2 = currentState.num2 + ".")
        }
    }


    private fun onClearClicked() {
        val currentState = state.value
        state.value = currentState.copy(num1 = "0", operator = null, num2 = "")
    }


    private fun onCalculateClicked() {
        val currentState = state.value
        if (currentState.num1.isNotEmpty() && currentState.operator != null && currentState.num2.isNotEmpty()) {

            val num1 = currentState.num1.toDouble()
            val num2 = currentState.num2.toDouble()
            val result = when (currentState.operator) {
                Operators.Addition -> num1 + num2
                Operators.Subtraction -> num1 - num2
                Operators.Multiplication -> num1 * num2
                Operators.Division -> num1 / num2
                Operators.Power -> num1.pow(num2)
                else -> {}
            }
            val tempResult = result
            val formattedString = String.format("%.3f", result)
            val roundedString = formattedString.trimEnd('0').trimEnd('.')
            val replaceString = roundedString.replace(',','.')
            val finalString = if (replaceString[replaceString.length - 1] == '.') {
                replaceString.dropLast(1)
            } else {
                replaceString
            }
            state.value = currentState.copy(num1 = finalString.toString(), operator = null, num2 = "")
        }
    }

    private fun onNumberClicked(number: Int) {
        val currentState = state.value
        if (currentState.operator == null) {
            state.value = currentState.copy(num1 = currentState.num1 + number)
        } else {
            state.value = currentState.copy(num2 = currentState.num2 + number)
        }
    }

    private fun onPercentageClicked() {
        val currentState = state.value
        if (currentState.operator == null && currentState.num1.isNotEmpty()) {
            state.value = currentState.copy(num1 = (currentState.num1.toDouble() / 100).toString())
        } else if (currentState.operator != null && currentState.num2.isNotEmpty()) {
            state.value = currentState.copy(num2 = (currentState.num2.toDouble() / 100).toString())
        }
    }


    fun dispatch(action: ActionType) {
        when (action) {
            is ActionType.Number -> onNumberClicked(action.number)
            is ActionType.Operator -> onOperatorClicked(action.operator)
            is ActionType.Clear -> onClearClicked()
            is ActionType.Calculate -> onCalculateClicked()
            is ActionType.Delete -> onDeleteClicked()
            is ActionType.Decimal -> onDecimalClicked()
            is ActionType.Percentage -> onPercentageClicked()
            is ActionType.Const -> onConstClicked(action.number)
        }
    }

    private fun onConstClicked(number: String) {

    }


    internal class ViewState(val result: String)

    private data class State(
        val num1: String = "",
        val operator: Operators? = null,
        val num2: String = ""
    )
}