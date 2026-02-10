package com.company.oms;

public class Order {

    private final String orderId;
    private final double amount;
    private final String customerId;
    private final int quantity;

    public Order(String orderId, double amount, String customerId, int quantity) {
        this.orderId = orderId;
        this.amount = amount;
        this.customerId = customerId;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getQuantity() {
        return quantity;
    }
}
