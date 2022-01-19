package io;

import entities.Restaurant;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class restaurantIO {

    public static List<Restaurant> readAllRestaurant(String filepath) throws FileNotFoundException {
        List<String> lines = CustomFileReader.readFile(filepath);
        List<Restaurant> result = new ArrayList<>();

        for(String line :lines){
            String[] splitline = line.split(" ");
            String Name = splitline[0];
            String Location = splitline[1];
            double ID  = Double.parseDouble(splitline[2]);
            result.add(new Restaurant(splitline[0]+" " , (splitline[1]) ,  Double.parseDouble(splitline[2])));
        }
        System.out.println("Read "+ lines.size()+ "Restaurants");
        return result;
    }
}
