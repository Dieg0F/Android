package com.dfsw.tagmaker.di.module

import com.dfsw.tagmaker.app.createsession.CreateSessionViewModel
import com.dfsw.tagmaker.app.editsession.EditSessionViewModel
import com.dfsw.tagmaker.app.sessiondetails.SessionDetailsViewModel
import com.dfsw.tagmaker.app.sessions.SessionsViewModel
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single { Gson() }
    viewModel { SessionsViewModel() }
    viewModel { CreateSessionViewModel() }
    viewModel { SessionDetailsViewModel() }
    viewModel { EditSessionViewModel() }
}