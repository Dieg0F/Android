package com.dfsw.tasks.app.createtask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.findNavController
import com.dfsw.tasks.R
import com.dfsw.tasks.app.adapters.NotificationSpinnerAdapter
import com.dfsw.tasks.common.KeyboardHelper
import com.dfsw.tasks.common.Logger
import com.dfsw.tasks.data.model.Task
import kotlinx.android.synthetic.main.fragment_create_task.*
import org.jetbrains.anko.runOnUiThread
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*
import kotlin.collections.ArrayList


class CreateTaskFragment : Fragment(), KoinComponent {

    companion object {
        val TAG = Logger.TAG
    }

    private val createTaskViewModel: CreateTaskViewModel by inject()
    private lateinit var task: Task

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
        task = Task()

        floatingActionButton.setOnClickListener {

            task.title = et_task_title.text.toString()
            task.description = et_task_information.text.toString()
            task.status = "NEW"

            if (task.notificationsEnabled) {
                task.notificationsRepeatable = sw_notification_repeat.isChecked
                task.notificationFrequency = getTimeNotification()

                createTaskViewModel.enableNotification(task, requireContext())
            }

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

            } else {
                notification_panel.visibility = View.GONE
                task.notificationsEnabled = false
                task.notificationsRepeatable = false
                task.notificationFrequency = 0L
            }
        }

        setSpinnerSetup()
    }

    private fun getTimeNotification() =
        task.notificationDateInMills - Calendar.getInstance().timeInMillis

    private fun setSpinnerSetup() {
        val array: ArrayList<String> = arrayListOf()

        array.add("Selecione")
        array.add("Hoje")
        array.add("Escolher Data")

        sp_notification_period.adapter = NotificationSpinnerAdapter(
            requireContext(), array, "Selecione"
        )
        sp_notification_period.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int,
                id: Long
            ) {
                when (parent.getItemAtPosition(position)) {
                    "Hoje" -> {
                        val calendar = Calendar.getInstance()
                        val day = calendar.get(Calendar.DAY_OF_MONTH)
                        val month = calendar.get(Calendar.MONTH)
                        val year = calendar.get(Calendar.YEAR)

                        openTimePicker(year, month, day)
                    }
                    "Escolher Data" -> {
                        openDatePicker()
                    }
                }
            }
        }
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        if (Logger.DEBUG) Log.d(TAG, "getStartDate")
        val datePickerDialog = DatePickerDialog(
            requireContext(), 0,
            DatePickerDialog.OnDateSetListener { _, yearSet, monthSet, daySet ->
                task.notificationDate = "Notify when $daySet/$monthSet/$yearSet at"
                openTimePicker(yearSet, monthSet, daySet)
            }, year, month, day
        )

        datePickerDialog.show()
    }

    private fun openTimePicker(year: Int, month: Int, dayOfMonth: Int) {
        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            task.notificationDate += " $hour:$minute"
            tv_notification_at.text = task.notificationDate

            val date = Calendar.getInstance()
            date.set(year, month, dayOfMonth, hour, minute)

            task.notificationDateInMills = date.timeInMillis
        }

        TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }

    override fun onStop() {
        view?.let {
            KeyboardHelper.hide(requireContext(), it)
        }
        super.onStop()
    }
}
