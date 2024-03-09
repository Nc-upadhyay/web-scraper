package com.nc.controller;

import com.nc.exception.RestaurantIDNotFound;
import com.nc.model.RestaurantSchema;
import com.nc.service.GrabService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class HomeController {
    // Autowiring the GrabService for accessing restaurant-related functionalities
    @Autowired
    GrabService grabService;

    // Endpoint to get the list of restaurant names
    @Tag(name = "Restaurant name list", description = "This API return the list of restaurants name")
    @RequestMapping(value = "/restaurant-name-list", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getRestaurantNameList() {
        // Returning the list of restaurant names along with HTTP status OK
        return new ResponseEntity<>(grabService.getRestaurantList(), HttpStatus.OK);
    }

    // Endpoint to get details of a specific restaurant by its ID
    @Tag(name = "Get Restaurant", description = "This API return the details of restaurant. you need to the restaurant id")
    @GetMapping(value = "/restaurant/{id}")
    public ResponseEntity<RestaurantSchema> getRestaurant(@PathVariable String id) throws RestaurantIDNotFound {
        // Returning the details of the requested restaurant along with HTTP status OK
        return new ResponseEntity<>(grabService.getRestaurant(id), HttpStatus.OK);
    }

    // Endpoint to get details of all restaurants
    @Tag(name = "Get ALL Restaurants", description = "This API return the details of all restaurant. ")
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public ResponseEntity<List<RestaurantSchema>> getRestaurantList() {
        // Returning the list of all restaurant details along with HTTP status OK
        return ResponseEntity.ok(grabService.getRestaurants());
    }
}
