package com.Santa.main;

import com.Santa.Heuristics.SimpleSolver;
import com.Santa.Utils.CalcUtil;
import com.Santa.Utils.ReaderUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        List<Gift> giftLocationList = ReaderUtil.readGiftLocations();

        double weariness = SimpleSolver.solve(giftLocationList);
        System.out.printf("Weariness: %f\n ", weariness );



    }
}
