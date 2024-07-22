package com.jess.nbcamp.compose4.mvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MvvmActivity : ComponentActivity() {

    private val viewModel: MvvmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

        viewModel.requestBoard()
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(lifecycle)
                .collectLatest { state ->
                    Log.d("jess", state.toString())
                }
        }
    }
}