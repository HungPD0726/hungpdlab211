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
public class Triangle extends Shape {

    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle() {
    }

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }

    @Override
    public double getPerimater() {
        return (sideA + sideB + sideC);
    }

    @Override
    public double getArea() {
        double p = getPerimater() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public void getResult() {
        System.out.println("-----Triangle-----");
        System.out.println("Side A: " + sideA);
        System.out.println("Side B: " + sideB);
        System.out.println("Side C: " + sideC);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimater());
    }

    @Override
    public void input() {
        while (true) {
            sideA = Validator.getDouble("Please input side A of Triangle: ",
                    "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
            sideB = Validator.getDouble("Please input side B of Triangle: ",
                    "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
            sideC = Validator.getDouble("Please input side C of Triangle: ",
                    "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
            if (sideA + sideB > sideC && sideA + sideC > sideB && sideB + sideC > sideA) {
                break;
            } else {
                System.out.println("Sum of two side must be more than side remaining! Try again");
            }
        }
    }
}
