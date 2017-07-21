package com.ktt.archsample

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

/**
 * @author luke_kao
 */
class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var textView: TextView = itemView.findViewById(R.id.text) as TextView

    fun bindViews(record: Record) {
        textView.text = record.value.toString()
    }
}