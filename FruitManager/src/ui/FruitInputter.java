/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Fruit;
import utils.Validator;

/**
 *
 * @author Administrator
 */
public class FruitInputter {

    public Fruit inputFruit(String id) {
        System.out.println("Enter fruit detail: ");
        String name = Validator.getString("Fruit name: ", "Invalid fruit name! Letters and spaces only.", "^[a-zA-Z ]+$");
        double price = Validator.getDouble("Price: ", "Price must be greater than 0 and up to 10000", "Invalid price! Must be a number.", 0.01, 10000);
        int quantity = Validator.getInt("Quantity: ", "Quantity must be between 1 - 10000", "Invalid quantity! Must be an integer.", 1, 10000);
        String origin = Validator.getString("Origin: ", "Invalid origin! Letters and spaces only.", "^[a-zA-Z ]+$");
        return new Fruit(id, name, price, quantity, origin);
    }

}
