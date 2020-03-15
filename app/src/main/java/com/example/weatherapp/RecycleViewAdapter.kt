package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.DCity
import kotlinx.android.synthetic.main.fragment_city.view.*

class RecycleViewAdapter(
    private val mValues: List<DCity>,
    private val mListener : onListInteraction
    ) : RecyclerView.Adapter<RecycleViewAdapter.Viewholder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_city, parent, false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: RecycleViewAdapter.Viewholder, position: Int) {
        val item = mValues[position]
        holder.textView1.text = item.nombre
        holder.textView2.text = item.temperatura

        holder.mView.setOnClickListener{
            mListener?.onListInteraction(item)
        }
    }

    public fun updateData(){
        notifyDataSetChanged()
    }

    inner class Viewholder(val mView: View) : RecyclerView.ViewHolder(mView){
        val textView1: TextView = mView.textViewCityName
        val textView2: TextView = mView.textViewCityTemp
    }

    interface onListInteraction{
        fun onListInteraction(item: DCity?)
    }
}