package com.dfsw.tagmaker.app.sessiondetails

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.navigation.findNavController
import com.dfsw.tagmaker.R
import com.dfsw.tagmaker.app.adapters.TagRecyclerViewAdapter
import com.dfsw.tagmaker.common.Constants.ARGS_SESSION_ID
import com.dfsw.tagmaker.common.Logger
import com.dfsw.tagmaker.data.model.Session
import com.dfsw.tagmaker.data.model.Tag
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
        getSession()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.session_details_menu, menu)
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
        var sessionId = 0
        arguments?.getInt(ARGS_SESSION_ID)?.let {
            sessionId = it
        }
        Log.d(TAG, "requestArgs : $sessionId")
        return sessionId
    }

    private fun getSession() {
        Log.d(TAG, "getSession")
        val lifecycleOwner = this
        val sessionId = requestArgs()
        context?.runOnUiThread {
            sessionDetailsViewModel.getSession(sessionId).observe(lifecycleOwner, Observer<Session> {
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

            setRecyclerView(it.tags)
        } ?: run {
            Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerView(tags: String) {
        Log.d(TAG, "setRecyclerView")

        val tagList: MutableList<Tag> = mutableListOf()
        tags.split("#").forEach {
            tagList.add(Tag(name = "#$it"))
        }
        tagList.removeAt(0)

        tags_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        tags_recycler_view.adapter = TagRecyclerViewAdapter(tagList)
    }

    private fun toEditTask(taskId: Int) {
        Log.d(TAG, "toEditTask, Session Id : $taskId")
        val bundle = Bundle()
        bundle.putInt(ARGS_SESSION_ID, taskId)
        view?.findNavController()?.navigate(R.id.edit_task, bundle)
    }
}
