package com.mmmd.maher.androidlocator.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.mmmd.maher.androidlocator.R;
import com.mmmd.maher.androidlocator.fragments.MainFragmet;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    final int PERMISSION_LOCATION = 123;
    private GoogleApiClient mGoogleApiClient;
    private MainFragmet mainFragmet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addConnectionCallbacks(this).addApi(LocationServices.API).build();

        mainFragmet = (MainFragmet)getSupportFragmentManager().findFragmentById(R.id.container_main);

        if (mainFragmet == null) {
            mainFragmet = MainFragmet.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container_main, mainFragmet).commit();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);

            Log.v("MY_MAPS", "Requestion Permissions");
        } else {
            Log.v("MY_MAPS", "Starting Locations Services from onConnected");
            startLocationServices();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v("MY_MAPS", "Lat: " + location.getLatitude() + " - Long: " + location.getLongitude());
        mainFragmet.setUserMarker(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationServices();
                    Log.v("MY_MAPS", "Permission granted - starting services");
                } else {
                    // Toast to enable location
                    Log.v("MY_MAPS", "Permission Denied");
                }
            }
        }
    }

    public void startLocationServices() {
        Log.v("MY_MAPS", "Starting Locations Services Called");

        try {
            LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);
            Log.v("MY_MAPS", "Requesting location updates");
        } catch (SecurityException exception) {
            // Toast to enable location
            Log.v("MY_MAPS", exception.toString());
        }
    }
}
