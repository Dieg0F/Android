package com.dfsw.tagmaker.app.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfsw.tagmaker.R
import com.dfsw.tagmaker.data.model.Session
import kotlinx.android.synthetic.main.row_item_session.view.*

class TaskRecyclerViewAdapter(
    private var sessionList: MutableList<Session>,
    private val clickListener: (Int) -> Unit)  : RecyclerView.Adapter<SessionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SessionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SessionViewHolder(inflater.inflate(R.layout.row_item_session, parent, false))
    }

    override fun onBindViewHolder(taskViewHolder: SessionViewHolder, position: Int) {
        taskViewHolder.bind(sessionList[position], clickListener)
    }

    override fun getItemCount() = sessionList.size
}

class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(session: Session, clickListener: (Int) -> Unit) {
        itemView.tv_session_title.text = session.title
        itemView.content_task_item.setOnClickListener { clickListener(session.id) }
    }
}