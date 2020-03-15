package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.DTemp
import kotlinx.android.synthetic.main.fragment_city.view.*
import kotlinx.android.synthetic.main.fragment_temperature.view.*

class RecycleViewAdapter2(
    private val mValues: List<DTemp>,
    private val mListener : onListInteraction
) : RecyclerView.Adapter<RecycleViewAdapter2.Viewholder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_temperature, parent, false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: RecycleViewAdapter2.Viewholder, position: Int) {
        val item = mValues[position]
        holder.textView1.text = item.dia
        holder.textView2.text = item.temperatura

        holder.mView.setOnClickListener{
            mListener?.onListInteraction(item)
        }
    }

    public fun updateData(){
        notifyDataSetChanged()
    }

    inner class Viewholder(val mView: View) : RecyclerView.ViewHolder(mView){
        val textView1: TextView = mView.textViewDay
        val textView2: TextView = mView.textViewDayTemp
    }

    interface onListInteraction{
        fun onListInteraction(item: DTemp?)
    }
}