package exceptions;

import entities.Ingredient;

import java.util.Map;

public class InsufficientIngredientException extends RuntimeException{
    private Map<Ingredient, Double> InsufficentIngredient;

    public InsufficientIngredientException(Map<Ingredient, Double> InsufficentIngredient) {

        this.InsufficentIngredient = InsufficentIngredient;
    }

    public Map<Ingredient, Double> getInsufficentIngredient() {
        return InsufficentIngredient;
    }
}
