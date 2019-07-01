package com.dfsw.tasks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }

    private fun setToolbarConfig(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.to_do_list -> {
                    toolbar.navigationIcon = null
                    toolbar.title = "Tasks To Do"
                }
                R.id.done_list -> {
                    toolbar.navigationIcon = null
                    toolbar.title = "Done Tasks"
                }
            }
        }
    }
}
