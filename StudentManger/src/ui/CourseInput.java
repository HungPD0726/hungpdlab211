package ui;

import entity.Course;
import utils.Validator;

/**
 *
 * @author HP
 */
public class CourseInput {

    public Course courseInput() {
        System.out.println("Avaiable Course: ");
        System.out.println("1. Java");
        System.out.println("2. DOT_NET");
        System.out.println("3. CPP");
        int choice = Validator.getInt("Please choose: ", "Choose again", "Choose again", 1, 3);
        return Course.getCourseByType(choice);
    }
}
