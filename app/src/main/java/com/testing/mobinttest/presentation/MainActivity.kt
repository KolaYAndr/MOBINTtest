package com.testing.mobinttest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.testing.mobinttest.presentation.navigation.Navigation
import com.testing.mobinttest.presentation.theme.MOBINTtestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOBINTtestTheme {
                Navigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MOBINTtestTheme {
        Navigation()
    }
}