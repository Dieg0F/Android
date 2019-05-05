package com.dfsw.atx.koin

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.dfsw.atx.koin.adapters.ProfilesAdapter
import com.dfsw.atx.koin.presentation.ProfileView
import com.dfsw.atx.koin.presentation.ProfilesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val profilesAdapter: ProfilesAdapter by inject()
    private val profilesViewModel: ProfilesViewModel by viewModel()
    private val profileView: ProfileView by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profilesViewModel.observableProfilesLiveData().observe(this, Observer {
            it?.let {
                profilesAdapter.profiles = it
            }
        })

        val profilesJson = resources.openRawResource(R.raw.data)
                .bufferedReader().use { it.readText() }
        profilesViewModel.retrieveProfiles(profilesJson)
        setRecyclerView()
    }

    fun setRecyclerView() {
        profile_recycler_view.layoutManager = LinearLayoutManager(this)
        profile_recycler_view.adapter = profilesAdapter
    }
}
