/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Fruit;
import entity.OrderItem;
import java.util.ArrayList;
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
     * Creates a new fruit and adds it to the fruit list. Prompts the user
     * to input fruit details and checks if the ID already exists.
     * When user enters 'N', displays all created fruits and returns to main screen.
     */
    public void createFruit() {
        while (true) {
            String id = Validator.getString("Enter Fruit ID: ",
                    "ID cannot be empty and must contain letters or digits!",
                    "^[a-zA-Z0-9]+$");
            if (fruitManager.findFruitByIdString(id) != null) {
                System.out.println("Fruit ID already exists!");
                continue;
            }
            Fruit fruit = fruitInputter.inputFruit(id);
            fruitManager.addFruit(fruit);
            System.out.println("Fruit added successfully!");
            String choice = Validator.getString("Do you want to continue (Y/N)? ",
                    "Please enter Y or N!",
                    "^[yYnN]$");
            if (choice.equalsIgnoreCase("N")) {
                fruitManager.displayAllFruits();
                break;
            }
        }
    }

    /**
     * Displays the list of placed orders matching the assignment format.
     */
    public void viewOrders() {
        fruitManager.displayAllOrders();
    }

    /**
     * Handles the shopping process for buyers matching the assignment format.
     */
    public void shopping() {
        ArrayList<OrderItem> cart = new ArrayList<>();
        while (true) {
            fruitManager.listFruits();
            int maxItem = fruitManager.getAvailableFruitCount();
            if (maxItem == 0) {
                System.out.println("No fruit available in stock!");
                break;
            }
            int selectItem = Validator.getInt("To order, customer selects Item (1-" + maxItem + "): ",
                    "Please choose an item in range (1-" + maxItem + "): ",
                    "Invalid input! Please enter a number.", 1, maxItem);

            Fruit selectFruit = fruitManager.findFruitById(selectItem);
            if (selectFruit == null) {
                System.out.println("Fruit not found!");
                continue;
            }

            System.out.println("You selected: " + selectFruit.getFruitName());

            int quantity = Validator.getInt("Please input quantity: ",
                    "Quantity must be between 1 and " + selectFruit.getQuantity() + ": ",
                    "Invalid quantity! Please enter an integer.", 1, selectFruit.getQuantity());

            if (!fruitManager.addToCart(cart, selectFruit, quantity)) {
                System.out.println("Not enough fruit to sell!");
                continue;
            }

            String choice = Validator.getString("Do you want to order now (Y/N)? ",
                    "Please choose Y or N!",
                    "^[yYnN]$");

            if (choice.equalsIgnoreCase("Y")) {
                if (cart.isEmpty()) {
                    System.out.println("Cart is empty!");
                    continue;
                }
                fruitManager.printInvoice(cart);

                String customer = Validator.getName("Input your name: ", "Invalid name! Characters and spaces only.", "^[a-zA-Z ]+$");
                fruitManager.placeOrder(customer, cart);
                System.out.println("Order placed successfully!");
                break;
            }
        }
    }
}
