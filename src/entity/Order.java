/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Order {

    private String customerName;
    private ArrayList<OrderItem> items;

    public Order(String customerName) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
    }

    public Order(String customerName, ArrayList<OrderItem> items) {
        this.customerName = customerName;
        this.items = items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getAmount();
        }
        return total;
    }

    public void printOrder(boolean hasIndex) {
        System.out.printf("%-15s | %-8s | %-8s | %-8s%n", "Product", "Quantity", "Price", "Amount");
        int count = 0;
        for (OrderItem item : items) {
            count++;
            String name = hasIndex ? (count + ". " + item.getFruit().getFruitName())
                                   : item.getFruit().getFruitName();
            System.out.printf("%-15s | %-8d | %-8s | %-8s%n",
                    name, item.getQuantity(),
                    formatMoney(item.getFruit().getPrice()),
                    formatMoney(item.getAmount()));
        }
        System.out.println("Total: " + formatMoney(getTotal()));
    }

    private String formatMoney(double value) {
        return value % 1 == 0 ? String.format("%.0f$", value)
                              : String.format("%.2f$", value);
    }

}
