package com.jess.nbcamp.compose4.user.signin

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.jess.nbcamp.compose4.user.signup.SignUpActivity
import com.jess.nbcamp.compose4.user.user.UserActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // ... data 처리
        }
    }

    LaunchedEffect(viewModel.event) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.event.collectLatest { event ->
                    when (event) {
                        is SignInEvent.NeedInputElement -> {
                            Toast.makeText(
                                context,
                                context.getText(R.string.sign_in_need_input_element),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is SignInEvent.MoveUserScreen -> {
                            context.startActivity(
                                UserActivity.newIntent(context)
                            )
                        }

                        is SignInEvent.NotFoundUser -> {
                            Toast.makeText(
                                context,
                                context.getText(R.string.sign_in_not_found_user),
                                Toast.LENGTH_SHORT
                            ).show()
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
                label = stringResource(id = R.string.sign_in_id),
                input = state.id,
                onValueChange = {
                    viewModel.onChangedId(it)
                },
            )

            Spacer(modifier = Modifier.height(8.dp))

            Input(
                label = stringResource(id = R.string.sign_in_password),
                input = state.password,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    viewModel.onChangedPassword(it)
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                text = stringResource(id = R.string.sign_in_login_in),
            ) {
                viewModel.onClickLogin()
            }

            ActionButton(
                text = stringResource(id = R.string.sign_in_sign_up),
            ) {
                launcher.launch(
                    SignUpActivity.newIntent(context)
                )
            }
        }
    }
}

@Composable
private fun Input(
    label: String,
    input: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
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