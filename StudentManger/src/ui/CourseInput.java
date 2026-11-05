/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Course;
import utils.Validator;

/**
 * Lớp giao diện tiếp nhận và xử lý việc lựa chọn môn học từ người dùng.
 * 
 * @author Administrator
 */
public class CourseInput {

    /**
     * Khởi tạo đối tượng CourseInput để hỗ trợ việc hiển thị và nhập lựa chọn môn học.
     */
    public CourseInput() {
    }

    /**
     * Hiển thị danh sách các môn học có sẵn và yêu cầu người dùng chọn qua menu (1: Java, 2: .Net, 3: C/C++).
     * Sử dụng phương thức {@link Validator#getInt} để đảm bảo người dùng chỉ nhập số nguyên từ 1 đến 3.
     * 
     * @return Đối tượng {@link Course} tương ứng với lựa chọn của người dùng
     */
    public Course inputCourse() {
        System.out.println("Available courses:");
        System.out.println("1. Java");
        System.out.println("2. .Net");
        System.out.println("3. C/C++");
        int selectedCourseNumber = Validator.getInt(
                "Enter course number (1-3): ",
                "Course number must be between 1 and 3.",
                "Please enter a valid integer number.",
                1, 3
        );
        return Course.getCourseByType(selectedCourseNumber);
    }
}
