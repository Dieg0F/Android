package com.dfsw.tasks.app.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.dfsw.tasks.R
import kotlinx.android.synthetic.main.notification_spinner.view.*

class NotificationSpinnerAdapter(parentContext: Context, var listItems: ArrayList<String>,
                                 val firstOption: String) : BaseAdapter() {

    val inflater = LayoutInflater.from(parentContext)
    val context: Context = parentContext
    lateinit var listItem: TextView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(R.layout.notification_spinner, parent, false)

        listItem = view.spinner_text
        listItem.text = listItems[position]
//        listItem.gravity = View.TEXT_ALIGNMENT_VIEW_END

        //Set meta data here and later we can access these values from OnItemSelected Event Of Spinner
        view.setTag(R.string.meta_position, position.toString())
        view.setTag(R.string.meta_title, listItems[position])

        return view
    }

    override fun getItem(position: Int): Any = listItems[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = listItems.size

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View

        if (listItems[position] == firstOption) {
            val tv = TextView(context)
            tv.height = 0
            tv.visibility = View.GONE
            view = tv
        } else {
            view = super.getDropDownView(position, null, parent)
        }

        listItem.setTypeface(null, Typeface.NORMAL)
        listItem.setCompoundDrawablesRelative(null, null, null, null)

        parent?.isVerticalScrollBarEnabled = false

        return view
    }
}