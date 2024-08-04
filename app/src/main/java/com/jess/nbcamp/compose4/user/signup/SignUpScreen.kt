package com.jess.nbcamp.compose4.user.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jess.nbcamp.compose4.R

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color.Green),
                contentScale = ContentScale.Crop,
                painter = painterResource(
                    id = R.drawable.ic_launcher_foreground
                ),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Input(
                label = stringResource(id = R.string.sign_up_name),
                input = state.name,
                onValueChange = {
                    viewModel.onChangedName(it)
                },
            )

            Spacer(modifier = Modifier.height(8.dp))

            Input(
                label = stringResource(id = R.string.sign_up_id),
                input = state.id,
                onValueChange = {
                    viewModel.onChangedId(it)
                },
            )

            Spacer(modifier = Modifier.height(8.dp))

            Input(
                label = stringResource(id = R.string.sign_up_password),
                input = state.password,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    viewModel.onChangedPassword(it)
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                text = stringResource(id = R.string.sign_up_confirm),
            ) {
                onConfirm()
            }
        }
    }
}

@Composable
private fun Input(
    label: String,
    input: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = label,
        )

        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = input,
            visualTransformation = visualTransformation,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun ActionButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text)
    }
}