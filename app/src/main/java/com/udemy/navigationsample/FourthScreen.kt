package com.udemy.navigationsample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udemy.navigationsample.ui.theme.NavigationSampleTheme

@Composable
fun FourthScreen(modifier: Modifier = Modifier, data: DataObject, navigateToFirstScreen: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "This is the fourth screen",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text("Welcome ${data.name}, age ${data.age}!", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = { navigateToFirstScreen() }) {
            Text("Go to First screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FourthScreenPreview() {
    NavigationSampleTheme {
        FourthScreen(Modifier.background(MaterialTheme.colorScheme.background), DataObject("Iulia", 37)) {}
    }
}
