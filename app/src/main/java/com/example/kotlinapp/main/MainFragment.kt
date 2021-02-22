package com.example.kotlinapp.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import androidx.lifecycle.Observer
import com.example.kotlinapp.Binders.HorizontalList
import com.example.kotlinapp.R
import com.example.kotlinapp.Binders.VerticalList
import com.example.kotlinapp.models.Pictures
import com.example.kotlinapp.viewModel.ViewModel

class MainFragment: Fragment(R.layout.fragment_main) , Adapter.PictureAdapterListener {

    private val mainViewModel by sharedViewModel<ViewModel>()

    private val adapter = Adapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         // instance to the recycler view i would do the changes to .

        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter

        // The ViewModel component will be created with Repository instance.
        // To get it into our Activity, let’s inject it with the by viewModel() delegate injector:

        mainViewModel.horizontalPhotos.observe(viewLifecycleOwner ,Observer<List<Pictures>>{
            val horizontalList = HorizontalList(it)
            adapter.updateList(horizontalList)
        })
        mainViewModel.verticalPhotos.observe(viewLifecycleOwner ,Observer<List<Pictures>>{
            val gridList = VerticalList(it)
            adapter.updateList(gridList)
        })

        mainViewModel.favoritePhotos.observe(viewLifecycleOwner,Observer<List<Pictures>>{
            val horizontalList = HorizontalList(it)
            adapter.updateFavoriteList(horizontalList)
        })

        //  this line will show the lists in the section
        mainViewModel.getPhotosList()
    }


    override fun onPhotoClick(pictures: Pictures) {
        super.onPhotoClick(pictures)
        FragmentDialog.newInstance(pictures.ID, pictures.url, pictures.favorite)
            .show(this.activity!!.supportFragmentManager, FragmentDialog.TAG);
    }
}

