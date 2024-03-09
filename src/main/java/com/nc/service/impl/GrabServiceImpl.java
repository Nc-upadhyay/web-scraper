package com.nc.service.impl;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.exception.RestaurantIDNotFound;
import com.nc.model.RestaurantSchema;
import com.nc.service.GrabService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GrabServiceImpl implements GrabService {
    // Define the URL of the Grab Food Delivery website
    public final String URL = "https://food.grab.com/sg/en/cuisines/promos-delivery/10566";
    // File path, In this file data is store after fetching from web.
    public final String filePath = "E:\\Project\\spring-boot-project\\scraper-to-scrape-Grab-Food-Delivery\\src\\main\\resources\\static\\data.txt";
    // Logger for logging messages
    private final static Logger logger = Logger.getLogger("GrabServiceImpl");
    // List to store names of restaurants
    private List<String> listOfRestaurant = new ArrayList<>();
    // List to store restaurant details
    private List<RestaurantSchema> restaurantSchemas = new ArrayList<>();

    // Method to fetch the HTML document of the website
    @Override
    public void fetchDocument() {
        try {
            // Connect to the website and retrieve the HTML document
            Document document = Jsoup.connect(URL).get();
            // Extracting main content div
            Elements restaurants = document.select("div.ant-layout");
            // Extracting title of the website
            String title = restaurants.select("h1.title___dCI3A").select("span").text();
            // Extracting individual restaurant elements
            Elements listOfRestaurants = restaurants.select("div.ant-layout")
                    .select("div.ant-row-flex.RestaurantListRow___1SbZY")
                    .select("div.ant-col-24.RestaurantListCol___1FZ8V.ant-col-md-12.ant-col-lg-6");

            // Iterating through each restaurant element
            for (Element restaurant : listOfRestaurants) {
                // Extracting restaurant name
                String restaurant_name = restaurant.select("a")
                        .select("div.ant-row-flex.asList___1ZNTr")
                        .select("div.ant-col-24.ant-col-lg-24")
                        .select("h2").text();
                // Adding restaurant name to the list
                listOfRestaurant.add(restaurant_name);
                // Extracting restaurant ID from href tag
                String id_Href = restaurant.select("a").attr("href");
                String restaurant_id = extractIdFromHrefTag(id_Href);

                // Extracting various details of the restaurant
                Elements external_div = restaurant.select("a")
                        .select("div.ant-row-flex.ant-row-flex-start.ant-row-flex-top.asList___1ZNTr")
                        .select("div.ant-col-24.colInfo___3iLqj.ant-col-md-24.ant-col-lg-24");
                String restaurantCuisine = external_div
                        .select("div.basicInfoContainer___1DcNs")
                        .select("div.basicInfoRow___UZM8d.cuisine___T2tCh").text();
                String restaurantRating = external_div.select("div.basicInfoRow___UZM8d.numbers___2xZGn")
                        .select("div.numbersChild___2qKMV:first-child").text();
                String estimateTimeOfDelivery = external_div.select("div.basicInfoRow___UZM8d.numbers___2xZGn")
                        .select("div.numbersChild___2qKMV:last-child ").text();
                String[] str = getDeliveryTimeAndDistance(estimateTimeOfDelivery);
                String estimatedTime = "";
                String distance = "";
                try {
                    estimatedTime = str[0];
                    distance = str[1];
                } catch (Exception e) {
                    estimatedTime = "-";
                    distance = "-";
                    e.printStackTrace();
                }
                String promotionalOffers = external_div.select("div.basicInfoRow___UZM8d.discount___3h-0m")
                        .select("span").text();
                String restaurantNotice = " check if it visible";
                String imageUrl = restaurant.select("a")
                        .select("div.ant-row-flex.ant-row-flex-start.ant-row-flex-top.asList___1ZNTr").
                        select("div.ant-col-24.colPhoto___3vb-o.ant-col-md-24.ant-col-lg-24").
                        select("div.promoTag___IYhfm").
                        select("div.placeholder___1xbBh.restoPhoto___3nncy").
                        select("img.realImage___2TyNE").attr("alt");
                String promo = restaurant.select("a")
                        .select("div.ant-row-flex.ant-row-flex-start.ant-row-flex-top.asList___1ZNTr").
                        select("div.ant-col-24.colPhoto___3vb-o.ant-col-md-24.ant-col-lg-24").
                        select("div.promoTag___IYhfm").select("div.promoTagHead___1bjRG").text();
                // Creating RestaurantSchema object and setting details
                RestaurantSchema restaurantSchema = new RestaurantSchema();
                try {
                    getCoordinate("https://food.grab.com" + id_Href, restaurantSchema);// setting the coordinate
                } catch (Exception e) {
                    e.getMessage();
                }
                restaurantSchema.setRestaurantId(restaurant_id);
                restaurantSchema.setRestaurantName(restaurant_name);
                restaurantSchema.setRestaurantCuisine(restaurantCuisine);
                restaurantSchema.setRestaurantRating(restaurantRating);
                restaurantSchema.setEstimatedDeliveryTime(estimatedTime);
                restaurantSchema.setRestaurantDistance(distance);
                restaurantSchema.setPromotionalOffers(promotionalOffers);
//                restaurantSchema.setImageLink(id_Href);
                restaurantSchema.setRestaurantNotice("false");
                restaurantSchema.setIsPromoAvailable(getPromoIsAvailable((promo)));
                // Adding RestaurantSchema object to the list
                restaurantSchemas.add(restaurantSchema);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveResultInJsonFormat();
    }


    // Method to check if promo is available
    private boolean getPromoIsAvailable(String promo) {
        try {
            return promo.length() > 1 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to extract restaurant ID from href tag
    public String extractIdFromHrefTag(String str) {
        String id = "_";
        try {
            String[] arr = str.split("/");
            id = arr[arr.length - 1];
            id = id.substring(0, id.length() - 1);
        } catch (Exception e) {
            id = "-";
        }
        return id;
    }

    // Method to get the list of restaurant names
    @Override
    public List<String> getRestaurantList() {
        return listOfRestaurant;
    }

    // Method to get the list of restaurant details
    @Override
    public List<RestaurantSchema> getRestaurants() {
        return restaurantSchemas;
    }

    // Method to get details of a specific restaurant by its ID
    @Override
    public RestaurantSchema getRestaurant(String id) throws RestaurantIDNotFound {
        for (var res : restaurantSchemas)
            if (res.getRestaurantId().trim().equals(id.trim()))
                return res;

        throw new RestaurantIDNotFound("Restaurant id not found");
    }

    // Method to split delivery time and distance
    String[] getDeliveryTimeAndDistance(String str) {
        String[] parts = str.split("•");
        return parts;
    }

    public void saveResultInJsonFormat() {
        try {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(writer);
            for (RestaurantSchema line : restaurantSchemas) {
                bw.write(String.valueOf(line));
                bw.newLine();
            }
            System.out.println("Restaurant data has been saved to " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Failed to save restaurant data to " + filePath);
        }
    }

    public void getCoordinate(String url, RestaurantSchema restaurantSchema) {
        try {
            Document document = Jsoup.connect(url).get();
            Element element = document.getElementById("__NEXT_DATA__");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(element.data());
            // Navigate to the geolocation object
            JsonNode geolocationNode = root.path("props").path("ssrState");
//          JsonNode imageLinkNode = root.path("props").path("ssrState").path("countryConfig").path("seo");

            // Get the longitude value
            double longitude = geolocationNode.path("geolocation").path("longitude").asDouble();
            double latitude = geolocationNode.path("geolocation").path("latitude").asDouble();
            //Get the image link
            String imageLink = geolocationNode.path("countryConfig").path("restaurants").path("download_img").toString();
            restaurantSchema.setLatitude(latitude);
            restaurantSchema.setLongitude(longitude);
            if (!imageLink.isEmpty())
                restaurantSchema.setImageLink("https://food.grab.com/sg/en/" + imageLink.substring(2, imageLink.length() - 1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

