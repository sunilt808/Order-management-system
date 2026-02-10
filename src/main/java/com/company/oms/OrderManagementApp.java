package com.company.oms;

import java.util.Scanner;

public class OrderManagementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService();

        System.out.println("=================================");
        System.out.println("   ORDER MANAGEMENT SYSTEM");
        System.out.println("=================================");

        // 1. Ask customer name
        System.out.print("Enter Customer Name: ");
        scanner.nextLine(); // consume leftover
        String customerName = scanner.nextLine();

        double totalAmount = 0;
        int totalQuantity = 0;
        String lastItemName = "";

        boolean addMore;

        do {
            System.out.println("\nChoose a category:");
            System.out.println("1. Clothes");
            System.out.println("2. Electronics");
            System.out.println("3. Groceries");
            System.out.println("4. Books");

            System.out.print("Enter category number: ");
            int categoryChoice = scanner.nextInt();

            Item selectedItem = null;

            switch (categoryChoice) {
                case 1:
                    selectedItem = showClothesMenu(scanner);
                    break;
                case 2:
                    selectedItem = showElectronicsMenu(scanner);
                    break;
                case 3:
                    selectedItem = showGroceriesMenu(scanner);
                    break;
                case 4:
                    selectedItem = showBooksMenu(scanner);
                    break;
                default:
                    System.out.println("Invalid category!");
                    return; // or System.exit(0);
            }


            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            totalAmount += selectedItem.getPrice() * quantity;
            totalQuantity += quantity;
            lastItemName = selectedItem.getName();

            System.out.print("Do you want to add another product? (Y/N): ");
            addMore = scanner.next().equalsIgnoreCase("Y");

        } while (addMore);

        // Payment confirmation
        System.out.println("\nTotal Amount to Pay: ₹" + totalAmount);
        System.out.print("Confirm payment? (Y/N): ");
        String paymentConfirm = scanner.next();

        if (!paymentConfirm.equalsIgnoreCase("Y")) {
            System.out.println("\nOrder Cancelled. Thank you, " + customerName + "!");
            scanner.close();
            return;
        }

        Order order = new Order(
                "ORD-" + System.currentTimeMillis(),
                totalAmount,
                customerName,
                totalQuantity
        );

        try {
            OrderResult result = orderService.processOrder(order);

            System.out.println("\n=================================");
            System.out.println("Order Confirmed Successfully!");
            System.out.println("Customer: " + customerName);
            System.out.println("Last Item Added: " + lastItemName);
            System.out.println("Total Quantity: " + totalQuantity);
            System.out.println("Final Price: ₹" + result.getFinalPrice());
            System.out.println("Delivery Status: Will be delivered soon 🚚");
            System.out.println("=================================");

        } catch (OrderProcessingException e) {
            System.out.println("Order Failed: " + e.getMessage());
        }

        scanner.close();
    }

    private static Item showClothesMenu(Scanner scanner) {
        System.out.println("\n--- Clothes ---");
        return selectItem(scanner, new Item[]{
                new Item("Shirt", 1200),
                new Item("Pant", 1800),
                new Item("Jacket", 3500),
                new Item("T-Shirt", 800),
                new Item("Jeans", 2200)
        });
    }

    private static Item showElectronicsMenu(Scanner scanner) {
        System.out.println("\n--- Electronics ---");
        return selectItem(scanner, new Item[]{
                new Item("Mobile", 15000),
                new Item("Laptop", 55000),
                new Item("Headphones", 3000),
                new Item("Keyboard", 2000),
                new Item("Mouse", 1200)
        });
    }

    private static Item showGroceriesMenu(Scanner scanner) {
        System.out.println("\n--- Groceries ---");
        return selectItem(scanner, new Item[]{
                new Item("Rice", 1200),
                new Item("Oil", 900),
                new Item("Sugar", 600),
                new Item("Wheat", 1000),
                new Item("Pulses", 1400)
        });
    }

    private static Item showBooksMenu(Scanner scanner) {
        System.out.println("\n--- Books ---");
        return selectItem(scanner, new Item[]{
                new Item("Java", 700),
                new Item("DSA", 900),
                new Item("DevOps", 1100),
                new Item("AWS", 1300),
                new Item("System Design", 1500)
        });
    }

    private static Item selectItem(Scanner scanner, Item[] items) {
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i].getName() + " - ₹" + items[i].getPrice());
        }
        System.out.print("Select item number: ");
        int choice = scanner.nextInt();
        return items[choice - 1];
    }
}
