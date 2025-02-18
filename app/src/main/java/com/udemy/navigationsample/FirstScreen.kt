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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udemy.navigationsample.ui.theme.NavigationSampleTheme

@Composable
fun FirstScreen(modifier: Modifier = Modifier, navigateToSecondScreen: (String, Int, String?) -> Unit) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the first screen", fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = age.toString(),
            onValueChange = { age = it.toIntOrNull() ?: 0 },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { navigateToSecondScreen(name, age, null) }) {
            Text("Go to Second screen")
        }
    }
}

@PreviewLightDark
@Composable
fun FirstScreenPreview() {
    NavigationSampleTheme {
        FirstScreen(Modifier.background(MaterialTheme.colorScheme.background), { _, _, _ -> })
    }
}
