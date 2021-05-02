package com.example.weatherap;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {
    public static final String QUERY_CITY_ID_ENDPOINT = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_CITY_WEATHER_ENDPOINT = "https://www.metaweather.com/api/location/";
    final Gson gson = new Gson();

    Context context;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    // Callback interface
    public interface GetCityIdResponse {
        void onError(String errorMessage);

        void onResponse(String cityId, String title);
    }

    public void getCityId(String cityName, GetCityIdResponse getCityIdResponse) {
        String url = QUERY_CITY_ID_ENDPOINT + cityName;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject cityDetails = response.getJSONObject(0);
                    String woeid = cityDetails.getString("woeid");
                    String title = cityDetails.getString("title");
                    getCityIdResponse.onResponse(woeid, title);

                } catch (JSONException e) {
                    e.printStackTrace();
                    getCityIdResponse.onError("City not found");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getCityIdResponse.onError("Something went wrong");
            }
        });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface GetCityWeatherResponse {
        void onError(String errorMessage);

        void onResponse(List<WeatherReportModel> weather_report_list);
    }

    public void getCityWeatherById(String cityId, GetCityWeatherResponse getCityWeatherResponse) {
        String url = QUERY_CITY_WEATHER_ENDPOINT + cityId;
        List<WeatherReportModel> weather_report_list = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray consolidated_weather_list = response.getJSONArray("consolidated_weather");

                    for (int i = 0; i < consolidated_weather_list.length(); i++) {
                        WeatherReportModel day = gson.fromJson(consolidated_weather_list.getString(i), WeatherReportModel.class);
                        weather_report_list.add(day);
                    }

                    getCityWeatherResponse.onResponse(weather_report_list);

                } catch (JSONException e) {
                    e.printStackTrace();
                    getCityWeatherResponse.onError("City ID invalid");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "City ID invalid", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
