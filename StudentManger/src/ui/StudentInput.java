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
 * Lớp giao diện chịu trách nhiệm nhập và kiểm tra dữ liệu đầu vào của sinh viên từ người dùng.
 * Đảm bảo các thông tin như mã sinh viên (ID), tên sinh viên, học kỳ và môn học
 * được nhập đúng định dạng theo yêu cầu.
 * 
 * @author Administrator
 */
public class StudentInput {

    private final CourseInput courseInput = new CourseInput();

    /**
     * Khởi tạo đối tượng StudentInput để tiếp nhận dữ liệu sinh viên từ bàn phím.
     */
    public StudentInput() {
    }

    /**
     * Nhập thông tin sinh viên mới mà không truyền vào đối tượng StudentManager để tra cứu ID.
     * Phương thức này gọi gián tiếp tới {@link #inputStudent(StudentManager)} với tham số null.
     * 
     * @return Đối tượng {@link Student} mới được tạo từ dữ liệu nhập vào
     */
    public Student inputStudent() {
        return inputStudent(null);
    }

    /**
     * Nhập thông tin sinh viên mới từ bàn phím có kiểm tra và tái sử dụng tên sinh viên theo ID.
     * <p>
     * Quy tắc nghiệp vụ:
     * 1. Yêu cầu nhập mã sinh viên (ID) gồm chữ và số.
     * 2. Nếu mã sinh viên (ID) đã tồn tại trong hệ thống (tra cứu qua StudentManager),
     *    hệ thống tự động sử dụng lại tên sinh viên cũ nhằm đảm bảo 1 ID chỉ thuộc về 1 sinh viên duy nhất.
     * 3. Nếu mã sinh viên (ID) là mới hoàn toàn, yêu cầu người dùng nhập họ tên sinh viên (chỉ chữ và khoảng trắng).
     * 4. Nhập học kỳ theo định dạng hợp lệ (ví dụ: Fall 2023).
     * 5. Cho phép người dùng chọn khóa học từ menu danh sách môn học có sẵn.
     * 
     * @param studentManager Đối tượng quản lý dữ liệu sinh viên dùng để kiểm tra sự tồn tại của ID
     * @return Đối tượng {@link Student} mới chứa toàn bộ dữ liệu vừa nhập
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
