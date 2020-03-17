package com.example.weatherapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.DCity
import com.example.weatherapp.data.DTemp
import com.example.weatherapp.data.DayViewModel
import com.example.weatherapp.databinding.FragmentTemperatureBinding
import kotlinx.android.synthetic.main.fragment_city_list.view.*
import kotlinx.android.synthetic.main.fragment_temperature_list.view.*

/**
 * A simple [Fragment] subclass.
 */
    class TemperatureList : Fragment(), View.OnClickListener, RecycleViewAdapter2.onListInteraction    {
    private lateinit var dayViewModel: DayViewModel
    lateinit var city: DCity
    lateinit var mBinding: FragmentTemperatureBinding
    var dtemp = mutableListOf<DTemp>()
    private var dtemptemp = mutableListOf<DTemp>()
    private var adapter: RecycleViewAdapter2? = null
    lateinit var d1: DTemp
    lateinit var d2: DTemp
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_temperature_list, container, false)
        city = arguments!!.getParcelable("data")!!

        dayViewModel = ViewModelProvider(this).get(DayViewModel::class.java)

        dayViewModel.addCityDays(city.nombre)

        dayViewModel.getCityDays().observe(viewLifecycleOwner, Observer { resulttemps ->
            run{
                dtemptemp = resulttemps as MutableList<DTemp>

                dtemp.clear()
                for (temp in dtemptemp){
                    dtemp.add(DTemp(
                        temp.dia,
                        temp.temperatura
                    ))
                }
            }
            adapter!!.updateData()
        })

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
