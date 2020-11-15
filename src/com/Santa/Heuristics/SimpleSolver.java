package com.Santa.Heuristics;

import com.Santa.Utils.CalcUtil;
import com.Santa.main.Gift;

import java.util.List;

public class SimpleSolver {


    /**
     * Calculates the weariness of the reindeers while transporting one present at a time
     * @param giftLocationList List of Gift.
     * @return Weariness on the reindeers
     */
    public static double solve(List<Gift> giftLocationList){
        double totalWeariness = 0;
        for (Gift gift : giftLocationList) {
            double distance = CalcUtil.getHaversineDistanceToNorthPole(gift.getLatitude(), gift.getLongitude());
            totalWeariness += (10 + gift.getWeight()) * distance; // Weariness to Gift Location
            totalWeariness += 10 *distance; // Weariness back to North Pole
        }
        return totalWeariness;
    }

}
