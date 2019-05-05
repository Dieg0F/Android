package com.dfsw.atx.koin.modules

import com.dfsw.atx.koin.presentation.ProfilesViewModel
import com.dfsw.atx.koin.adapters.ProfilesAdapter
import com.dfsw.atx.koin.data.DataRepository
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single { Gson() }
    factory { ProfilesAdapter() }
    factory { DataRepository(get()) }
    // If you want inject a interface, just do this
    //factory { DataRepository() as Interface }
    //factory<Interface> { DataRepository() }
    viewModel { ProfilesViewModel(get()) }
}