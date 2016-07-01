package com.mmmd.maher.androidlocator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.mmmd.maher.androidlocator.fragments.MainFragmet;

public class MapsActivity extends FragmentActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MainFragmet mainFragmet = (MainFragmet)getSupportFragmentManager().findFragmentById(R.id.container_main);

        if (mainFragmet == null) {
            mainFragmet = MainFragmet.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container_main, mainFragmet).commit();
        }
    }

}
