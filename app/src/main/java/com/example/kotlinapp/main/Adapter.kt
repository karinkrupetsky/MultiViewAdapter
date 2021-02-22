package com.example.kotlinapp.main

import com.example.kotlinapp.Binders.HeaderBinder
import com.example.kotlinapp.Binders.HorizontalListBinder
import com.example.kotlinapp.Binders.VerticalListBinder
import com.example.kotlinapp.Binders.HorizontalList
import com.example.kotlinapp.Binders.VerticalList
import com.example.kotlinapp.models.Pictures
import mva2.adapter.HeaderSection
import mva2.adapter.ItemSection
import mva2.adapter.MultiViewAdapter

class Adapter(
    photoListener: PictureAdapterListener)
    : MultiViewAdapter() {

    interface PictureAdapterListener {
        fun onPhotoClick(pictures: Pictures) {}
    }

    private val horizontalSection: ItemSection<HorizontalList>
    private val verticalSection: ItemSection<VerticalList>
    private val favoriteSection:ItemSection<HorizontalList>

    init {
        registerItemBinders(HeaderBinder(),  HorizontalListBinder(photoListener) , VerticalListBinder(photoListener))
        val horizontalHeaderSection = HeaderSection<String>()
        horizontalHeaderSection.header = "Horizontal List = Big Photos"

        horizontalSection = ItemSection()
        horizontalHeaderSection.addSection(horizontalSection)

        val verticalHeaderSection = HeaderSection<String>()
        verticalHeaderSection.header = "Vertical List = Small Photos"

        verticalSection = ItemSection()
        verticalHeaderSection.addSection(verticalSection)

        val favoriteHeaderSection = HeaderSection<String>()
        favoriteHeaderSection.header = "Favorite List"
        favoriteSection = ItemSection()
        favoriteHeaderSection.addSection(favoriteSection)

        addSection(horizontalHeaderSection)
        addSection(verticalHeaderSection)
        addSection(favoriteHeaderSection)
    }

    fun updateList(list:Any){
        when(list){
            is HorizontalList -> horizontalSection.setItem(list)
            is VerticalList -> verticalSection.setItem(list)
        }
    }

    fun updateFavoriteList(horizontalList: HorizontalList) {
        favoriteSection.setItem(horizontalList)
    }
}