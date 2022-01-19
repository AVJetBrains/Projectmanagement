package Service;

import entities.Expense;
import entities.Sales;

import java.util.List;

public class AccountHandler {

    public void printSales(List<Sales>salesList){
        System.out.println("Total no of sales done so far is "+ salesList.size());
        for(Sales sales : salesList){
            System.out.println(sales.toString());
        }
    }

    public void printExpenses(List<Expense>expenseList){
        System.out.println("Total no of Expenses done so far is "+ expenseList.size());
        for(Expense expense : expenseList){
            System.out.println(expense.toString());
        }
    }

    public void printProfit(List<Sales> salesList , List<Expense> expenseList){
        double netProfit = 0;
        for(Sales sales : salesList){
            netProfit += sales.getAmount();
        }
        for(Expense expense : expenseList){
            netProfit += expense.getAmount();
        }
        System.out.println("Net Profit is " + netProfit);
    }
}
