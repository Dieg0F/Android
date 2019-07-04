package com.dfsw.tasks.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
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

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.close()
        return super.onPrepareOptionsMenu(menu)
    }

    private fun setToolbarConfig(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            window.statusBarColor =
                ContextCompat.getColor(this, R.color.status_bar_color)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            when(destination.id) {
                R.id.to_do_list, R.id.done_list -> {
                    toolbar.navigationIcon = null
                    toolbar.title = "My tasks"
                    navigation.visibility = View.VISIBLE
                    floatingActionButton.show()
                }
                R.id.create_task -> {
                    toolbar.title = "Create task"
                    navigation.visibility = View.GONE
                    floatingActionButton.hide()
                }
                R.id.tasks_details -> {
                    toolbar.title = "Task information"
                    navigation.visibility = View.GONE
                    floatingActionButton.hide()
                }
                R.id.edit_task -> {
                    toolbar.title = "Edit task"
                }
            }
        }
    }
}
