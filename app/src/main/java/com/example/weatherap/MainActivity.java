package com.example.weatherap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_getCityId, btn_getCityWeatherById, btn_getCityWeatherByName;
    EditText et_input;
    ListView lv_weatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Toast.makeText(MainActivity.this, et_input.getText().toString(), Toast.LENGTH_SHORT).show();
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
