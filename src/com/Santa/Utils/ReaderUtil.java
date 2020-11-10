package com.Santa.Utils;

import com.Santa.main.Gift;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ReaderUtil {


    public static List<Gift> readGifts(){

        String pathToGift = "data" + java.io.File.separator + "gifts.csv";

        try (BufferedReader tspFileReader = Files.newBufferedReader(Paths.get(pathToGift), Charset.forName("UTF-8"))) {
            List<String> lines = Files.readAllLines(Paths.get(pathToGift));
            //List<String> header = lines.subList(0, 2);



            List<Gift> giftList = lines.subList(1, lines.size()).stream().map(line -> line.split(","))
                    .map(splits -> new Gift(Integer.parseInt(splits[0]), Double.parseDouble(splits[1]), Double.parseDouble(splits[2]), Double.parseDouble(splits[3])))
                    .collect(Collectors.toList());

            return giftList;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }







}
