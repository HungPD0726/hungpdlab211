/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import entity.Circle;
import entity.Retangle;
import entity.Shape;
import entity.Triangle;

/**
 *
 * @author HP
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("=====Calculator Shape Program=====");
        Shape shapes[] = {new Retangle(), new Circle(), new Triangle()};
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].input();
        }
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].getResult();
        }
    }

}
