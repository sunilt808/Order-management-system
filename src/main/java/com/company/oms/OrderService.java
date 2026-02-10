package com.company.oms;

public class OrderService {

    public OrderResult processOrder(Order order) {

        // 1️⃣ Validate basic order rules
        validateOrder(order);

        // 2️⃣ Fraud detection: only trigger if BOTH amount and quantity exceed threshold
        if (isFraudulent(order)) {
            throw new OrderProcessingException(
                    "Fraud detected for order " + order.getOrderId()
            );
        }

        // 3️⃣ Calculate final price
        double finalPrice = calculatePrice(order);

        // 4️⃣ Return result
        return new OrderResult(
                order.getOrderId(),
                finalPrice,
                true
        );
    }

    // --------------------
    // Private helper methods
    // --------------------

    private void validateOrder(Order order) {
        if (order.getAmount() <= 0) {
            throw new OrderProcessingException("Order amount must be greater than zero");
        }
        if (order.getQuantity() <= 0) {
            throw new OrderProcessingException("Order quantity must be greater than zero");
        }
    }

    private boolean isFraudulent(Order order) {
        // ✅ Corrected: fraud only if BOTH are above threshold
        return order.getAmount() > 50000 && order.getQuantity() > 10;
    }

    private double calculatePrice(Order order) {
        // Discount: 10% if amount > 20k, multiplied by quantity
        double discount = order.getAmount() > 20000 ? 0.10 : 0.0;
        double pricePerUnit = order.getAmount() - (order.getAmount() * discount);
        return pricePerUnit * order.getQuantity();
    }
}
