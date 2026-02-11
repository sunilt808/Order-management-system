package com.company.oms;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class OrderServiceTest {

    private final OrderService service = new OrderService();

    // ✅ PASS CASE
    @Test
    public void shouldProcessValidOrderSuccessfully() {
        Order order = new Order(
                "ORD-TEST-1",
                20000,
                "CUST-01",
                2
        );

        OrderResult result = service.processOrder(order);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.getFinalPrice() > 0);
    }

    // ❌ FAIL CASE: Invalid amount
    @Test(expectedExceptions = OrderProcessingException.class)
    public void shouldFailWhenAmountIsZero() {
        Order order = new Order(
                "ORD-TEST-2",
                0,
                "CUST-02",
                1
        );

        service.processOrder(order);
    }

    // ❌ FAIL CASE: Fraud detected
    @Test(expectedExceptions = OrderProcessingException.class)
    public void shouldFailWhenFraudRulesTriggered() {
        Order order = new Order(
                "ORD-TEST-3",
                60000,
                "CUST-03",
                15
        );

        service.processOrder(order);
    }

    // ✅ PASS CASE
    @Test
    public void shouldAllowHighAmountWithLowQuantity() {
        Order order = new Order(
                "ORD-TEST-4",
                60000,
                "CUST-04",
                1
        );

        OrderResult result = service.processOrder(order);

        Assert.assertTrue(result.getFinalPrice() > 0);
    }

    // ⚠️ UNSTABLE / SKIPPED CASE
    @Test
    public void unstableTestBasedOnEnvironmentVariable() {

        if (System.getenv("RUN_FRAUD_TESTS") == null) {
            throw new SkipException("Skipping because RUN_FRAUD_TESTS not set");
        }

        Order order = new Order(
                "ORD-TEST-5",
                30000,
                "CUST-05",
                3
        );

        OrderResult result = service.processOrder(order);
        Assert.assertNotNull(result);
    }
}
