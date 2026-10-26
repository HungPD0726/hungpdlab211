package main;

import controller.StudentController;
import utils.Validator;

/**
 *
 * @author HP
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentController studentControlller = new StudentController();
        while (true) {
            int choice = Validator.getInt("WELCOME TO STUDENT MANAGEMENT\n"
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
            switch (choice) {
                case 1:
                    studentControlller.createStudent();
                    break;
                case 2:
                    studentControlller.findAndSortStudents();
                    break;
                case 3:
                    studentControlller.updateOrDeleteStudent();
                    break;
                case 4:
                    studentControlller.displayCourseReport();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    throw new AssertionError("Invalid choice: " + choice);
            }
        }

    }

}
