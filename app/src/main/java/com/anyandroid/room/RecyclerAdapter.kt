package com.anyandroid.room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var postsList: List<Post>, private val listener: MainActivity) :
    RecyclerView.Adapter<RecyclerAdapter.PostViewHolder>() {

   inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnLongClickListener {
        val title = itemView.findViewById<TextView>(R.id.textTitle)!!
        val body = itemView.findViewById<TextView>(R.id.textBody)!!
        init {
            itemView.setOnLongClickListener(this)
            itemView.setOnClickListener { listener.onPostClick(adapterPosition) }
        }

       override fun onLongClick(v: View?): Boolean {
           val position = adapterPosition
           if (position != RecyclerView.NO_POSITION) {
               listener.onDeleteClick(position)
           }
           return true
       }


   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(posts: List<Post>){
        this.postsList = posts
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.title.text = postsList[position].title
        holder.body.text = postsList[position].body
    }

}