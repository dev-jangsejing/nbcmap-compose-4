package com.jess.nbcamp.compose4.user.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.jess.nbcamp.compose4.user.signup.SignUpActivity

class UserActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, UserActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
