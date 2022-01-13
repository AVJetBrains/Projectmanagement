package io;

import entities.Ingredient;
import entities.Receipe;
import exceptions.InvalidIngredientException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receipeio {

    public List<Receipe> readAllReceipe(String filepath , List<Ingredient> ingredientList) throws FileNotFoundException {
        List<String> lines = CustomFileReader.readFile(filepath);
        List<Receipe> receipeList = new ArrayList<>();

        for (String line : lines) {
            String[] splitline = line.split(" ");
            String receipeName = splitline[0];
            double amount  = Double.parseDouble(splitline[1]);
            Map<Ingredient , Double> composition = new HashMap<>();

            for(int i =2 ; i< splitline.length ; i+=2){
                String ingredientname = splitline[i];
                double qty = Double.parseDouble(splitline[i+1]);

                boolean flag = false;

                for(int j = 0 ; j<ingredientList.size(); j++){
                    if(ingredientList.get(j).getName().equals(ingredientname)){
                        flag = true;
                        composition.put(ingredientList.get(i) , qty);
                        break;
                    }
                }
                if(flag = false){
                    throw new InvalidIngredientException("Ingredient "+ ingredientname + "Not found");
                }
            }
            Receipe receipe = new Receipe(receipeName , composition , amount);
            receipeList.add(receipe);
        }
        System.out.println("Read "+ receipeList.size() +"receipes");
        return receipeList;
    }
}
