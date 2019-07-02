package com.dfsw.tasks.app.createtask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfsw.tasks.R
import com.dfsw.tasks.data.model.Task
import kotlinx.android.synthetic.main.fragment_create_task.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class CreateTaskFragment : Fragment(), KoinComponent  {

    private val createTaskViewModel: CreateTaskViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {

        floatingActionButton.setOnClickListener {

            val task = Task()
            task.title = et_task_title.text.toString()
            task.information = et_task_information.text.toString()

            createTaskViewModel.insert(task)
        }
    }
}
