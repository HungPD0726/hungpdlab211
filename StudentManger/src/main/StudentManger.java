package main;

import view.StudentView;

/**
 * Lớp khởi chạy ứng dụng Quản lý sinh viên (LAB211 - J1.L.P0021)
 * Chỉ làm nhiệm vụ gọi View khởi động chương trình.
 */
public class StudentManger {

    public static void main(String[] args) {
        StudentView view = new StudentView();
        view.run();
    }
}
