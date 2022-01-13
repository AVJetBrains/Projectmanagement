package io;

import entities.Ingredient;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Ingredientio {

    public List<Ingredient> readIngredientList(String filePath) throws FileNotFoundException , NumberFormatException {
        List<String> lines = CustomFileReader.readFile(filePath);
        List<Ingredient> result = new ArrayList<>();

        for(String line :lines){
            String[] splitline = line.split(" ");
            result.add(new Ingredient(splitline[0]+" " , Double.parseDouble(splitline[1]) ,  Double.parseDouble(splitline[2])));
        }
        System.out.println("Read "+ lines.size()+ "Ingredients");
        return result;
    }
}
