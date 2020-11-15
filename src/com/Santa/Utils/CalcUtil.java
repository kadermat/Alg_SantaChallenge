package com.Santa.Utils;

public class CalcUtil {
    private static final int EARTH_RADIUS = 6371; // Radius of the earth in km

    /**
     * Calculates the Haversine distance between two points
     * @param latitude1 Latitude of point1 in degrees [-90 ... 90]
     * @param longitude1 Longitude of point1 in degrees
     * @param latitude2 Latitude of point2 in degrees
     * @param longitude2 Longitude of point2 in degrees
     * @return Haversine Distance in km
     */
    public static double getHaversineDistance(double latitude1, double longitude1, double latitude2, double longitude2){
        double latitudeDistance = toRad(latitude2 - latitude1);
        double longitudeDistance = toRad(longitude2 - longitude1);
        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2) +
                Math.cos(toRad(latitude1)) * Math.cos(toRad(latitude2)) *
                        Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return  EARTH_RADIUS * c;
    }

    public static double getHaversineDistanceToNorthPole(double latitude, double longitude){
        return getHaversineDistance(90,0, latitude, longitude );

    }


    private static Double toRad(double value) {
        return value * Math.PI / 180;
    }
}
