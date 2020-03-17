package com.example.weatherapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.CityViewModel
import com.example.weatherapp.data.DCity
import kotlinx.android.synthetic.main.fragment_city_list.view.*


/**
 * A simple [Fragment] subclass.
 */
class CityList : Fragment(), View.OnClickListener, RecycleViewAdapter.onListInteraction{


    private lateinit var navController: NavController
    private lateinit var viewModel: CityViewModel
    lateinit var Quilla: DCity
    lateinit var Cgena: DCity
    var dcity = mutableListOf<DCity>()
    private var dcitytemp = mutableListOf<DCity>()
    private var adapter: RecycleViewAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_city_list, container, false)
        //dcity = mutableListOf<DCity>()
        //dcity.add(DCity("Barranquilla", "30"))
        //dcity.add(DCity("Cartagena", "25"))



        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        viewModel.addCities()

        viewModel.getCities().observe(viewLifecycleOwner, Observer { resultcities ->
            run{
                dcitytemp = resultcities as MutableList<DCity>

                dcity.clear()
                for (city in dcitytemp){
                    dcity.add(DCity(
                        city.nombre,
                        city.temperatura
                    ))
                }
            }
            adapter!!.updateData()
        })

        adapter = RecycleViewAdapter(dcity, this)

        view.citylist.layoutManager = LinearLayoutManager(context)
        view.citylist.adapter = adapter

        //view.floatingActionButton.setOnClickListener{

        //}
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        //Quilla = DCity("Barranquilla", "30")
        //Cgena = DCity("Cartagena", "25")
    }

    override fun onListInteraction(item: DCity?) {
        //Log.d("KRecycle", "onListInteraction" + item!!.nombre)
        val bundle = bundleOf("data" to item)
        //val bundle = bundleOf("nombre" to item?.nombre)
        navController!!.navigate(R.id.action_cityList_to_temperatureList, bundle)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
