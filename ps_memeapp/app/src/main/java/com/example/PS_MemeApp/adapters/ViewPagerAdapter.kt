package com.example.PS_MemeApp.adapters

import com.example.PS_MemeApp.model.Meme
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.PS_MemeApp.R


class ViewPagerAdapter(val context: Context, val memes : List<Meme>): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    class ViewPagerViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val imageview : ImageView = itemView.findViewById<ImageView>(R.id.memeImage)
        val title : TextView = itemView.findViewById(R.id.memeName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context)
                               .inflate(R.layout.memes_item,parent,false)
        return ViewPagerViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curMeme = memes[position]


        holder.apply {
            Glide.with(context)
                .load(curMeme.url)
                .into(imageview)
            title.text = curMeme.name
        }


    }



    override fun getItemCount(): Int {
        return memes.size
    }




}



