package com.dfsw.tasks.app.edittask

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.dfsw.tasks.R
import com.dfsw.tasks.common.Constants.ARGS_TASK_ID
import com.dfsw.tasks.common.KeyboardHelper
import com.dfsw.tasks.common.Logger
import com.dfsw.tasks.data.model.Task
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.fragment_create_task.et_task_information
import kotlinx.android.synthetic.main.fragment_create_task.et_task_title
import kotlinx.android.synthetic.main.fragment_create_task.floatingActionButton
import kotlinx.android.synthetic.main.fragment_create_task.notification_panel
import kotlinx.android.synthetic.main.fragment_create_task.sw_notification_repeat
import kotlinx.android.synthetic.main.fragment_create_task.sw_notifications
import kotlinx.android.synthetic.main.fragment_edit_task.*
import org.jetbrains.anko.runOnUiThread
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class EditTaskFragment : Fragment(), KoinComponent  {

    companion object {
        val TAG = Logger.TAG
    }

    private val editTaskViewModel: EditTaskViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(false)
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        getTask()
    }

    private fun getTask() {
        Log.d(TAG, "getTaskById")
        val lifecycleOwner = this
        val taskId = requestArgs()
        context?.runOnUiThread {
            editTaskViewModel.getTaskById(taskId).observe(lifecycleOwner, Observer<Task> {
                if (it == null) {
                    return@Observer
                }
                setView(it)
            })
        }
    }

    private fun requestArgs(): Int {
        var taskId = 0
        arguments?.getInt(ARGS_TASK_ID)?.let {
            taskId = it
        }
        Log.d(TAG, "requestArgs : $taskId")
        return taskId
    }

    private fun setView(task: Task) {
        Log.d(TAG, "setView")

        et_task_title.setText(task.title)
        et_task_information.setText(task.description)

        sw_notifications.isChecked = task.notificationsEnabled
        sw_notification_repeat.isChecked = task.notificationsRepeatable
        et_notification_period.setText(task.notificationFrequency.toString())

        floatingActionButton.setOnClickListener {

            task.title = et_task_title.text.toString()
            task.description = et_task_information.text.toString()
            task.status = "UPDATED"

            if (task.notificationsEnabled) {
                task.notificationsRepeatable = sw_notification_repeat.isChecked
                task.notificationFrequency = getTimeNotification()

                editTaskViewModel.updateNotification(task, requireContext())
            }

            editTaskViewModel.update(task) { success ->
                context?.runOnUiThread {
                    val message = if (success) {
                        view?.findNavController()?.navigateUp()
                        "Edit Success!"
                    } else {
                        "Edit Fail!"
                    }

                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        notificationSwitchController(task)
        notificationSwitchAction(task)
    }

    private fun notificationSwitchAction(task: Task) {
        sw_notifications.setOnClickListener {
            if (notification_panel.visibility == View.GONE) {
                notification_panel.visibility = View.VISIBLE
                task.notificationsEnabled = true
            } else {
                notification_panel.visibility = View.GONE
                task.notificationsEnabled = false
                task.notificationsRepeatable = false
                task.notificationFrequency = 0L
            }
        }
    }

    private fun notificationSwitchController(task: Task) {
        if (task.notificationsEnabled) {
            notification_panel.visibility = View.VISIBLE
            task.notificationsEnabled = true
        } else {
            notification_panel.visibility = View.GONE
            task.notificationsEnabled = false
            task.notificationsRepeatable = false
            task.notificationFrequency = 0L
        }
    }

    private fun getTimeNotification() : Long {
        return et_notification_period.text.toString().toLong()
    }


    override fun onStop() {
        view?.let {
            KeyboardHelper.hide(requireContext(), it)
        }
        super.onStop()
    }
}
