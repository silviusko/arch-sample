package com.ktt.archsample.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.ktt.archsample.R
import com.ktt.archsample.db.entity.Record

/**
 * @author luke_kao
 */
class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var textView: TextView = itemView.findViewById(R.id.text)

    fun bindViews(record: Record) {
        textView.text = record.value.toString()
    }
}