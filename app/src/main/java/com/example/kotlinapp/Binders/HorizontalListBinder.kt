package com.example.kotlinapp.Binders

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.main.Adapter
import com.example.kotlinapp.R
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HorizontalListBinder(private val listener: Adapter.PictureAdapterListener) :
    ItemBinder<HorizontalList, HorizontalListBinder.ViewHolder>() {

    override fun bindViewHolder(holder: ViewHolder, item: HorizontalList) {
        val layoutManager = LinearLayoutManager(holder.itemView.context ,LinearLayoutManager.HORIZONTAL ,false)
        holder.recyclerView.layoutManager = layoutManager
        val adapter = HorizontalPictureAdapter(listener)
        holder.recyclerView.adapter = adapter
        adapter.submitList(item.pictures)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.recycler))
    }

    override fun canBindData(item: Any?): Boolean {
        return item is HorizontalList
    }

    class ViewHolder(itemView: View) : ItemViewHolder<HorizontalList>(itemView) {
        val recyclerView : RecyclerView = itemView.findViewById(R.id.rv)
    }
}
