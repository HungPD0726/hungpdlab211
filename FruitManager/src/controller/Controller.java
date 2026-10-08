/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Fruit;
import entity.Order;
import entity.OrderItem;
import java.util.ArrayList;
import java.util.Hashtable;

import model.FruitManager;
import ui.FruitInputter;
import utils.Validator;

/**
 *
 * @author Administrator
 */
public class Controller {

    private final FruitManager fruitManager = new FruitManager();
    private final FruitInputter fruitInputter = new FruitInputter();

    public Controller() {
    }

    /**
     * Creates a new fruit and adds it to the fruit list. This method prompts
     * the user to input fruit details (ID, name, price, quantity, and origin)
     * and checks if the ID already exists. If the ID is valid, the fruit is
     * added to the inventory, and the user can choose to continue adding more
     * fruits or display the fruit list.
     */
    public void createFruit() {
        while (true) {
            String id = Validator.getString("Enter Fruit ID (format Fxx): ",
                    "Please enter ID with format Fxx (x is digit)",
                    "F\\d{2}");
            if (fruitManager.findFruitByIdString(id) != null) {
                System.out.println("Fruit ID already exists!");
                continue;
            }
            Fruit fruit = fruitInputter.inputFruit(id);
            fruitManager.addFruit(fruit);
            System.out.println("Fruit added successfully!");
            String choice = Validator.getString("Do you want to continue (Y/N)? ",
                    "Please enter Y or N!",
                    "[yYnN]");
            if (choice.equalsIgnoreCase("N")) {
                fruitManager.listFruits();
                break;
            }
        }
    }

    /**
     * Displays the list of placed orders. This method retrieves the list of
     * orders from the FruitManager and prints detailed information for each
     * order, including customer name, product list, quantities, prices, and
     * total amount. If no orders exist, an appropriate message is shown.
     */
    public void viewOrders() {
        Hashtable<String, Order> orders = fruitManager.getOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders available!");
            return;
        }
        for (String key : orders.keySet()) {
            Order order = orders.get(key);
            System.out.println("\nCustomer: " + order.getCustomerName());
            System.out.println("Product | Quantity | Price | Amount");
            int itemNum = 0;
            int total = 0;
            for (OrderItem item : order.getItems()) {
                itemNum++;
                Fruit fruit = item.getFruit();
                int amount = item.getAmount();
                System.out.printf("%d. %-12s %-7d %-6s %d$%n",
                        itemNum,
                        fruit.getFruitName(),
                        item.getQuantity(),
                        fruit.getPrice() + "$",
                        amount);
                total += amount;
            }
            System.out.println("Total: " + total + "$");
        }
    }

    /**
     * Handles the shopping process for buyers. This method allows users to
     * select fruits from the available list, add them to a cart with a
     * specified quantity, and confirm the order. Upon order confirmation, it
     * displays the order details, including product names, quantities, prices,
     * and total amount. The cart is cleared after a successful order placement.
     */
    public void shopping() {
        ArrayList<OrderItem> cart = new ArrayList<>();
        while (true) {
            fruitManager.listFruits();
            if (fruitManager.getFruitList() == 0) {
                System.out.println("No fruit available");
                break;
            }
            int selectItem = Validator.getInt("Choose fruit (1-" + fruitManager.getFruitList() + "): ",
                    "You must input the fruit in range (1-" + fruitManager.getFruitList() + "): ",
                    "Invalid", 1, fruitManager.getFruitList());

            Fruit selectFruit = fruitManager.findFruitById(selectItem);

            if (selectFruit == null) {
                System.out.println("Invalid input");
                continue;
            }

            System.out.println("You selected fruit: " + selectFruit.getFruitName());

            int quantity = Validator.getInt("Please choose the quantity (1-" + selectFruit.getQuantity() + "): ",
                    "Please choose in range (1-" + selectFruit.getQuantity() + "): ",
                    "Invalid", 1, selectFruit.getQuantity());
            if (!fruitManager.addToCart(cart, selectFruit, quantity)) {
                System.out.println("Not enough fruit to sell");
                continue;
            }
            String choice = Validator.getString("Do you want to order now (Y/N)? ",
                    "Please choose Y or N",
                    "[ynYN]");
            if (choice.equalsIgnoreCase("Y")) {
                if (cart.isEmpty()) {
                    System.out.println("Cart is empty, please add fruit to the cart");
                    continue;
                }
                System.out.println("Product | Quantity | Price | Amount");
                int total = 0;
                for (OrderItem item : cart) {
                    int amount = item.getAmount();
                    System.out.printf("%-12s %-7d %-6s %d$%n",
                            item.getFruit().getFruitName(),
                            item.getQuantity(),
                            item.getFruit().getPrice() + "$",
                            amount);
                    total += amount;
                }
                System.out.println("Total: " + total + "$");
                String customer = Validator.getName("Customer: ", "Invalid name", "[a-zA-Z ]+");
                fruitManager.placeOrder(customer, cart);
                System.out.println("Order placed successfully!");
                cart.clear();
                break;
            }
        }
    }
}
