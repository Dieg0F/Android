package com.dfsw.atx.koin.data

import com.dfsw.atx.koin.model.Profile
import com.google.gson.Gson

class DataRepository(private val gson: Gson) {

    fun getProfiles(jsonString: String) =
            gson.fromJson(jsonString, Array<Profile>::class.java).toList()
}