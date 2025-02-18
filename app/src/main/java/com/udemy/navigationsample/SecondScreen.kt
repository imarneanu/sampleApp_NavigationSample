package com.udemy.navigationsample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udemy.navigationsample.ui.theme.NavigationSampleTheme

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    name: String,
    age: Int,
    navigateToThirdScreen: (String, Int) -> Unit,
    navigateToFourthScreen: (DataObject) -> Unit,
) {
    var dataAge by remember { mutableIntStateOf(age) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "This is the second screen",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text("Welcome $name!", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = dataAge.toString(),
            onValueChange = { dataAge = it.toIntOrNull() ?: 0 },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { navigateToThirdScreen(name, dataAge) }) {
            Text("Go to Third screen")
        }
        Button(onClick = { navigateToFourthScreen(DataObject(name, dataAge)) }) {
            Text("Go to Fourth screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    NavigationSampleTheme {
        SecondScreen(
            Modifier.background(MaterialTheme.colorScheme.background),
            "Iulia",
            37,
            navigateToThirdScreen = { _, _ -> },
            navigateToFourthScreen = { },
        )
    }
}
