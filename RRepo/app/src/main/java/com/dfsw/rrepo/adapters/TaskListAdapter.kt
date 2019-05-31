package com.dfsw.rrepo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfsw.rrepo.R
import com.dfsw.rrepo.data.model.Task
import kotlinx.android.synthetic.main.tasks_row.view.*

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private val tasks: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return TaskViewHolder(inflater.inflate(R.layout.tasks_row, viewGroup, false))
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(taskViewHolder: TaskViewHolder, position: Int) {
        taskViewHolder.bind(tasks[position])
    }

    fun addTask(task: Task) {
        if (!tasks.contains(task)) {
            tasks.add(task)
            notifyItemInserted(tasks.size)
        }
    }

    //ViewHolder Class
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            itemView.taskTitle.text = task.title
        }
    }
}