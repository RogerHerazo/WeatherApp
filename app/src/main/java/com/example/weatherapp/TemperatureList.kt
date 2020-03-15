package com.example.weatherapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.DCity
import com.example.weatherapp.data.DTemp
import kotlinx.android.synthetic.main.fragment_city_list.view.*
import kotlinx.android.synthetic.main.fragment_temperature_list.view.*

/**
 * A simple [Fragment] subclass.
 */
    class TemperatureList : Fragment(), View.OnClickListener, RecycleViewAdapter2.onListInteraction    {
    val dtemp = mutableListOf<DTemp>()
    private var adapter: RecycleViewAdapter2? = null
    lateinit var d1: DTemp
    lateinit var d2: DTemp
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_temperature_list, container, false)

        dtemp.add(DTemp("Dia 1", "20"))
        dtemp.add(DTemp("Dia 2", "23"))

        adapter = RecycleViewAdapter2(dtemp, this)

        view.templist.layoutManager = LinearLayoutManager(context)
        view.templist.adapter = adapter

        //view.floatingActionButton.setOnClickListener{
        //VolleySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(getJsonObjectRequest())
        //}
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        d1 = DTemp("Dia 1", "20")
        d2 = DTemp("Dia 2", "23")
    }

    override fun onListInteraction(item: DTemp?) {
        //Log.d("KRecycle", "onListInteraction" + item!!.nombre)
        val bundle = bundleOf("data" to item)
        //val bundle = bundleOf("nombre" to item?.nombre)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}
