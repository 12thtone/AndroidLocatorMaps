package com.mmmd.maher.androidlocator.services;

import com.mmmd.maher.androidlocator.model.Places;

import java.util.ArrayList;

/**
 * Created by maher on 7/1/16.
 */
public class DataService {
    private static DataService instance = new DataService();

    public static DataService getInstance() {

        return instance;
    }

    private DataService() {

    }

    public ArrayList<Places> getPlacesWithin10MilesOfZip(int zipcode) {
        // Get data from internet

        ArrayList<Places> list = new ArrayList<>();
        list.add(new Places(34.7408873f, -84.08016129999999f, "Chattahoochee-Oconee National Forest", "Suches, GA 30572", "slo"));
        list.add(new Places(34.1549103f, -84.70229440000003f, "Red Top Mountain State Park", "50 Lodge Rd SE, Cartersville, GA 30121", "slo"));
        list.add(new Places(34.9107078f, -83.41160939999997f, "Black Rock Mountain State Park", "3085 Black Rock Mountain Pkwy, Mountain City, GA 30562", "slo"));
        list.add(new Places(31.9651636f, -83.9152211f, "Georgia Veterans State Park", "2459 U.S. 280, Cordele, GA 31015", "slo"));

        return list;
    }
}
