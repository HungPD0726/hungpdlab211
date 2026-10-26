package ui;

import entity.Course;
import entity.Student;
import model.StudentManager;
import utils.Validator;

/**
 *
 * @author HP
 */
public class StudentInput {

    private final CourseInput courseInput = new CourseInput();

    public Student inputStudent() {
        return inputStudent(null);
    }

    public Student inputStudent(StudentManager studentManager) {
        String id = Validator.getString("Enter student ID (e.g., he187004): ",
                "Invalid student ID format (alphanumeric only).",
                "[a-zA-Z0-9]+");
        String studentName = null;
        if (studentManager != null) {
            studentName = studentManager.getStudentById(id);
        }

        if (studentName != null) {
            System.out.println("Da ton tai" + studentName);
        } else {
            studentName = Validator.getString("Enter student name: ",
                    "Invalid student name format (alphanumeric only).",
                    "[a-zA-Z\\s]+");
        }
        String semester = Validator.getString(
                "Enter semester (e.g., Fall 2023): ",
                "Invalid semester.",
                "[a-zA-Z0-9\\s]+");

        Course selectedCourse = courseInput.courseInput();

        return new Student(id, studentName, semester, selectedCourse);
    }
}
