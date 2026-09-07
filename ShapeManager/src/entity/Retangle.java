/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import validator.Validator;

/**
 *
 * @author HP
 */
public class Retangle extends Shape {

    private double width;
    private double length;

    public Retangle() {
    }

    public Retangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getPerimater() {
        return 2 * (width + length);
    }

    @Override
    public double getArea() {
        return width * length;
    }

    @Override
    public void getResult() {
        System.out.println("-----Rectangle-----");
        System.out.println("Width: " + width);
        System.out.println("Length: " + length);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimater());
    }

    @Override
    public void input() {
        width = Validator.getDouble("Please input side width of Rectangle: ",
                "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
        length = Validator.getDouble("Please input side length of Rectangle: ",
                "Please enter number >= " + width, "Invalid!", width, Double.MAX_VALUE);
    }

}
