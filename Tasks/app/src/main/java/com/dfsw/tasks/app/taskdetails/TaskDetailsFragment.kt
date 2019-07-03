package com.dfsw.tasks.app.taskdetails

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.navigation.findNavController
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
        setHasOptionsMenu(true)
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_task_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        getTask()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d(TAG, "onOptionsItemSelected")
        this.task?.let { task ->
            when (item?.itemId) {
                R.id.edit_task_option -> {
                    Log.d(TAG, "onOptionsItemSelected : edit_task_option")
                    toEditTask(task.id)
                }
                R.id.remove_task_option -> {
                    Log.d(TAG, "onOptionsItemSelected : remove_task_option")

                    taskDetailsViewModel.deleteTask(task) {
                        Log.d(TAG, "onOptionsItemSelected : task removed")
                        view?.findNavController()?.popBackStack()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun requestArgs(): Int {
        var taskId = 0
        arguments?.getInt(ARGS_TASK_ID)?.let {
            taskId = it
        }
        Log.d(TAG, "requestArgs : $taskId")
        return taskId
    }

    private fun getTask() {
        Log.d(TAG, "getTaskById")
        val lifecycleOwner = this
        val taskId = requestArgs()
        context?.runOnUiThread {
            taskDetailsViewModel.getTaskById(taskId).observe(lifecycleOwner, Observer<Task> {
                if (it == null) {
                    return@Observer
                }
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
            tv_task_information.text = it.description
        } ?: run {
            Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toEditTask(taskId: Int) {
        Log.d(TAG, "toEditTask, Task Id : $taskId")
        val bundle = Bundle()
        bundle.putInt(ARGS_TASK_ID, taskId)
        view?.findNavController()?.navigate(R.id.edit_task, bundle)
    }
}
