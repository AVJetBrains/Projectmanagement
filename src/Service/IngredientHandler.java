package Service;

import entities.Ingredient;
import exceptions.InsusfficientmoneyException;

import java.util.List;
import java.util.Map;

public class IngredientHandler {
    public void viewIngredients(List<Ingredient> ingredientList){
        for(Ingredient ingredient : ingredientList){
            System.out.println(ingredient.toString());
        }
    }
    public boolean isPossibleToOrderIngredient(Ingredient ingredient , double qty , double availableMoney){
        if(availableMoney>= ingredient.getRate()*qty){
            return true;
        }
        else{
            return false;
        }
    }

    public void isPossibleToOrderIngredients(Map<Ingredient , Double> ingredientsToorder ,double availableMoney){
        double moneyRequired = 0;

        for(Ingredient ingredient : ingredientsToorder.keySet()){
            double qtytoOrder = ingredientsToorder.get(ingredient);
            double rate = ingredient.getRate();

            moneyRequired += rate*qtytoOrder;
        }

        if(availableMoney < moneyRequired){
            throw new InsusfficientmoneyException();
        }
    }
}
