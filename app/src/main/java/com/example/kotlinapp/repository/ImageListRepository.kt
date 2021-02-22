package com.example.kotlinapp.repository

import com.example.kotlinapp.models.Pictures

class ImageListRepository (private var imageCount : Int = 0) {

    var favoritePhotos = mutableListOf<Pictures>()


    var allPhotosList = mutableListOf<Pictures>()

    fun getPhotos(): List<Pictures> {
        val arraySize = imageCount + 100
        val photos = mutableListOf<Pictures>()
        for (i in imageCount until arraySize) {
            val photo = Pictures(i, "https://picsum.photos/900?temp=$i")
            imageCount++
            allPhotosList.add(photo)
            photos.add(photo)
        }
        return photos
    }


    fun addFavPhoto(id: Int){
        val photo = allPhotosList.find{it.ID == id}
        photo!!.favorite = true
        favoritePhotos.add(photo)
    }
    fun removeFavPhoto(id:Int){
        val photo = favoritePhotos.find{it.ID == id}
        photo!!.favorite = false
        favoritePhotos.remove(photo)
    }



}

