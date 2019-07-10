package com.dfsw.tagmaker.app.editsession

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.dfsw.tagmaker.R
import com.dfsw.tagmaker.common.Constants.ARGS_SESSION_ID
import com.dfsw.tagmaker.common.KeyboardHelper
import com.dfsw.tagmaker.common.Logger
import com.dfsw.tagmaker.data.model.Session
import kotlinx.android.synthetic.main.fragment_create_session.*
import org.jetbrains.anko.runOnUiThread
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class EditSessionFragment : Fragment(), KoinComponent  {

    companion object {
        val TAG = Logger.TAG
    }

    private val editSessionViewModel: EditSessionViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(false)
        return inflater.inflate(R.layout.fragment_edit_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        getTask()
    }

    private fun getTask() {
        Log.d(TAG, "getSession")
        val lifecycleOwner = this
        val taskId = requestArgs()
        context?.runOnUiThread {
            editSessionViewModel.getTaskById(taskId).observe(lifecycleOwner, Observer<Session> {
                if (it == null) {
                    return@Observer
                }
                setView(it)
            })
        }
    }

    private fun requestArgs(): Int {
        var taskId = 0
        arguments?.getInt(ARGS_SESSION_ID)?.let {
            taskId = it
        }
        Log.d(TAG, "requestArgs : $taskId")
        return taskId
    }

    private fun setView(session: Session) {
        Log.d(TAG, "setView")

        et_session_title.setText(session.title)
        et_session_information.setText(session.description)

        floatingActionButton.setOnClickListener {

            session.title = et_session_title.text.toString()
            session.description = et_session_information.text.toString()
            session.status = "UPDATED"

            editSessionViewModel.update(session) { success ->
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

    }

    override fun onStop() {
        view?.let {
            KeyboardHelper.hide(requireContext(), it)
        }
        super.onStop()
    }
}
