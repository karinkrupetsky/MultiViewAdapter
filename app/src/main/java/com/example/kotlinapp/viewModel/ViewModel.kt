package com.example.kotlinapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.models.Pictures
import com.example.kotlinapp.repository.ImageListRepository

class ViewModel(private val repo : ImageListRepository) : ViewModel()  {
    val verticalPhotos = MutableLiveData<List<Pictures>>()
    val horizontalPhotos = MutableLiveData<List<Pictures>>()
    val favoritePhotos = MutableLiveData<List<Pictures>>()


    fun getPhotosList(){
        if (verticalPhotos.value.isNullOrEmpty())
            verticalPhotos.postValue(repo.getPhotos())

        if (horizontalPhotos.value.isNullOrEmpty())
            horizontalPhotos.postValue(repo.getPhotos())
    }

    fun addFavPhoto(id:Int){
        repo.addFavPhoto(id)
        favoritePhotos.postValue(repo.favoritePhotos)
    }

    fun removeFavPhoto(id:Int){
        repo.removeFavPhoto(id)
        favoritePhotos.postValue(repo.favoritePhotos)
    }
}
