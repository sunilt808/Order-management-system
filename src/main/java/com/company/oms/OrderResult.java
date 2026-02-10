package com.company.oms;

public class OrderResult {

    private final String orderId;
    private final double finalPrice;
    private final boolean fraudChecked;

    public OrderResult(String orderId, double finalPrice, boolean fraudChecked) {
        this.orderId = orderId;
        this.finalPrice = finalPrice;
        this.fraudChecked = fraudChecked;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public boolean isFraudChecked() {
        return fraudChecked;
    }
}
