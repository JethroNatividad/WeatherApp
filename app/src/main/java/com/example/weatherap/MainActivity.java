package com.example.weatherap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btn_getCityId, btn_getCityWeatherById, btn_getCityWeatherByName;
    EditText et_input;
    ListView lv_weatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherDataService weatherDataService = new WeatherDataService(this);
        // assign values
        btn_getCityId = findViewById(R.id.btn_getCityId);
        btn_getCityWeatherById = findViewById(R.id.btn_getCityWeatherById);
        btn_getCityWeatherByName = findViewById(R.id.btn_getCityWeatherByName);

        et_input = findViewById(R.id.et_input);
        lv_weatherList = findViewById(R.id.lv_weatherList);


        // get city id
        btn_getCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = et_input.getText().toString();
                weatherDataService.getCityId(input, new WeatherDataService.WeatherDataServiceListener() {
                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityId, String title) {
                        Toast.makeText(MainActivity.this, "Country ID of " + title + " is " + cityId, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        // get weather by id
        btn_getCityWeatherById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, et_input.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // get weather by name
        btn_getCityWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, et_input.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
