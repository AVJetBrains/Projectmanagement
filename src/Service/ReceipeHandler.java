package Service;

import entities.Ingredient;
import entities.Receipe;
import exceptions.InsufficientIngredientException;
import exceptions.InsusfficientmoneyException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceipeHandler {
    public void checkifPossibleToPrepareRecipe(Receipe receipe , List<Ingredient>ingredients){
        Map<Ingredient, Double> composition = receipe.getComposition();
        Map<Ingredient, Double> InsufficientIngredient = new HashMap<>();
        for(Ingredient ing : ingredients){
         if(composition.containsKey(ing)){
             double qtyUsed = composition.get(ing);
             if(qtyUsed > ing.getQty()){
                 InsufficientIngredient.put(ing , qtyUsed - ing.getQty());
             }
         }
        }
        if(InsufficientIngredient.size() >0) {
            throw new InsufficientIngredientException(InsufficientIngredient);
        }
    }
}
