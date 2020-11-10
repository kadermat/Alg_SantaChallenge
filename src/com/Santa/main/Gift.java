package com.Santa.main;

public class Gift {

    private int giftId;
    private double latitude;
    private double longitude;
    private double weight;

    public Gift(int giftId, double latitude, double longitude, double weight) {
        this.giftId = giftId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weight = weight;
    }

    public int getGiftId() {
        return giftId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getWeight() {
        return weight;
    }
}
