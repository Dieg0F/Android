package com.dfsw.tagmaker.app.sessiondetails

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.navigation.findNavController
import com.dfsw.tagmaker.R
import com.dfsw.tagmaker.common.Constants.ARGS_SESSION_ID
import com.dfsw.tagmaker.common.Logger
import com.dfsw.tagmaker.data.model.Session
import kotlinx.android.synthetic.main.fragment_session_details.*
import org.jetbrains.anko.runOnUiThread
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SessionDetailsFragment : Fragment(), KoinComponent {

    private val sessionDetailsViewModel: SessionDetailsViewModel by inject()
    private var session: Session? = null

    companion object {
        val TAG = Logger.TAG
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        setHasOptionsMenu(true)
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_session_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        getTask()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.task_details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d(TAG, "onOptionsItemSelected")
        this.session?.let { task ->
            when (item?.itemId) {
                R.id.edit_task_option -> {
                    Log.d(TAG, "onOptionsItemSelected : edit_task_option")
                    toEditTask(task.id)
                }
                R.id.remove_task_option -> {
                    Log.d(TAG, "onOptionsItemSelected : remove_task_option")

                    sessionDetailsViewModel.deleteSession(task) {
                        Log.d(TAG, "onOptionsItemSelected : session removed")
                        view?.findNavController()?.popBackStack()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun requestArgs(): Int {
        var taskId = 0
        arguments?.getInt(ARGS_SESSION_ID)?.let {
            taskId = it
        }
        Log.d(TAG, "requestArgs : $taskId")
        return taskId
    }

    private fun getTask() {
        Log.d(TAG, "getSession")
        val lifecycleOwner = this
        val taskId = requestArgs()
        context?.runOnUiThread {
            sessionDetailsViewModel.getSession(taskId).observe(lifecycleOwner, Observer<Session> {
                if (it == null) {
                    return@Observer
                }
                Log.d(TAG, "Session : $session")
                setView(it)
            })
        }
    }

    private fun setView(session: Session?) {
        Log.d(TAG, "setView")
        session?.let { it ->
            this.session = it
            tv_session_title.text = it.title
            tv_session_information.text = it.description
        } ?: run {
            Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toEditTask(taskId: Int) {
        Log.d(TAG, "toEditTask, Session Id : $taskId")
        val bundle = Bundle()
        bundle.putInt(ARGS_SESSION_ID, taskId)
        view?.findNavController()?.navigate(R.id.edit_task, bundle)
    }
}
