package com.ktt.archsample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author luke_kao
 */
class HistoryAdapter : RecyclerView.Adapter<HistoryItemViewHolder>() {
    var history: MutableList<Record> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HistoryItemViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater?.inflate(R.layout.item_history, parent, false)
        return HistoryItemViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder?, position: Int) {
        val record = history[position]
        holder?.bindViews(record)
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