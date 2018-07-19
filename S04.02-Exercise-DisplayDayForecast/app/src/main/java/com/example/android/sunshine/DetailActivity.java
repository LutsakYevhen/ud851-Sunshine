package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private static final String INTENT_KEY_FROM_MAINACTIVITY = "weatherForDay";
    private TextView mWeatherData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherData = (TextView) findViewById(R.id.text_from_main);

        mWeatherData.setText(getIntent().getStringExtra(INTENT_KEY_FROM_MAINACTIVITY));
    }
}