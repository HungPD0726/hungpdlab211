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
public class Circle extends Shape{
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getPerimater() {
        return 2*Math.PI*radius;
    }

    @Override
    public double getArea() {
        return Math.PI*radius*radius;
    }

    @Override
    public void getResult() {
        System.out.println("-----Circle-----");
        System.out.println("Radius: " + radius);
        System.out.println("Perimeter: " + getPerimater());
        System.out.println("Area: " + getArea());
    }

    @Override
    public void input() {
        radius = Validator.getDouble("Please input radius: ", "Number out of range", "Error number", Double.MIN_VALUE, Double.MAX_VALUE);
    }
    
    
}
