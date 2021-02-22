package com.example.kotlinapp.Binders
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlinapp.R
import kotlinx.android.synthetic.main.header.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HeaderBinder : ItemBinder<String, HeaderBinder.ViewHolder>() {


    class ViewHolder(itemView: View) : ItemViewHolder<String>(itemView) {
        val header: TextView = itemView.header_title
    }

    override fun bindViewHolder(holder: ViewHolder, item: String?) {
        holder.header.text = item
    }

    override fun createViewHolder(parent: ViewGroup?): ViewHolder {
        return ViewHolder(inflate(parent!!, R.layout.header))
    }

    override fun canBindData(item: Any?): Boolean {
        return item is String
    }
}
