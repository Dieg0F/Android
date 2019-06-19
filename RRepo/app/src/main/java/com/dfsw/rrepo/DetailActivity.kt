package com.dfsw.rrepo

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.dfsw.rrepo.data.AppDataBase
import com.dfsw.rrepo.data.dao.TaskDao
import com.dfsw.rrepo.data.model.Task
import kotlinx.android.synthetic.main.activity_task_details.*
import kotlin.concurrent.thread

class DetailActivity : AppCompatActivity() {

    private lateinit var taskDao: TaskDao
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        taskDao = AppDataBase.getInstance(this).taskDao()
        taskDao.getTask(extractTaskId()).observe( this, Observer<Task> {
            if (it == null) {
                finish()
                return@Observer
            }
            this.task = it
            taskTitle.text = it.title
            taskCompletionCheckbox.isChecked = it.completed
        })

        taskCompletionCheckbox.setOnCheckedChangeListener { _, isChecked ->
            task.completed = isChecked
            thread { taskDao.update(task) }
        }
    }

    private fun extractTaskId(): Int {
        if (!intent.hasExtra(TASK_ID)) {
            throw IllegalArgumentException("$TASK_ID must be provided to start this Activity")
        }
        return intent.getIntExtra(TASK_ID, Int.MIN_VALUE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_task_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_delete_task -> {

                thread { taskDao.delete(task) }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {

        private const val TASK_ID = "INTENT_TASK_ID"

        fun launchIntent(context: Context, taskId: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(TASK_ID, taskId)
            return intent
        }
    }
}