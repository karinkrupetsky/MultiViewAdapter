package com.example.kotlinapp.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.kotlinapp.R
import com.example.kotlinapp.viewModel.ViewModel
import kotlinx.android.synthetic.main.fragment_dialog.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FragmentDialog: DialogFragment() {

    private var id : Int?= null
    private var url : String?= null
    private var isFavorite: Boolean?= null
    private val mainViewModel by sharedViewModel<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID)
            url = it.getString(URL)
            isFavorite = it.getBoolean(FAV)
        }
    }

    companion object {
        const val TAG = "FragmentDialog"
        private const val ID = "argID"
        private const val URL = "argURL"
        private const val FAV = "argFAV"

        fun newInstance(id: Int, url: String, isFavorite: Boolean) = FragmentDialog().apply {
            arguments = Bundle().apply {
                putInt(ID, id)
                putString(URL, url)
                putBoolean(FAV, isFavorite)
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view)
            .load(url)
            .centerCrop()
            .into(dialog_photo)


        //init buttons
        val addFavBtn: ImageButton = dialog_btn_favorite
        val removeFavBtn: Button = dialog_btn_favorite_remove
        val cancelButton: ImageButton = dialog_btn_close

        if(isFavorite!!){
            addFavBtn.visibility = View.GONE
            removeFavBtn.visibility = View.VISIBLE
        }

        addFavBtn.setOnClickListener(){
            mainViewModel.addFavPhoto(id!!)
            addFavBtn.visibility = View.GONE
            removeFavBtn.visibility = View.VISIBLE
        }
        removeFavBtn.setOnClickListener(){
            mainViewModel.removeFavPhoto(id!!)
            removeFavBtn.visibility = View.GONE
            addFavBtn.visibility = View.VISIBLE
        }
        cancelButton.setOnClickListener {
            dismiss()
        }
    }
}