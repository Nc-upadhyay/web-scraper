package com.nc.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantSchema {
    private String restaurantId;
    private String restaurantName;
    private String restaurantCuisine;
    private String restaurantRating;
    private String estimatedDeliveryTime; // in minutes
    private String restaurantDistance; // in kilometers
    private String promotionalOffers;
    private String restaurantNotice;
    private String imageLink;
    private boolean isPromoAvailable;
    private double latitude;
    private double longitude;
    private double estimatedDeliveryFee;

    public void setIsPromoAvailable(boolean promo) {
        this.isPromoAvailable = promo;
    }

    @Override
    public String toString() {
        return " RestaurantSchema \n { \n" +
                "  restaurantId= " + restaurantId + "\n" +
                "  restaurantName= " + restaurantName + "\n" +
                "  restaurantCuisine= " + restaurantCuisine + "\n" +
                "  restaurantRating= " + restaurantRating + "\n" +
                "  estimatedDeliveryTime= " + estimatedDeliveryTime + "\n" +
                "  restaurantDistance= " + restaurantDistance + "\n" +
                "  promotionalOffers= " + promotionalOffers + "\n" +
                "  restaurantNotice= " + restaurantNotice + "\n" +
                "  imageLink= " + imageLink + "\n" +
                "  isPromoAvailable= " + isPromoAvailable + "\n" +
                "  latitude= " + latitude + "\n" +
                "  longitude= " + longitude + "\n" +
                "  estimatedDeliveryFee= " + estimatedDeliveryFee + "\n" +
                " } \n";
    }
}
