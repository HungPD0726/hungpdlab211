/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Course;
import utils.Validator;

/**
 * Lớp giao diện tiếp nhận lựa chọn môn học từ người dùng.
 * 
 * @author Administrator
 */
public class CourseInput {

    /**
     * Cho phép người dùng chọn môn học qua menu 1, 2, 3 (Java, .Net, C/C++).
     * 
     * @return Đối tượng Course tương ứng
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