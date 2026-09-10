/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Course;
import entity.Student;
import model.StudentManager;
import utils.Validator;

/**
 * Lớp chịu trách nhiệm nhập dữ liệu sinh viên từ người dùng.
 * 
 * @author Administrator
 */
public class StudentInput {

    private final CourseInput courseInput = new CourseInput();

    public Student inputStudent() {
        return inputStudent(null);
    }

    /**
     * Nhập thông tin sinh viên mới. Nếu ID đã tồn tại trong StudentManager,
     * tự động nhận diện tên cũ để đảm bảo tính nhất quán (1 ID chỉ có 1 tên).
     * 
     * @param studentManager Đối tượng quản lý sinh viên để tra cứu ID
     * @return Đối tượng Student mới
     */
    public Student inputStudent(StudentManager studentManager) {
        String studentId = Validator.getString(
                "Enter student ID (e.g., he187004): ",
                "Invalid student ID format (alphanumeric only).",
                "[a-zA-Z0-9]+");

        String studentName = null;
        if (studentManager != null) {
            studentName = studentManager.getStudentNameById(studentId);
        }

        if (studentName != null) {
            System.out.println("-> ID already exists in the system. Student Name: " + studentName);
        } else {
            studentName = Validator.getString(
                    "Enter student name: ",
                    "Invalid student name (letters and spaces only).",
                    "[A-Za-z\\s]+");
        }

        String semester = Validator.getString(
                "Enter semester (e.g., Fall 2023): ",
                "Invalid semester.",
                "[a-zA-Z0-9\\s]+");

        Course selectedCourse = courseInput.inputCourse();

        return new Student(studentId, studentName, semester, selectedCourse);
    }
}