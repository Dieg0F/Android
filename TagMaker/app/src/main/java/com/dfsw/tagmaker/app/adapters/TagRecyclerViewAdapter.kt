package com.dfsw.tagmaker.app.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfsw.tagmaker.R
import com.dfsw.tagmaker.data.model.Tag
import kotlinx.android.synthetic.main.row_item_tag.view.*

class TagRecyclerViewAdapter(private var tagList: MutableList<Tag>)  : RecyclerView.Adapter<TagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TagViewHolder(inflater.inflate(R.layout.row_item_tag, parent, false))
    }

    override fun onBindViewHolder(taskViewHolder: TagViewHolder, position: Int) {
        taskViewHolder.bind(tagList[position])
    }

    override fun getItemCount() = tagList.size
}

class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(tag: Tag) {
        itemView.tv_tag.text = tag.name
    }
}