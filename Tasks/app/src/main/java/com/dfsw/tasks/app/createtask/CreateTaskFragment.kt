package com.dfsw.tasks.app.createtask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.dfsw.tasks.R
import com.dfsw.tasks.common.KeyboardHelper
import com.dfsw.tasks.common.Logger
import com.dfsw.tasks.data.model.Task
import kotlinx.android.synthetic.main.fragment_create_task.*
import org.jetbrains.anko.runOnUiThread
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class CreateTaskFragment : Fragment(), KoinComponent  {

    companion object {
        val TAG = Logger.TAG
    }

    private val createTaskViewModel: CreateTaskViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(false)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(false)
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        Log.d(TAG, "setView")
        val task = Task()

        floatingActionButton.setOnClickListener {

            task.title = et_task_title.text.toString()
            task.description = et_task_information.text.toString()
            task.status = "NEW"

            createTaskViewModel.insert(task) { success ->
                context?.runOnUiThread {
                    val message = if (success) {
                        view?.findNavController()?.navigateUp()
                        "Success!"
                    } else {
                        "Fail!"
                    }

                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        sw_notifications.setOnClickListener {
            if (notification_panel.visibility == View.GONE) {
                notification_panel.visibility = View.VISIBLE
                task.notificationsEnabled = true
                task.notificationsRepeatable = sw_notification_repeat.isEnabled
                task.notificationFrequency = 15000L
            } else {
                notification_panel.visibility = View.GONE
            }
        }
    }

    override fun onStop() {
        view?.let {
            KeyboardHelper.hide(requireContext(), it)
        }
        super.onStop()
    }
}
