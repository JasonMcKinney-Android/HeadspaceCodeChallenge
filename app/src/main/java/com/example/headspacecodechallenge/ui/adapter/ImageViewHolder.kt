package com.example.headspacecodechallenge.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.headspacecodechallenge.db.entites.ImageEntry
import kotlinx.android.synthetic.main.item_photo.view.*

class ImageViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    fun bindItem(image: ImageEntry) {
        //populate text
        itemView.imageAuthor.text = image.author
        val dimensionText = image.height.toString() + " x " + image.width.toString()
        itemView.imageDimensions.text = dimensionText
        // load image
        Glide.with(itemView)
            .load(image.download_url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(itemView.image)
        itemView.setOnClickListener {
            if (itemView.imageAuthor.visibility == View.GONE) {
                itemView.imageAuthorTitle.visibility = View.VISIBLE
                itemView.imageAuthor.visibility = View.VISIBLE
                itemView.imageDimensions.visibility = View.VISIBLE
            } else {
                itemView.imageAuthorTitle.visibility = View.GONE
                itemView.imageAuthor.visibility = View.GONE
                itemView.imageDimensions.visibility = View.GONE
            }
        }
    }
}
