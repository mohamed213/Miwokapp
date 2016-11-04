package com.example.osama.miwokapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager myview= (ViewPager) findViewById(R.id.viewpager);
        Viewpager adapter= new Viewpager(getSupportFragmentManager());
        myview.setAdapter(adapter);

    }
}
