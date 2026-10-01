/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Fruit;
import entity.Order;
import entity.OrderItem;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Administrator
 */
public class FruitManager {

    private final ArrayList<Fruit> fruitList = new ArrayList<>();
    private final Hashtable<String, Order> orders = new Hashtable<>();
    private int orderCounter = 0;

    public FruitManager() {
        availableFruit();
    }

    /**
     * Initializes the fruit list with default fruit entries matching the assignment specs.
     */
    public void availableFruit() {
        fruitList.add(new Fruit("F01", "Coconut", 2.0, 35, "Vietnam"));
        fruitList.add(new Fruit("F02", "Orange", 3.0, 30, "US"));
        fruitList.add(new Fruit("F03", "Apple", 4.0, 50, "Thailand"));
        fruitList.add(new Fruit("F04", "Grape", 6.0, 20, "France"));
    }

    /**
     * Counts the number of available fruits with a quantity greater than zero.
     * @return The number of fruits available in the inventory.
     */
    public int getAvailableFruitCount() {
        int count = 0;
        for (Fruit fruit : fruitList) {
            if (fruit.getQuantity() > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Adds a new fruit to the fruit list.
     * @param fruit The Fruit object to be added to the list.
     */
    public void addFruit(Fruit fruit) {
        fruitList.add(fruit);
    }

    /**
     * Displays all fruits created in the shop (for shop owner review).
     */
    public void displayAllFruits() {
        if (fruitList.isEmpty()) {
            System.out.println("No fruits created yet!");
            return;
        }
        System.out.println("\nAll created fruits in shop:");
        System.out.printf("| %-8s | %-15s | %-12s | %-8s | %-8s |%n", "Fruit ID", "Fruit Name", "Origin", "Price", "Quantity");
        for (Fruit fruit : fruitList) {
            String priceStr = (fruit.getPrice() % 1 == 0
                    ? String.format("%.0f$", fruit.getPrice())
                    : String.format("%.2f$", fruit.getPrice()));
            System.out.printf("| %-8s | %-15s | %-12s | %-8s | %-8d |%n",
                    fruit.getFruitID(),
                    fruit.getFruitName(),
                    fruit.getOrigin(),
                    priceStr,
                    fruit.getQuantity());
        }
    }

    /**
     * Displays the list of available fruits for shopping.
     */
    public void listFruits() {
        if (fruitList.isEmpty()) {
            System.out.println("No fruits available!");
            return;
        }
        System.out.println("\nList of Fruit:");
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
        int itemCount = 0;
        for (Fruit fruit : fruitList) {
            if (fruit.getQuantity() > 0) {
                itemCount++;
                String priceStr = (fruit.getPrice() % 1 == 0
                        ? String.format("%.0f$", fruit.getPrice())
                        : String.format("%.2f$", fruit.getPrice()));
                System.out.printf("      %-12d %-18s %-16s %s%n",
                        itemCount,
                        fruit.getFruitName(),
                        fruit.getOrigin(),
                        priceStr);
            }
        }
        if (itemCount == 0) {
            System.out.println("No fruits available in stock!");
        }
    }

    /**
     * Finds a fruit by its item number in the list of available fruits.
     * @param item The item number of the fruit in the list.
     * @return The Fruit object if found; otherwise, null.
     */
    public Fruit findFruitById(int item) {
        int count = 0;
        for (Fruit fruit : fruitList) {
            if (fruit.getQuantity() > 0) {
                count++;
                if (count == item) {
                    return fruit;
                }
            }
        }
        return null;
    }

    /**
     * Finds a fruit by its string ID (case-insensitive).
     * @param id The string ID of the fruit.
     * @return The Fruit object if found; otherwise, null.
     */
    public Fruit findFruitByIdString(String id) {
        for (Fruit fruit : fruitList) {
            if (fruit.getFruitID().equalsIgnoreCase(id.trim())) {
                return fruit;
            }
        }
        return null;
    }

    /**
     * Updates the quantity of a fruit in the inventory.
     * @param fruit The Fruit object to update.
     * @param quantity The quantity to deduct.
     * @return true if successful; false if insufficient quantity.
     */
    public boolean updateFruitQuantity(Fruit fruit, int quantity) {
        if (fruit.getQuantity() >= quantity) {
            fruit.setQuantity(fruit.getQuantity() - quantity);
            return true;
        }
        return false;
    }

    /**
     * Places an order for a customer. Stores orders in Hashtable cleanly.
     * @param customer The name of the customer placing the order.
     * @param cart The list of OrderItem objects in the cart.
     */
    public void placeOrder(String customer, ArrayList<OrderItem> cart) {
        if (!cart.isEmpty()) {
            orderCounter++;
            String orderKey = customer + "#" + orderCounter;
            Order order = new Order(customer);
            for (OrderItem newItem : cart) {
                order.addItem(new OrderItem(newItem.getFruit(), newItem.getQuantity()));
            }
            orders.put(orderKey, order);
            cart.clear();
        }
    }

    /**
     * Adds a fruit to the shopping cart and merges quantity if the fruit already exists in cart.
     * @param cart The shopping cart.
     * @param fruit The Fruit object to add.
     * @param quantity The quantity to add.
     * @return true if successful; false if insufficient stock.
     */
    public boolean addToCart(ArrayList<OrderItem> cart, Fruit fruit, int quantity) {
        if (fruit == null || !updateFruitQuantity(fruit, quantity)) {
            return false;
        }
        for (OrderItem item : cart) {
            if (item.getFruit().getFruitID().equalsIgnoreCase(fruit.getFruitID())) {
                item.setQuantity(item.getQuantity() + quantity);
                return true;
            }
        }
        cart.add(new OrderItem(fruit, quantity));
        return true;
    }

    /**
     * Displays all orders in the system matching assignment format.
     */
    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available!");
            return;
        }
        for (String key : orders.keySet()) {
            Order order = orders.get(key);
            System.out.println("\nCustomer: " + order.getCustomerName());
            order.printOrder(true);
        }
    }

    /**
     * Prints an invoice preview for the current shopping cart.
     * @param cart The shopping cart to display.
     */
    public void printInvoice(ArrayList<OrderItem> cart) {
        Order tempOrder = new Order("", cart);
        tempOrder.printOrder(false);
    }

}