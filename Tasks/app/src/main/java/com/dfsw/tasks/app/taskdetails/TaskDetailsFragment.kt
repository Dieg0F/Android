package com.dfsw.tasks.app.taskdetails

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dfsw.tasks.R
import com.dfsw.tasks.common.Containts.ARGS_TASK_ID
import com.dfsw.tasks.common.Logger
import com.dfsw.tasks.data.model.Task
import kotlinx.android.synthetic.main.fragment_task_details.*
import org.jetbrains.anko.runOnUiThread
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class TaskDetailsFragment : Fragment(), KoinComponent {

    private val taskDetailsViewModel: TaskDetailsViewModel by inject()
    private var task: Task? = null

    companion object {
        val TAG = Logger.TAG
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_task_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        getTask()
    }

    private fun requestArgs() : Int {
        Log.d(TAG, "requestArgs")
        val bundle = Bundle()
        Log.d("TAGG", "${bundle.getInt(ARGS_TASK_ID, Int.MIN_VALUE)}")
        Log.d("TAGG", "${bundle.get(ARGS_TASK_ID)}")
        Log.d("TAGG", "${bundle.getBundle(ARGS_TASK_ID)}")
        Log.d("TAGG", "${bundle.getString(ARGS_TASK_ID)} ")
        return 0
    }

    private fun getTask() {
        Log.d(TAG, "getTaskById")
        val lifecycleOwner = this
        val taskId = requestArgs()
        context?.runOnUiThread {
            taskDetailsViewModel.getTaskById(taskId).observe(lifecycleOwner, Observer<Task> {
                Log.d(TAG, "requestArgs : $taskId")
                Log.d(TAG, "Task : $task")
                setView(it)
            })
        }
    }

    private fun setView(task: Task?) {
        Log.d(TAG, "setView")
        task?.let { it ->
            this.task = it
            tv_task_title.text = it.title
            tv_task_information.text = it.information
        } ?: run {
            Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
        }
    }
}
