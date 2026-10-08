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
     * Initializes the fruit list with default fruit entries.
     * This method populates the fruitList with a predefined set of fruits,
     * including their ID, name, price, quantity, and origin.
     * It is called during the instantiation of the FruitManager to provide initial
     * data for the system.
     */
    public void availableFruit() {
        fruitList.add(new Fruit("F01", "Apple", 5, 50, "USA"));
        fruitList.add(new Fruit("F02", "Banana", 3, 40, "Vietnam"));
        fruitList.add(new Fruit("F03", "Orange", 6, 30, "Spain"));
        fruitList.add(new Fruit("F04", "Mango", 4, 25, "Thailand"));
        fruitList.add(new Fruit("F05", "Grape", 8, 20, "France"));
        fruitList.add(new Fruit("F06", "Pineapple", 7, 18, "Philippines"));
        fruitList.add(new Fruit("F07", "Coconut", 4, 35, "Vietnam"));
        fruitList.add(new Fruit("F08", "Watermelon", 10, 15, "China"));
        fruitList.add(new Fruit("F09", "Papaya", 5, 22, "India"));
        fruitList.add(new Fruit("F10", "Jackfruit", 6, 12, "Malaysia"));
    }

    /**
     * Counts the number of available fruits with a quantity greater than zero.
     * This method iterates through the fruit list and returns the count of fruits
     * that have a positive quantity in stock.
     * 
     * @return The number of fruits available in the inventory.
     */
    public int getFruitList() {
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
     * This method takes a Fruit object and appends it to the fruitList for
     * inventory management.
     * 
     * @param fruit The Fruit object to be added to the list.
     */
    public void addFruit(Fruit fruit) {
        fruitList.add(fruit);
    }

    /**
     * Displays the list of available fruits.
     * This method prints details of fruits with a quantity greater than zero,
     * including item number, name, origin, and price.
     * If no fruits are available, an appropriate message is displayed.
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
                System.out.printf("| %-10d | %-15s | %-11s | %-10s |%n",
                        itemCount,
                        fruit.getFruitName(),
                        fruit.getOrigin(),
                        fruit.getPrice() + "$");
            }
        }
        if (itemCount == 0) {
            System.out.println("No fruits available in stock!");
        }
    }

    /**
     * Finds a fruit by its item number in the list of available fruits.
     * This method returns the Fruit object corresponding to the specified item
     * number, considering only fruits with a quantity greater than zero.
     * 
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
     * Finds a fruit by its string ID.
     * This method searches the fruit list for a Fruit object with an ID matching
     * the provided string.
     * 
     * @param id The string ID of the fruit (e.g., "F01").
     * @return The Fruit object if found; otherwise, null.
     */
    public Fruit findFruitByIdString(String id) {
        for (Fruit fruit : fruitList) {
            if (fruit.getFruitID().equals(id)) {
                return fruit;
            }
        }
        return null;
    }

    /**
     * Updates the quantity of a fruit in the inventory.
     * This method checks if the requested quantity can be deducted from the fruit's
     * current quantity.
     * If sufficient, the quantity is updated, and the method returns true.
     * 
     * @param fruit    The Fruit object to update.
     * @param quantity The quantity to deduct.
     * @return true if the update is successful; false if there is insufficient
     *         quantity.
     */
    public boolean updateFruitQuantity(Fruit fruit, int quantity) {
        if (fruit.getQuantity() >= quantity) {
            fruit.setQuantity(fruit.getQuantity() - quantity);
            return true;
        }
        return false;
    }

    /**
     * Places an order for a customer.
     * Each call to this method creates a NEW order for the customer.
     * Items from the cart are copied to the new order.
     * The cart is cleared after placing the order.
     * 
     * @param customer The name of the customer placing the order.
     * @param cart     The list of OrderItem objects in the cart.
     */
    public void placeOrder(String customer, ArrayList<OrderItem> cart) {
        if (!cart.isEmpty()) {
            orderCounter++;
            String orderKey = customer + "#" + orderCounter;
            Order order = new Order(customer);
            for (OrderItem cartItem : cart) {
                order.addItem(new OrderItem(cartItem.getFruit(), cartItem.getQuantity()));
            }
            orders.put(orderKey, order);
            cart.clear();
        }
    }

    /**
     * Adds a fruit to the shopping cart.
     * This method checks if the fruit is valid and if there is enough quantity in
     * stock. If so, it updates the fruit's quantity
     * and adds a new OrderItem to the cart (merging quantity if fruit already exists in cart).
     * 
     * @param cart     The shopping cart (ArrayList of OrderItem objects).
     * @param fruit    The Fruit object to add to the cart.
     * @param quantity The quantity of the fruit to add.
     * @return true if the addition is successful; false if the fruit is invalid or
     *         there is insufficient quantity.
     */
    public boolean addToCart(ArrayList<OrderItem> cart, Fruit fruit, int quantity) {
        if (fruit == null || !updateFruitQuantity(fruit, quantity)) {
            return false;
        }
        for (OrderItem item : cart) {
            if (item.getFruit().getFruitID().equals(fruit.getFruitID())) {
                item.setQuantity(item.getQuantity() + quantity);
                return true;
            }
        }
        cart.add(new OrderItem(fruit, quantity));
        return true;
    }

    /**
     * Retrieves the list of all orders.
     * This method returns the Hash table containing all orders, with customer names
     * as keys and Order objects as values.
     * 
     * @return The Hash table of orders.
     */
    public Hashtable<String, Order> getOrders() {
        return orders;
    }
}
