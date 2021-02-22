package com.example.kotlinapp.Binders
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.main.Adapter
import com.example.kotlinapp.R

import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class VerticalListBinder(private val listener: Adapter.PictureAdapterListener) : ItemBinder<VerticalList, VerticalListBinder.ViewHolder>() {


    class ViewHolder(itemView: View) : ItemViewHolder<VerticalList>(itemView) {
        val recyclerView : RecyclerView = itemView.findViewById(R.id.rv)
    }

    override fun bindViewHolder(holder: ViewHolder, item: VerticalList) {


        val layoutManager =
            GridLayoutManager(holder.itemView.context,6, GridLayoutManager.VERTICAL, false)
        holder.recyclerView.layoutManager = layoutManager
        val adapter = VerticalPhotoAdapter(listener)
        holder.recyclerView.adapter = adapter
        adapter.submitList(item.pictures)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.recycler))
    }

    override fun canBindData(item: Any?): Boolean {
        return item is VerticalList
    }
}