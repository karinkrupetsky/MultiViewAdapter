package com.example.kotlinapp.Binders
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinapp.R
import com.example.kotlinapp.main.Adapter
import com.example.kotlinapp.models.Pictures
import kotlinx.android.synthetic.main.horizontal_view.view.*


class HorizontalPictureAdapter(private val listener: Adapter.PictureAdapterListener)
    : ListAdapter <Pictures, RecyclerView.ViewHolder>(
    Callback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.horizontal_view,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        val photo = getItem(position)
        holder.bind(photo)
        holder.photoImage.setOnClickListener{listener.onPhotoClick(photo)}
    }

    private class Callback : DiffUtil.ItemCallback<Pictures>() {

        override fun areItemsTheSame(oldItem: Pictures, newItem: Pictures): Boolean {
            return oldItem.ID == newItem.ID
        }

        override fun areContentsTheSame(oldItem: Pictures, newItem: Pictures): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoImage: ImageView = itemView.horizontal_img

        fun bind(pictures: Pictures){
            Glide.with(itemView)
                .load(pictures.url)
                .centerCrop()
                .into(photoImage)
        }
    }
}


