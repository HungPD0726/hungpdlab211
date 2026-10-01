/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Administrator
 */
public class OrderItem {

    private Fruit fruit;
    private int quantity;

    public OrderItem(Fruit fruit, int quantity) {
        this.fruit = fruit;
        this.quantity = quantity;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return quantity * fruit.getPrice();
    }
}
