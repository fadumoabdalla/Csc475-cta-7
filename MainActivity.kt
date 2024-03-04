package com.example.unitconversion

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Unit Conversion App") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        }
    ) { innerPadding ->
        UnitConversionApp(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun UnitConversionApp(modifier: Modifier = Modifier) {
    var tempText by remember { mutableStateOf("") }
    var tempResult by remember { mutableStateOf("") }
    var weightText by remember { mutableStateOf("") }
    var weightResult by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        // Temperature Conversion
        TextField(
            value = tempText,
            onValueChange = { tempText = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter temperature in Celsius") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            tempResult = convertTemperature(
                tempText.toDoubleOrNull() ?: 0.0,
                "Celsius",
                "Fahrenheit"
            ).toString() + " Fahrenheit"
        }) {
            Text("Convert Temperature")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Temperature Result: $tempResult")

        Spacer(modifier = Modifier.height(16.dp))

        // Weight Conversion
        TextField(
            value = weightText,
            onValueChange = { weightText = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter weight in Kilograms") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            weightResult = convertWeight(
                weightText.toDoubleOrNull() ?: 0.0,
                "Kilograms",
                "Pounds"
            ).toString() + " Pounds"
        }) {
            Text("Convert Weight")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Weight Result: $weightResult")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        MainScreen()
    }
}

fun convertTemperature(value: Double, fromUnit: String, toUnit: String): Double {
    // Temperature conversion logic
    return when (fromUnit to toUnit) {
        "Celsius" to "Fahrenheit" -> (value * 9/5) + 32
        "Fahrenheit" to "Celsius" -> (value - 32) * 5/9
        else -> value
    }
}

fun convertWeight(value: Double, fromUnit: String, toUnit: String): Double {
    // Weight conversion logic
    return when (fromUnit to toUnit) {
        "Kilograms" to "Pounds" -> value * 2.20462
        "Pounds" to "Kilograms" -> value / 2.20462
        else -> value
    }
}




