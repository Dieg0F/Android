package com.dfsw.tagmaker.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dfsw.tagmaker.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        setUpViews()
    }

    private fun setUpViews() {

        val navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)
        setToolbarConfig(navController)

        actionBottom.setOnClickListener {
            navController.navigate(R.id.action_sessionsFragment_to_createSessionFragment, null)
        }
    }

    private fun setToolbarConfig(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            window.statusBarColor =
                ContextCompat.getColor(this, R.color.status_bar_color)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            when(destination.id) {

            }
        }
    }
}
