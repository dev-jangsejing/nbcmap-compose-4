package com.jess.nbcamp.compose4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jess.nbcamp.compose4.mvvm.MvvmViewModel
import com.jess.nbcamp.compose4.state.HoistingActivity
import com.jess.nbcamp.compose4.state.StatefulActivity
import com.jess.nbcamp.compose4.state.StatelessActivity
import com.jess.nbcamp.compose4.ui.theme.NbCampTheme
import com.jess.nbcamp.compose4.user.signin.SignInActivity

class MainActivity : ComponentActivity() {

    private val viewModel: MvvmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NbCampTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                ) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        Button(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        StatefulActivity::class.java
                    )
                )
            },
        ) {
            Text(text = "StatefulActivity")
        }

        Button(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        StatelessActivity::class.java
                    )
                )
            },
        ) {
            Text(text = "StatelessActivity")
        }

        Button(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        HoistingActivity::class.java
                    )
                )
            },
        ) {
            Text(text = "HoistingActivity")
        }

        Button(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        SignInActivity::class.java
                    )
                )
            },
        ) {
            Text(text = "SignInActivity")
        }
    }
}