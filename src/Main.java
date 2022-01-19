import Service.AccountHandler;
import Service.IngredientHandler;
import Service.ReceipeHandler;
import entities.*;
import exceptions.*;
import io.Ingredientio;
import io.Receipeio;
import io.accountIO;
import Service.RestaurantHandler;
import io.restaurantIO;
import io.LocationIO;


import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static List<Sales> salesList;
    private static List<Expense> expenseList;
    private static double availablemoney;
    private static List<Ingredient> ingredients;
    private static List<Receipe> receipeList;
    private static AccountHandler accountHandler;
    private static IngredientHandler ingredientHandler;
    private static ReceipeHandler receipeHandler;
    private static Ingredientio ingredientio;
    private static Receipeio receipeio;
    private static accountIO accountIO;
    private static List<Restaurant> restaurantList;
    private static RestaurantHandler restaurantHandler;
    private static List<Location> locationList;


    public static void main(String[] args) throws FileNotFoundException {
        salesList = new ArrayList<>();
        expenseList = new ArrayList<>();
        ingredientio = new Ingredientio();
        receipeio = new Receipeio();
        ingredientHandler = new IngredientHandler();
        accountIO = new accountIO();
        accountHandler = new AccountHandler();
        receipeHandler = new ReceipeHandler();
        restaurantHandler = new RestaurantHandler();
        restaurantList = new ArrayList<>();

        CommandType currentCommand = CommandType.No_COMMAND;
        Restaurant SelectedRestaurant = null;
        Ingredient SelectedIngredient = null;
        double IngredientQty;
        Receipe SelectedRecipe = null;
        Map<Ingredient , Double> InsufficientIngredients = null;
        Restaurant UpdatedRestaurant = null;

        ingredients = ingredientio.readIngredientList("resources/ingredients.txt");
        receipeList = receipeio.readAllReceipe("resources/receipe.txt" , ingredients);
        availablemoney = accountIO.readAccount("resources/accounts.txt");
        restaurantList = restaurantIO.readAllRestaurant("resources/Restaurant.txt");
        locationList = LocationIO.readLocationList("resources/Location.txt");

        System.out.println("Available Money is " + availablemoney);

        while(true){
            try{
            if(currentCommand == CommandType.No_COMMAND) {
                int selectNumber = displayPrompt();
                currentCommand = CommandType.values()[selectNumber];
            }
            else if(currentCommand == CommandType.SELECT_NEW_RESTAURANT){
                System.out.println("Welcome to Cafe Chain -Enter the Location Name from the given List and start Ordering");
                SelectedRestaurant = selectRestaurant();
                System.out.println(SelectedRestaurant.getName()+"-> ID: "+SelectedRestaurant.getId()+" Restaurant Successfully Selected");
                System.out.println("Welcome to "+ SelectedRestaurant.getName()+ "Restaurant");
                currentCommand = CommandType.No_COMMAND;
            }
            else if (currentCommand == CommandType.VIEW_TOTAL_SALES){
                accountHandler.printSales(salesList);
                System.out.println("Total sales of "+SelectedRestaurant.getName()+"Location: "+ SelectedRestaurant.getLocation());
                currentCommand = CommandType.No_COMMAND;
            }
            else if (currentCommand == CommandType.VIEW_TOTAL_EXPENSE){
                accountHandler.printExpenses(expenseList);
                currentCommand = CommandType.No_COMMAND;
            }
            else if (currentCommand == CommandType.VIEW_NET_PROFIT){
                accountHandler.printProfit(salesList , expenseList);
                currentCommand = CommandType.No_COMMAND;
            }
            else if (currentCommand == CommandType.VIEW_AVAILABLE_INGREDIENTS){
                ingredientHandler.viewIngredients(ingredients);
                currentCommand = CommandType.No_COMMAND;
            }
            else if(currentCommand == CommandType.ORDER_SPECIFIC_INGREDIENTS){
                SelectedIngredient = selectIngredient();
                currentCommand = CommandType.INPUT_INGREDIENT_QTY;
            }
            else if(currentCommand == CommandType.INPUT_INGREDIENT_QTY){
                IngredientQty = inputIngredientOty();
                if(ingredientHandler.isPossibleToOrderIngredient(SelectedIngredient,IngredientQty,availablemoney)){
                    System.out.println("Ordered Succesfully");
                    updateIngredientQty(SelectedIngredient ,IngredientQty);
                    currentCommand = CommandType.No_COMMAND;
                }
                throw new InsusfficientmoneyException();
            }
            else if (currentCommand == CommandType.PLACE_ORDER){
                if(SelectedRestaurant == null){
                    System.out.println("Please Choose a restaurant and then start ordering");
                    currentCommand = CommandType.No_COMMAND;
                }
                else {
                    System.out.println("Place your order for " + SelectedRestaurant.getName()+" Restaurant at "+SelectedRestaurant.getLocation());
                    SelectedRecipe = selectReceipe();
                    receipeHandler.checkifPossibleToPrepareRecipe(SelectedRecipe, ingredients);
                    currentCommand = CommandType.FINALISE_ORDER;
                }
            }
            else if(currentCommand == CommandType.ORDER_MULTIPLE_INGREDIENTS){
                ingredientHandler.isPossibleToOrderIngredients(InsufficientIngredients , availablemoney);
                purchaseIngredients(InsufficientIngredients);
                currentCommand = CommandType.FINALISE_ORDER;
            }
            else if(currentCommand == CommandType.FINALISE_ORDER){
                finalizeOrder(SelectedRecipe);
                System.out.println("Order for "+ SelectedRecipe.getName()+" "+ " is finalized and ready to serve!");
                currentCommand = CommandType.No_COMMAND;
            }
            else if(currentCommand == CommandType.OPEN_NEW_RESTAURANT){
                restaurantHandler.OpenNewRestaurant(restaurantList);
                System.out.println("Restaurent Succesfully Opened");
                System.out.println(restaurantList);
                currentCommand = CommandType.No_COMMAND;
            }
            if(currentCommand == CommandType.EXIT){
             System.exit(0);
            }
        }
            catch(InsufficientIngredientException ex){
                InsufficientIngredients = ex.getInsufficentIngredient();
                currentCommand = CommandType.ORDER_MULTIPLE_INGREDIENTS;

            }
            catch(InsusfficientmoneyException ex){
                System.out.println(ex.getMessage());
                currentCommand = CommandType.No_COMMAND;
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public static int displayPrompt(){
        System.out.println("Please select one of the following commands ");
        System.out.println("1. Select a Restaurant");
        System.out.println("2. View Available Ingredients");
        System.out.println("3. View Total Sales ");
        System.out.println("4. View Total Expense ");
        System.out.println("5. View Net Profit");
        System.out.println("6. Place Order");
        System.out.println("7. Order Specific Ingredients");
        System.out.println("8. Open New Restaurant");
        System.out.println("9. Exit the Program");

        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static Ingredient selectIngredient(){
        System.out.println("Please enter the Ingredient you want to order: ");
        Scanner scanner = new Scanner(System.in);
        String ingredientname = scanner.nextLine();

        for(int i = 0 ; i < ingredients.size() ;i++){
            if(ingredients.get(i).getName().equals(ingredientname)){
                return ingredients.get(i);
            }
        }
        throw new IngredientNotFoundException(String.format("Ingredient" + ingredientname+"Not found"));
    }
    public static Receipe selectReceipe(){
        System.out.println("Please enter the Receipe you want to order: ");
        Scanner scanner = new Scanner(System.in);
        String receipeName = scanner.nextLine();
        for(int i = 0 ; i<receipeList.size() ; i++){
            if(receipeList.get(i).getName().equals(receipeName)){
                return receipeList.get(i);
            }
        }
        throw new ReciepeNotFoundException("Receipe Not found");
    }


    public static double inputIngredientOty(){
        Scanner scanner  = new Scanner(System.in);
        return scanner.nextDouble();
    }

    public static void purchaseIngredient(Ingredient ingredientOrdered , double qtyOrdered){
        for(int i =0 ; i<ingredients.size() ; i++){
            if(ingredients.get(i).getName().equals(ingredientOrdered.getName())){
                double oldQty = ingredients.get(i).getQty();
                ingredients.get(i).setQty(oldQty+qtyOrdered);
            }
        }
        double totalAmount = ingredientOrdered.getRate()*qtyOrdered;
        Map<Ingredient, Double> composition = new HashMap<>();
        composition.put(ingredientOrdered,qtyOrdered);
        PurchaseOrder po = new PurchaseOrder(totalAmount, composition);
        expenseList.add(new Expense(totalAmount,po, ExpenseType.PURCHASE));
        availablemoney -= totalAmount;
    }

    public static void purchaseIngredients(Map<Ingredient , Double> ingredientsToOrder){
        double moneyspent = 0.0;
        for(int i = 0 ; i< ingredients.size() ; i++){
            if(ingredientsToOrder.containsKey(ingredients.get(i))){
                double oldqty = ingredients.get(i).getQty();
                double qtyToorder = ingredientsToOrder.get(ingredients.get(i));
                moneyspent += qtyToorder*ingredients.get(i).getRate();
                ingredients.get(i).setQty(oldqty + qtyToorder);
            }
        }
        PurchaseOrder po = new PurchaseOrder(moneyspent , ingredientsToOrder);
        Expense expense = new Expense(moneyspent , po , ExpenseType.PURCHASE);
        expenseList.add(expense);
        availablemoney -= moneyspent;
    }
    public static void finalizeOrder(Receipe receipe){
        Map<Ingredient , Double> composition = receipe.getComposition();

        for(int i = 0 ; i< ingredients.size(); i++){
            Ingredient currentIngredient = ingredients.get(i);
            if(composition.containsKey(currentIngredient)){
                double oldqty = currentIngredient.getQty();
                ingredients.get(i).setQty(oldqty - composition.get(currentIngredient));
            }
        }
        Order order = new Order(receipe , receipe.getAmount());
        Sales sales = new Sales(receipe.getAmount() , order);

        salesList.add(sales);
        availablemoney += receipe.getAmount();
    }

    public static void updateIngredientQty(Ingredient ingredientordered , double QtyOrdered){
        for(int i = 0 ; i<ingredients.size(); i++){
         if(ingredients.get(i).getName().equals(ingredientordered.getName())){
             double oldQty =  ingredients.get(i).getQty();
             ingredients.get(i).setQty(oldQty + QtyOrdered);
         }
        }
    }

    public static Restaurant selectRestaurant(){
        System.out.println(restaurantList);
        Scanner sc = new Scanner(System.in);
        String restaurantName = sc.nextLine();
        for(int i =0 ; i<restaurantList.size() ; i++){
            if(restaurantList.get(i).getLocation().equals(restaurantName)){
                return restaurantList.get(i);
            }
        }
        throw new RestaurantNotFoundException(restaurantName +" "+"Not found");
    }

    public static Location selectLocation(){
        Scanner sc = new Scanner(System.in);
        String LocationName = sc.nextLine();
        for (int i = 0 ; i< locationList.size(); i++){
            if(locationList.get(i).getCity().equals(LocationName)){
                return locationList.get(i);
            }
        }
        throw new RestaurantNotFoundException("Location not specified");

    }

    public static List<Restaurant> UpdateRestaurantDetails(String Name , String location , double ID){
        restaurantList.add(new Restaurant(Name , location , ID));
        return restaurantList;
    }
}
