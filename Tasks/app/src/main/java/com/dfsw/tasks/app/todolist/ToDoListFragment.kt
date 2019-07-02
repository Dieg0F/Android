package com.dfsw.tasks.app.todolist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfsw.tasks.R
import com.dfsw.tasks.app.adapters.TaskRecyclerViewAdapter
import com.dfsw.tasks.common.Logger
import com.dfsw.tasks.data.model.Task
import kotlinx.android.synthetic.main.fragment_to_do_list.*
import org.koin.android.ext.android.inject
import org.koin.standalone.KoinComponent
import kotlin.concurrent.thread

class ToDoListFragment : Fragment(), KoinComponent {

    companion object {
        val TAG = Logger.TAG
    }

    private var taskList: MutableList<Task> = mutableListOf()
    private val toDoListViewModel: ToDoListViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setView()
    }

    private fun setView() {
        Log.d(TAG, "setView")
        getTasks()
    }

    private fun getTasks() {
        Log.d(TAG, "getTasks")
        toDoListViewModel.getAllTasks().observe(this, Observer { tasks ->
            if (tasks.isNullOrEmpty()) {
                // Do something
            } else {
                Log.d(TAG, "Tasks : $tasks")
                taskList = tasks
                setRecyclerView()
            }
        })
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        setView()
        super.onResume()
    }

    private fun setRecyclerView() {
        Log.d(TAG, "setRecyclerView")
        task_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        task_recycler_view.adapter = TaskRecyclerViewAdapter(taskList)
    }
}