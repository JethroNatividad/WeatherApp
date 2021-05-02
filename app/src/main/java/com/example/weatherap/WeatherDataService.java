package com.example.weatherap;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {
    public static final String QUERY_CITY_ID_ENDPOINT = "https://www.metaweather.com/api/location/search/?query=";

    Context context;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    // Callback interface
    public interface WeatherDataServiceListener {
        void onError(String errorMessage);

        void onResponse(String cityId, String title);
    }

    public void getCityId(String cityName, WeatherDataServiceListener weatherDataServiceListener) {
        String url = QUERY_CITY_ID_ENDPOINT + cityName;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() == 0) {
                    weatherDataServiceListener.onError("City not found");
                } else {
                    try {
                        JSONObject cityDetails = response.getJSONObject(0);
                        String woeid = cityDetails.getString("woeid");
                        String title = cityDetails.getString("title");
                        weatherDataServiceListener.onResponse(woeid, title);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        weatherDataServiceListener.onError("Something went wrong");
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                weatherDataServiceListener.onError("Something went wrong");
            }
        });

        // Add the request to the RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
