/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Course;
import entity.Student;
import java.util.List;
import java.util.Map;
import model.StudentManager;
import ui.StudentInput;
import utils.Validator;

/**
 * Controller trong mô hình MVC: Điều phối tương tác giữa giao diện và StudentManager.
 *
 * @author Administrator
 */
public class StudentController {

    private final StudentManager studentManager;
    private final StudentInput studentInput;

    /**
     * Khởi tạo StudentController.
     */
    public StudentController() {
        studentManager = new StudentManager();
        studentInput = new StudentInput();
    }

    /**
     * Tạo mới sinh viên. Khi tổng số sinh viên >= 10, hỏi người dùng có muốn tiếp tục hay không.
     */
    public void createStudent() {
        while (true) {
            Student newStudent = studentInput.inputStudent(studentManager);
            if (!studentManager.addStudent(newStudent)) {
                System.out.println("This student record (ID, semester, course) already exists!");
                continue;
            }
            System.out.println("Create student successfully! (Total students: " + studentManager.getTotalStudentCount() + ")");

            // Đề bài: "User has to create at least 10 students, if number of students greater than 10, the program shows message: Do you want to continue (Y/N)?"
            if (studentManager.getTotalStudentCount() >= 10) {
                String continueChoice = Validator.getString(
                        "Do you want to continue (Y/N)? ",
                        "Please choose Y or N.",
                        "[yYnN]");
                if (continueChoice.equalsIgnoreCase("N")) {
                    break;
                }
            } else {
                System.out.println("-> Notice: Minimum 10 students required (Current: " + studentManager.getTotalStudentCount() + ").");
            }
        }
    }

    /**
     * Tìm kiếm sinh viên theo tên và hiển thị danh sách đã sắp xếp.
     */
    public void findAndSortStudents() {
        String searchKeyword = Validator.getString(
                "Enter name to search: ",
                "Invalid name.",
                "[A-Za-z\\s]+");
        List<Student> matchingStudentList = studentManager.findStudentsByName(searchKeyword);
        if (matchingStudentList.isEmpty()) {
            System.out.println("No students found with name containing: " + searchKeyword);
            return;
        }
        studentManager.sortStudentsByName(matchingStudentList);
        System.out.println("Found Students:");
        System.out.println("| Student Name       | Semester     | Course Name   |");
        for (Student currentStudent : matchingStudentList) {
            currentStudent.displayStudentInformation();
        }
    }

    /**
     * Tìm sinh viên theo ID và cho phép chọn Update (U) hoặc Delete (D).
     */
    public void updateOrDeleteStudent() {
        String searchStudentId = Validator.getString(
                "Enter Student ID (e.g., he187004): ",
                "Invalid ID format (alphanumeric only).",
                "[a-zA-Z0-9]+");
        List<Student> matchingStudentList = studentManager.findStudentsById(searchStudentId);
        if (matchingStudentList.isEmpty()) {
            System.out.println("Student with ID " + searchStudentId + " not found.");
            return;
        }
        System.out.println("Found Students:");
        System.out.println("| No. | Student Name       | Semester     | Course Name   |");
        for (int index = 0; index < matchingStudentList.size(); index++) {
            matchingStudentList.get(index).displayStudentWithOrder(index + 1);
        }
        int selectedRecordIndex = Validator.getInt(
                "Select student record to update/delete (1-" + matchingStudentList.size() + "): ",
                "Please choose a number between 1 and " + matchingStudentList.size() + ".",
                "Invalid integer number.",
                1, matchingStudentList.size());
        Student selectedStudent = matchingStudentList.get(selectedRecordIndex - 1);

        String userActionChoice = Validator.getString(
                "Do you want to Update (U) or Delete (D) student? ",
                "Please enter U or D.",
                "[uUdD]");
        if (userActionChoice.equalsIgnoreCase("U")) {
            studentManager.updateStudentInformation(selectedStudent);
            System.out.println("Student updated successfully!");
        }
        if (userActionChoice.equalsIgnoreCase("D")) {
            studentManager.deleteStudent(selectedStudent);
            System.out.println("Student deleted successfully!");
        }
    }

    /**
     * Báo cáo thống kê số lượng khóa học theo từng sinh viên.
     */
    public void displayCourseReport() {
        Map<String, Map<Course, Integer>> courseReportMap = studentManager.generateCourseReportData();
        if (courseReportMap.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }
        System.out.println("Report:");
        System.out.println("| Student Name       | Course       | Total of Course |");
        for (Map.Entry<String, Map<Course, Integer>> studentReportEntry : courseReportMap.entrySet()) {
            String studentName = studentReportEntry.getKey();
            for (Map.Entry<Course, Integer> courseDetailEntry : studentReportEntry.getValue().entrySet()) {
                System.out.printf("| %-18s | %-12s | %-15d |%n",
                        studentName,
                        courseDetailEntry.getKey().getLanguage(),
                        courseDetailEntry.getValue());
            }
        }
    }
}