package com.dfsw.rrepo.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfsw.rrepo.R
import com.dfsw.rrepo.data.model.Task
import kotlinx.android.synthetic.main.tasks_row.view.*


class TaskListAdapter(private val clickListener: (Task) -> Unit) :
    ListAdapter<Task, TaskListAdapter.ViewHolder>(DIFF_UTIL_CALLBACK) {

    companion object {

        val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Task>() {

            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.tasks_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task, clickListener: (Task) -> Unit) {
            itemView.taskTitle.text = task.title
            itemView.setOnClickListener { clickListener(task) }
        }

    }
}