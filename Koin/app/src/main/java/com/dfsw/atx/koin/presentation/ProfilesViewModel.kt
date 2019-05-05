package com.dfsw.atx.koin.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dfsw.atx.koin.data.DataRepository
import com.dfsw.atx.koin.model.Profile

class ProfilesViewModel constructor(
        private val dataRepository: DataRepository) : ViewModel() {

    private val profilesLiveData = MutableLiveData<List<Profile>>()

    fun retrieveProfiles(profilesJson: String) {
        val data = dataRepository.getProfiles(profilesJson)
        profilesLiveData.postValue(data)
    }

    fun observableProfilesLiveData() = profilesLiveData

}
