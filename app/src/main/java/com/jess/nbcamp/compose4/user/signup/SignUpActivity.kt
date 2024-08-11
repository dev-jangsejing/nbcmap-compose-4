package com.jess.nbcamp.compose4.user.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

class SignUpActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, SignUpActivity::class.java)
    }

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignUpScreen(
                viewModel = viewModel,
                onFinishAndResultOk = {
                    setResult(RESULT_OK)
                    finish()
                }
            )
        }
    }
}