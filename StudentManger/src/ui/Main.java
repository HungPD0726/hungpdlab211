/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.StudentController;
import utils.Validator;

/**
 * Lớp khởi chạy ứng dụng Quản lý sinh viên (Student Management System).
 * Chứa hàm main đóng vai trò là điểm vào (entry point) của toàn bộ chương trình.
 * 
 * @author Administrator
 */
public class Main {

    /**
     * Khởi tạo đối tượng Main phục vụ chạy chương trình.
     */
    public Main() {
    }

    /**
     * Điểm bắt đầu thực thi chương trình (Entry Point).
     * <p>
     * Phương thức thực hiện các nhiệm vụ:
     * 1. Khởi tạo đối tượng {@link StudentController} để điều phối các chức năng.
     * 2. Vận hành vòng lặp menu chính để người dùng chọn các chức năng từ 1 đến 5:
     *    - 1: Create (Tạo mới sinh viên, tối thiểu 10 bản ghi)
     *    - 2: Find and Sort (Tìm kiếm theo tên và sắp xếp tăng dần theo bảng chữ cái A-Z)
     *    - 3: Update/Delete (Tìm theo ID để cập nhật thông tin hoặc xóa bản ghi)
     *    - 4: Report (Thống kê tổng số khóa học mà từng sinh viên đã học)
     *    - 5: Exit (Thoát khỏi chương trình)
     * 
     * @param args Mảng các tham số dòng lệnh truyền vào chương trình (command line arguments)
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
