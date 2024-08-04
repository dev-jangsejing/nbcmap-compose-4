package com.jess.nbcamp.compose4.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class StatelessActivity : ComponentActivity() {

    private val viewModel: StatelessViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StatelessScreen(
                viewModel = viewModel
            )
        }
    }
}


@Composable
private fun StatelessScreen(
    viewModel: StatelessViewModel,
    modifier: Modifier = Modifier,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NameTextLabel(
                state = state,
                onValueChange = {
                    viewModel.onValueChange(it)
                }
            )
        }
    }
}

@Composable
private fun NameTextLabel(
    state: StatelessUiState,
    onValueChange: ((String) -> Unit),
) {
    if (state.name.isNotEmpty()) {
        Text(
            text = "Hello, ${state.name}!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }

    NameTextField(
        name = state.name,
        onValueChange = {
            onValueChange.invoke(it)
        },
    )
}

@Composable
private fun NameTextField(
    name: String,
    onValueChange: ((String) -> Unit),
) {
    OutlinedTextField(
        value = name,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text("Name")
        },
    )
}