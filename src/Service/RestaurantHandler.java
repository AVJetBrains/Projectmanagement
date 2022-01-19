package Service;

import entities.Ingredient;
import entities.Restaurant;
import entities.Sales;

import java.util.List;
import java.util.Scanner;


public class RestaurantHandler{

    public void OpenNewRestaurant(List<Restaurant>restaurants){

        Scanner sc = new Scanner(System.in);

        System.out.println("Please Enter the Name of your New Restaurant");
        String Name = sc.nextLine();

        System.out.println("Please Enter the Location of your New Restaurant");
        String Location = sc.nextLine();

        System.out.println("Please Enter the ID for your New Restaurant");
        double ID = sc.nextDouble();

        restaurants.add(new Restaurant(Name  , Location , ID));

        }

    public void ChooseYourRestaurant(List<Restaurant> restaurantList) {
        Scanner sc = new Scanner(System.in);
        int ID = sc.nextInt();
        for (Restaurant restaurant : restaurantList) {
            if (ID == 102) {
                System.out.println("Welcome to Restaurant of NewYork");
            } else if (ID == 103) {

                System.out.println("Welcome to Restaurant of Madrid");
            } else if (ID == 104) {
                System.out.println("Welcome to Restaurant of Chicago");
            }
        }
    }

    public void StartOrdering(List<Sales> salesList , List<Ingredient> ingredientList){


    }

}
