package com.Santa.Heuristics;

import com.Santa.Utils.CalcUtil;
import com.Santa.main.Gift;

import java.util.ArrayList;
import java.util.List;

public class Greedy {
    public static double solve(List<Gift> giftLocationList){
        // Initialize weariness
        double totalWeariness = 0;

        // Copy the list and add all its elements
        List <Gift> giftToCompute = new ArrayList<>(giftLocationList);

        // While there is gift to compute
        while(!giftToCompute.isEmpty()) {
            // Create a new journey
            List <Gift> journey = new ArrayList<>();
            // Add the first item to compute, and remove it from the waiting list
            journey.add(giftToCompute.get(0));
            giftToCompute.remove(0);

            boolean canAdd = true;
            while(canAdd){
                // We find the nearest gift from the position and check the weight
                Gift next = nearestGift(journey.get(journey.size() - 1), 1000 - journeyWeight(journey), giftToCompute);
                // If we found one, we add it to the journey and remove it from the waiting list
                if(next != null) {
                    giftToCompute.remove(next);
                    journey.add(next);
                } // Else we can stop for this journey
                else canAdd = false;
            }
            // We calculate the weariness of this journey and add it to the total
            totalWeariness += getWeariness(journey);
            // We display the percentage of progress
            System.out.printf("Progress: %6.2f%%.%n", (float)(giftLocationList.size() - giftToCompute.size()) / giftLocationList.size() * 100);
        }
        return totalWeariness;
    }

    /**
     * Browse the list and return the nearest gift to the input one
     * @param gift Start point
     * @param maxWeight Remaining space
     * @param searchList List to search the nearest gift
     * @return The nearest gift that fits
     */
    private static Gift nearestGift(Gift gift, double maxWeight, List<Gift> searchList){
        // We initialize with "infinite"
        int minDistance = Integer.MAX_VALUE;
        Gift nearest = null;
        for(Gift current : searchList){
            // If we find a better distance and there is enough space for this gift, we take this better choice
            if(CalcUtil.getHaversineDistance(gift.getLatitude(), gift.getLongitude(), current.getLatitude(), current.getLongitude()) < minDistance && current.getWeight() <= maxWeight){
                nearest = current;
            }
        }
        return nearest;
    }

    /**
     * Compute the weight of a journey (sum of the gift weight for this journey)
     * @param journey List of gift to browse
     * @return The sum of all weight
     */
    private static double journeyWeight(List<Gift> journey){
        double weight = 0;
        for(Gift gift: journey){
            weight += gift.getWeight();
        }
        return weight;
    }

    /**
     * Compute the weariness of a journey
     * @param journey The journey to browse
     * @return Weariness of this journey
     */
    private static double getWeariness(List<Gift> journey){
        // We initialize with the weariness from north pole to the first point, and from last point to the north pole
        double weariness = CalcUtil.getHaversineDistanceToNorthPole(journey.get(0).getLatitude(), journey.get(0).getLongitude()) * (10 + journeyWeight(journey)) + CalcUtil.getHaversineDistanceToNorthPole(journey.get(journey.size() - 1).getLatitude(), journey.get(journey.size() - 1).getLongitude()) * 10;

        // While we have gift to deliver
        while(journey.size() > 1){
            Gift previous = journey.get(0);
            journey.remove(0);
            // We calculate weariness from the previous to the current
            weariness += CalcUtil.getHaversineDistance(previous.getLatitude(), previous.getLongitude(), journey.get(0).getLatitude(), journey.get(0).getLongitude()) * (10 + journeyWeight(journey));
        }
        return weariness;
    }
}
