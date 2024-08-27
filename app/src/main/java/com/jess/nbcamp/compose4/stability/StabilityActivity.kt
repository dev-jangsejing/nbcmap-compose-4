package com.jess.nbcamp.compose4.stability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jess.nbcamp.compose4.ui.theme.NbCampTheme

class StabilityActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NbCampTheme {
                StabilityScreen()
            }
        }
    }
}

@Composable
private fun StabilityScreen() {
    var checked by remember { mutableStateOf(false) }
    Column {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )

        ContactsList(
//            StabilityUiState(
//                isLoading = false,
//                contacts = listOf("hello")
//            )
            isLoading = false,
            contacts = listOf("hello")
        )
    }
}

@Composable
private fun ContactsList(
//    state: StabilityUiState,
    isLoading: Boolean,
    contacts: List<String>,
    modifier: Modifier = Modifier,
) {

    Box(modifier.fillMaxSize()) {
        Text(
//            text = state.contacts.toString()
            text = contacts.toString()
        )
    }

}