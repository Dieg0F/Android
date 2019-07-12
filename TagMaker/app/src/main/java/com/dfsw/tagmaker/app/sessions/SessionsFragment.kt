package com.dfsw.tagmaker.app.sessions

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.dfsw.tagmaker.R
import com.dfsw.tagmaker.app.adapters.TaskRecyclerViewAdapter
import com.dfsw.tagmaker.common.Constants.ARGS_SESSION_ID
import com.dfsw.tagmaker.common.Logger
import com.dfsw.tagmaker.data.model.Session
import kotlinx.android.synthetic.main.fragment_sessions.*
import org.koin.android.ext.android.inject
import org.koin.standalone.KoinComponent

class SessionsFragment : Fragment(), KoinComponent {

    companion object {
        val TAG = Logger.TAG
    }

    private var sessionList: MutableList<Session> = mutableListOf()
    private val sessionsViewModel: SessionsViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(false)
        return inflater.inflate(R.layout.fragment_sessions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        Log.d(TAG, "setView")
        getTasks()
    }

    private fun getTasks() {
        Log.d(TAG, "getTasks")
        sessionsViewModel.getAllTasks().observe(this, Observer { tasks ->
            if (tasks.isNullOrEmpty()) {
                // Do something
            } else {
                Log.d(TAG, "Tasks : $tasks")
                sessionList = tasks
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
        session_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        session_recycler_view.adapter = TaskRecyclerViewAdapter(sessionList) {
            toTaskDetails(it)
        }
    }

    private fun toTaskDetails(taskId: Int) {
        Log.d(TAG, "toTaskDetails, Session Id : $taskId")
        val bundle = Bundle()
        bundle.putInt(ARGS_SESSION_ID, taskId)
        view?.findNavController()?.navigate(R.id.tasks_details, bundle)
    }
}