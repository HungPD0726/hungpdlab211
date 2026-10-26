package controller;

import entity.Course;
import entity.Student;
import java.util.List;
import java.util.Map;
import model.StudentManager;
import ui.StudentInput;
import utils.Validator;

/**
 *
 * @author HP
 */
public class StudentController {

    private final StudentManager studentManager = new StudentManager();
    private final StudentInput studentInput = new StudentInput();

    public void createStudent() {
        while (true) {
            Student newStudent = studentInput.inputStudent(studentManager);
            if (!studentManager.addStudent(newStudent)) {
                System.out.println("Student already exists!");
                continue;
            }
            System.out.println("Create successfully.");

            if (studentManager.getTotalStudent() >= 10) {
                String choice = Validator.getString(
                        "Do you want to continue (Y/N)? ",
                        "Please choose Y or N.",
                        "[yYnN]");
                if (choice.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("Current total students: " + studentManager.getTotalStudent());
                }
            }
        }
    }

    public void findAndSortStudents() {
        String keyWord = Validator.getString("Enter name: ", "Invalid name format.", "[A-Za-z\\s]+");
        List<Student> findList = studentManager.findStudentByName(keyWord);
        if (findList.isEmpty()) {
            System.out.println("Cannot find student with name: " + keyWord);
            return;
        }
        studentManager.sortStudentsByName(findList);
        System.out.println("Found students:");
        System.out.printf("| %-18s | %-12s | %-13s|%n", "Student Name", "Semester", "Course Name");
        for (Student currentStudent : findList) {
            currentStudent.displayStudentInfor();
        }
    }

    public void updateOrDeleteStudent() {
        String searchStudentId = Validator.getString(
                "Enter Student ID (e.g., he187004): ",
                "Invalid ID format (alphanumeric only).",
                "[a-zA-Z0-9]+");
        List<Student> findList = studentManager.findStudentById(searchStudentId);
        if (findList.isEmpty()) {
            System.out.println("Cannot find student with ID: " + searchStudentId);
            return;
        }
        System.out.println("Found students:");
        System.out.printf("| %-3s |%-18s | %-12s | %-13s|%n", "No.", "Student Name", "Semester", "Course Name");
        for (int i = 0; i < findList.size(); i++) {
            findList.get(i).displayStudentWithOrder(i + 1);
        }
        int selectRecordIndex = Validator.getInt("Select record (1 - " + findList.size() + "): ",
                "Please choose from 1 to " + findList.size(), "Invalid integer input.", 1, findList.size());
        Student selectStudent = findList.get(selectRecordIndex - 1);
        String choice = Validator.getString("Do you want to Update (U) or Delete (D) student? ",
                "Please enter U or D.",
                "[uUdD]");
        if (choice.equalsIgnoreCase("U")) {
            studentManager.updateStudentInformation(selectStudent);
            System.out.println("Update successfully.");
        } else if (choice.equalsIgnoreCase("D")) {
            studentManager.deleteStudent(selectStudent);
            System.out.println("Delete successfully.");
        }
    }

    /**
     * Displays a statistical report of course enrollments per student.
     */
    public void displayCourseReport() {
        Map<String, Map<Course, Integer>> courseReportMap = studentManager.generrateCourseData();
        if (courseReportMap.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }

        System.out.println("Report:");
        System.out.println("| Student Name       | Course       | Total of Course |");
        courseReportMap.forEach((studentName, courses)
                -> courses.forEach((course, total)
                        -> System.out.printf("| %-18s | %-12s | %-15d |%n",
                                studentName,
                                course.getLanguage(),
                                total)
                )
        );
    }
}
