package com.dfsw.atx.koin.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.dfsw.atx.koin.R
import com.dfsw.atx.koin.model.Profile
import kotlinx.android.synthetic.main.profile_view.view.*
import org.koin.standalone.KoinComponent

class ProfileView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr), KoinComponent {

    init {
        View.inflate(context, R.layout.profile_view, this)
    }

    fun setProfile(profile: Profile) {
        field_name.text = profile.name
        field_age.text = profile.age.toString()
        field_about.text = profile.age.toString()
        field_registered.text = profile.registered
    }

}