package io;

import entities.Ingredient;
import entities.Location;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LocationIO<lines> {

    public static List<Location> readLocationList(String filePath) throws FileNotFoundException {

        List<String> lines = CustomFileReader.readFile(filePath);
        List<Location> result = new ArrayList<>();

        for (String line : lines) {
            String[] splitline = line.split(" ");
            result.add(new Location(splitline[0] + " ", splitline[1]));
        }
        System.out.println("Read " + lines.size() + "Location");
        return result;
    }
}
