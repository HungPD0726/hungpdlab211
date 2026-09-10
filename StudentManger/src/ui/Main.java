/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.StudentController;
import utils.Validator;

/**
 * Lớp khởi chạy ứng dụng Quản lý sinh viên.
 * 
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentController studentController = new StudentController();
        while (true) {
            int menuChoice = Validator.getInt("WELCOME TO STUDENT MANAGEMENT\n"
                    + "1. Create\n"
                    + "2. Find and Sort\n"
                    + "3. Update/Delete\n"
                    + "4. Report\n"
                    + "5. Exit\n"
                    + "(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program).\n"
                    + "Please choose (1 - 5): ",
                    "Please choose a number between 1 and 5.",
                    "Invalid integer input.",
                    1, 5);
            switch (menuChoice) {
                case 1:
                    studentController.createStudent();
                    break;
                case 2:
                    studentController.findAndSortStudents();
                    break;
                case 3:
                    studentController.updateOrDeleteStudent();
                    break;
                case 4:
                    studentController.displayCourseReport();
                    break;
                case 5:
                    System.out.println("Exit program successfully. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError("Invalid menu choice: " + menuChoice);
            }
        }
    }
}