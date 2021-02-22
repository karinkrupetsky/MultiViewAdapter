package com.example.kotlinapp

import com.example.kotlinapp.repository.ImageListRepository
import com.example.kotlinapp.viewModel.ViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

   single { ImageListRepository() }

    viewModel { ViewModel(get()) }
        ////////////////////////////
}
