package com.azhar.examplefan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */
class MainAdapter(private val modelMain: List<ModelMain>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val data = modelMain[pos]
        holder.name.text = data.name
        holder.propellant.text = data.propellant
        holder.destination.text = data.destination
    }

    override fun getItemCount(): Int {
        return modelMain.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var propellant: TextView
        var destination: TextView

        init {
            name = itemView.findViewById(R.id.name)
            propellant = itemView.findViewById(R.id.propellant)
            destination = itemView.findViewById(R.id.destination)
        }
    }

}