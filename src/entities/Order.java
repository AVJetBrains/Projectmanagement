package entities;

import java.util.UUID;

public class Order {
    private Receipe receipe;
    private double amount;
    private String orderID;

    public Order(Receipe receipe, double amount) {
        this.receipe = receipe;
        this.amount = amount;
        this.orderID = String.valueOf(UUID.randomUUID());
    }

    public void setReceipe(Receipe receipe) {
        this.receipe = receipe;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Receipe getReceipe() {
        return receipe;
    }

    public double getAmount() {
        return amount;
    }

    public String getOrderID() {
        return orderID;
    }
}
