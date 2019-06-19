package com.dfsw.rrepo

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.dfsw.rrepo.adapters.UserListAdapter
import com.dfsw.rrepo.data.AppDataBase
import com.dfsw.rrepo.data.dao.UserDao
import com.dfsw.rrepo.data.model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_layout.*
import kotlin.concurrent.thread

class UsersActivity : AppCompatActivity() {

    private lateinit var userListAdapter: UserListAdapter
    private lateinit var database: AppDataBase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        setSupportActionBar(toolbar)

        database = AppDataBase.getInstance(this)
        //userDao = database.userDao()

        addTaskButton.setOnClickListener {
            addUser()
        }

        taskTitleInput.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addUser()
                taskTitleInput.setText("")
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })

        userListAdapter = UserListAdapter {}

        taskList.layoutManager = LinearLayoutManager(this)
        taskList.adapter = userListAdapter

        userDao.getAll().observe(this, Observer<List<User>> {
            userListAdapter.submitList(it)
        })
    }

    private fun addUser() {
        val title = taskTitleInput.text.toString()
        if (title.isBlank()) {
            Snackbar.make(toolbar, "User name is required", Snackbar.LENGTH_SHORT).show()
            return
        }
        val user = User(name = title)

        thread { userDao.insert(user) }
    }

}