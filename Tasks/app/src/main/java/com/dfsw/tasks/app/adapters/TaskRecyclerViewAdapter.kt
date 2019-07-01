package com.dfsw.tasks.app.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfsw.tasks.R
import com.dfsw.tasks.data.model.Task
import kotlinx.android.synthetic.main.row_item_task.view.*

class TaskRecyclerViewAdapter(private val taskList: MutableList<Task>) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(inflater.inflate(R.layout.row_item_task, parent, false))
    }

    override fun onBindViewHolder(taskViewHolder: TaskViewHolder, position: Int) {
        taskViewHolder.bind(taskList[position])
    }

    override fun getItemCount() = taskList.size
}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(task: Task) {
        itemView.task_title.text = task.title
        itemView.task_information.text = task.information
        itemView.task_status_title.text = task.status
    }

}