package com.example.appdev_act5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val context: Context, private val list: ArrayList<Info>) :
    RecyclerView.Adapter<Adapter.MyView>() {
    class MyView(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView
        var textView: TextView

        init {
            imageView = view.findViewById(R.id.ImageView)
            textView = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.imageView.setImageResource(list[position].image)
        holder.textView.text = list[position].name
        holder.itemView.setOnClickListener {
            if (position >= 0 && position < list.size) {
                val characterName = list[position].name
                Toast.makeText(context, "  $characterName", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}