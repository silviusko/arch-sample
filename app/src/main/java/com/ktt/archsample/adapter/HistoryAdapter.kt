package com.ktt.archsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ktt.archsample.R
import com.ktt.archsample.adapter.viewholder.HistoryItemViewHolder
import com.ktt.archsample.db.entity.Record

/**
 * @author luke_kao
 */
class HistoryAdapter : RecyclerView.Adapter<HistoryItemViewHolder>() {

    var history: MutableList<Record> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater?.inflate(R.layout.item_history, parent, false)
        return HistoryItemViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val record = history[position]
        holder.bindViews(record)
    }

    override fun getItemCount(): Int {
        return history.size
    }

    fun update(data: List<Record>?) {
        history.clear()
        if (data != null) {
            history.addAll(data)
        }
    }
}