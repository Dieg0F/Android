package com.dfsw.tagmaker.app.createsession

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.dfsw.tagmaker.R
import com.dfsw.tagmaker.common.KeyboardHelper
import com.dfsw.tagmaker.common.Logger
import com.dfsw.tagmaker.data.model.Session
import kotlinx.android.synthetic.main.fragment_create_session.*
import org.jetbrains.anko.runOnUiThread
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class CreateSessionFragment : Fragment(), KoinComponent  {

    companion object {
        val TAG = Logger.TAG
    }

    private val createSessionViewModel: CreateSessionViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(false)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(false)
        return inflater.inflate(R.layout.fragment_create_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        Log.d(TAG, "setView")
        floatingActionButton.setOnClickListener {

            val task = Session()
            task.title = et_session_title.text.toString()
            task.description = et_session_information.text.toString()
            task.status = "NEW"

            createSessionViewModel.insert(task) { success ->
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
    }

    override fun onStop() {
        view?.let {
            KeyboardHelper.hide(requireContext(), it)
        }
        super.onStop()
    }
}
