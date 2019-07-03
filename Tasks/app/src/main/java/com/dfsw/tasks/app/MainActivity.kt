package com.dfsw.tasks.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dfsw.tasks.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpViews()
    }

    private fun setUpViews() {

        val navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

        navigation.setupWithNavController(navController)
        setupActionBarWithNavController(this, navController)

        setToolbarConfig(navController)

        floatingActionButton.setOnClickListener {
            navController.navigate(R.id.create_task, null)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.task_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.close()
        return super.onPrepareOptionsMenu(menu)
    }

    private fun setToolbarConfig(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.to_do_list -> {
                    toolbar.navigationIcon = null
                    toolbar.title = "Tasks To Do"
                    navigation.visibility = View.VISIBLE
                    floatingActionButton.show()
                }
                R.id.done_list -> {
                    toolbar.navigationIcon = null
                    toolbar.title = "Done Tasks"
                    navigation.visibility = View.VISIBLE
                    floatingActionButton.show()
                }
                R.id.create_task -> {
                    toolbar.title = "Create Task"
                    navigation.visibility = View.GONE
                    floatingActionButton.hide()
                }
                R.id.tasks_details -> {
                    toolbar.title = "Task Details"
                    navigation.visibility = View.GONE
                    floatingActionButton.hide()
                }
            }
        }
    }
}
