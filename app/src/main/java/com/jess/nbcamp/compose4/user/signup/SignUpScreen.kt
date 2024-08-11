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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.jess.nbcamp.compose4.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    modifier: Modifier = Modifier,
    onFinishAndResultOk: () -> Unit,
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    var isShowDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.event.collectLatest { event ->
                    when (event) {
                        is SignUpEvent.FinishAndResultOk -> {
                            onFinishAndResultOk()
                        }

                        is SignUpEvent.NeedInputElement -> {
                            isShowDialog = true
                        }
                    }
                }
            }
        }
    }

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
                viewModel.onClickSignUp()
            }
        }
    }

    if (isShowDialog) {
        NeedInputElementDialog(
            onDismissRequest = {
                isShowDialog = false
            },
            onConfirmation = {
                isShowDialog = false
            },
        )
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

@Composable
private fun NeedInputElementDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        text = {
            Text(text = stringResource(id = R.string.sign_up_need_input_element))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.confirm)
                )
            }
        },
    )
}