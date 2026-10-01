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
            String productName = hasIndex ? (count + ". " + item.getFruit().getFruitName())
                                          : item.getFruit().getFruitName();
            String priceStr = (item.getFruit().getPrice() % 1 == 0
                    ? String.format("%.0f$", item.getFruit().getPrice())
                    : String.format("%.2f$", item.getFruit().getPrice()));
            String amountStr = (item.getAmount() % 1 == 0
                    ? String.format("%.0f$", item.getAmount())
                    : String.format("%.2f$", item.getAmount()));
            System.out.printf("%-15s | %-8d | %-8s | %-8s%n",
                    productName,
                    item.getQuantity(),
                    priceStr,
                    amountStr);
        }
        String totalStr = (getTotal() % 1 == 0
                ? String.format("%.0f$", getTotal())
                : String.format("%.2f$", getTotal()));
        System.out.println("Total: " + totalStr);
    }

}
