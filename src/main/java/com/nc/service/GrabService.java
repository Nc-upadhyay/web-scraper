package com.nc.service;

import com.nc.exception.RestaurantIDNotFound;
import com.nc.model.RestaurantSchema;

import java.util.List;

public interface GrabService {
    List<String> getRestaurantList();

    List<RestaurantSchema> getRestaurants();

    RestaurantSchema getRestaurant(String id) throws RestaurantIDNotFound;

    void fetchDocument();

    default void saveToFile() {
        //save data on file
    }
}
