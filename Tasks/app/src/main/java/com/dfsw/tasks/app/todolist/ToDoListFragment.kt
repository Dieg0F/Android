package com.dfsw.tasks.app.todolist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfsw.tasks.R
import com.dfsw.tasks.app.adapters.TaskRecyclerViewAdapter
import com.dfsw.tasks.data.model.Task
import kotlinx.android.synthetic.main.fragment_to_do_list.*
import org.koin.android.ext.android.inject

class ToDoListFragment : Fragment() {

    private var taskList: MutableList<Task> = mutableListOf()
    private val toDoListViewModel: ToDoListViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setView()
    }

    private fun setView() {
        setRecyclerView()
    }

    private fun setRecyclerView() {

        taskList.add(Task(0, "Task 1", "Task for Pee", "Doing", false))
        taskList.add(Task(0, "Task 2", "Task for Food", "New", false))
        taskList.add(Task(0, "Task 3", "Task for Mod", "Stopped", false))
        taskList.add(Task(0, "Task 4", "Task for Shit", "Doing", false))

        task_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        task_recycler_view.adapter = TaskRecyclerViewAdapter(taskList)
    }
}