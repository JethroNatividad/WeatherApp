package com.example.weatherap;

public class WeatherReportModel {
    private Long id;
    private String weather_state_name;
    private String weather_state_abbr;
    private String wind_direction_compass;
    private String created;
    private String applicable_date;
    private Float min_temp;
    private Float max_temp;
    private Float the_temp;
    private Float wind_speed;
    private Float wind_direction;
    private Float air_pressure;
    private Float humidity;
    private Float visibility;
    private Float predictability;

    public WeatherReportModel(Integer id, String weather_state_name, String weather_state_abbr, String wind_direction_compass, String created, String applicable_date, Float min_temp, Float max_temp, Float the_temp, Float wind_speed, Float wind_direction, Float air_pressure, Float humidity, Float visibility, Float predictability) {
        this.id = Long.parseLong(String.valueOf(id));
        this.weather_state_name = weather_state_name;
        this.weather_state_abbr = weather_state_abbr;
        this.wind_direction_compass = wind_direction_compass;
        this.created = created;
        this.applicable_date = applicable_date;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.the_temp = the_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictability = predictability;
    }

    @Override
    public String toString() {
        return
                "Date:'" + applicable_date + '\'' +
                "\nWeather type:'" + weather_state_name + '\'' +
                "\nTemperature:" + the_temp +
                "\nHumidity:" + humidity +
                "\nPossibility:" + predictability;
    }

}
