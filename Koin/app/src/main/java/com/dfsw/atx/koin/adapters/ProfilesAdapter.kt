package com.dfsw.atx.koin.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dfsw.atx.koin.R
import com.dfsw.atx.koin.model.Profile


class ProfilesAdapter(): RecyclerView.Adapter<ProfilesAdapter.ViewHolder>() {

    var profiles: List<Profile> = listOf()

    override fun getItemCount(): Int {
        return profiles.count()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val profile = profiles[position]
        viewHolder.nameText.text = profile.name
        viewHolder.ageText.text = profile.age.toString()
        viewHolder.registeredText.text = profile.registered
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_item, parent, false)

        return ViewHolder(inflateView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.field_name)
        val ageText: TextView = view.findViewById(R.id.field_age)
        val registeredText: TextView = view.findViewById(R.id.field_registered)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.profile_item)
    }

}