package entities;

import java.text.NumberFormat;
import java.util.Locale;

public class Sales {
    private double amount;
    private Order order;

    public Sales(double amount, Order order) {
        this.amount = amount;
        this.order = order;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public Order getOrder() {
        return order;
    }


    @Override
    public String toString(){
        Locale.setDefault(Locale.Category.valueOf("US"), Locale.US);
            return "Receipe = " + this.getOrder().getReceipe().getName() + ",Amount = " + NumberFormat.getCurrencyInstance().format(this.getAmount());
    }
}
