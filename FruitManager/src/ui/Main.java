/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ui;

import controller.Controller;
import utils.Validator;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller c = new Controller();

        while (true) {
            int choice = Validator.getInt("FRUIT SHOP SYSTEM\n"
                    + "1. Create Fruit\n"
                    + "2. View orders\n"
                    + "3. Shopping (for buyer)\n"
                    + "4. Exit\n"
                    + "(Please choose 1 to create product, 2 to view order, 3 for shopping, 4 to Exit program): ",
                    "Please choose 1-4", "Invalid input! Please enter a number.", 1, 4);
            switch (choice) {
                case 1:
                    c.createFruit();
                    break;
                case 2:
                    c.viewOrders();
                    break;
                case 3:
                    c.shopping();
                    break;
                case 4:
                    System.out.println("Exited successfully.");
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

}
