package com.example.weatherapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DayFields {
    public static ArrayList<DTemp> getCityDays(JSONObject response) {
        ArrayList<DTemp> cityDays = new ArrayList<>();

        try {
            JSONArray list = response.getJSONArray("list");

            for (int i = 0; i < list.length(); i ++) {

                JSONObject obj = list.getJSONObject(i);

                String temperature = obj.getJSONObject("main").getString("temp") + " °C";

                int day = i+1;
                cityDays.add(new DTemp("Day " + day, temperature));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cityDays;
    }
}
