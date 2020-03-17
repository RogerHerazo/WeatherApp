package com.example.weatherapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CityFields {

    public static ArrayList<DCity> getCities(JSONObject response) {
        ArrayList<DCity> cities = new ArrayList<>();

        try {
            JSONArray list = response.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {

                JSONObject obj = list.getJSONObject(i);

                String name = obj.getString("name");
                String temperature = obj.getJSONObject("main").getString("temp") + " °C";


                cities.add(new DCity(name,temperature));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cities;
    }

}
