/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 * Entity class đại diện cho đối tượng Sinh viên trong hệ thống.
 * Chứa các thuộc tính: mã sinh viên (ID), tên sinh viên, học kỳ và tên khóa học.
 * Lớp này hiện thực giao diện {@link Comparable} để hỗ trợ việc sắp xếp danh sách theo tên sinh viên.
 * 
 * @author Administrator
 */
public class Student implements Comparable<Student> {

    private String studentId;
    private String studentName;
    private String semester;
    private Course courseName;

    /**
     * Khởi tạo đối tượng Student mặc định không có tham số ban đầu.
     */
    public Student() {
    }

    /**
     * Khởi tạo đối tượng Student với đầy đủ các thuộc tính ban đầu.
     * 
     * @param studentId Mã số sinh viên (ID)
     * @param studentName Họ và tên sinh viên
     * @param semester Học kỳ tham gia học (ví dụ: Fall 2023)
     * @param courseName Tên môn học đăng ký (thuộc kiểu enum Course: Java, .Net, C/C++)
     */
    public Student(String studentId, String studentName, String semester, Course courseName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    /**
     * Lấy mã số của sinh viên (ID).
     * 
     * @return Mã sinh viên dưới dạng chuỗi ký tự (String)
     */
    public String getId() {
        return studentId;
    }

    /**
     * Thiết lập hoặc cập nhật mã số cho sinh viên.
     * 
     * @param studentId Mã sinh viên mới cần gán
     */
    public void setId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Lấy họ và tên của sinh viên.
     * 
     * @return Tên sinh viên dưới dạng chuỗi ký tự (String)
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Thiết lập hoặc cập nhật họ và tên cho sinh viên.
     * 
     * @param studentName Tên mới cần gán cho sinh viên
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Lấy tên học kỳ mà sinh viên theo học môn này.
     * 
     * @return Tên học kỳ dưới dạng chuỗi ký tự (ví dụ: "Fall 2023")
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Thiết lập hoặc cập nhật học kỳ theo học cho sinh viên.
     * 
     * @param semester Tên học kỳ mới cần gán
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Lấy đối tượng khóa học mà sinh viên đang theo học.
     * 
     * @return Đối tượng Course đại diện cho khóa học (Java, .Net, C/C++)
     */
    public Course getCourseName() {
        return courseName;
    }

    /**
     * Thiết lập hoặc cập nhật môn học cho sinh viên.
     * 
     * @param courseName Đối tượng Course mới cần gán
     */
    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    /**
     * So sánh tên của đối tượng sinh viên hiện tại với một đối tượng sinh viên khác.
     * Phương thức so sánh không phân biệt chữ hoa, chữ thường theo bảng chữ cái A-Z,
     * phục vụ cho yêu cầu sắp xếp danh sách sinh viên.
     * 
     * @param otherStudent Đối tượng sinh viên khác cần so sánh
     * @return Số âm nếu tên sinh viên này đứng trước, 0 nếu bằng nhau, số dương nếu đứng sau
     */
    @Override
    public int compareTo(Student otherStudent) {
        return this.studentName.compareToIgnoreCase(otherStudent.studentName);
    }

    /**
     * Hiển thị thông tin sinh viên kèm theo số thứ tự hiển thị ra màn hình console.
     * Định dạng các cột hiển thị: Số thứ tự, Tên sinh viên, Học kỳ, Tên môn học.
     * Phương thức này được sử dụng chủ yếu trong chức năng Cập nhật/Xóa (Update/Delete).
     * 
     * @param orderNumber Số thứ tự bản ghi được đánh số trong danh sách hiển thị
     */
    public void displayStudentWithOrder(int orderNumber) {
        System.out.printf("| %-3d | %-18s | %-12s | %-13s |%n", orderNumber, studentName, semester, courseName.getLanguage());
    }

    /**
     * Hiển thị thông tin chi tiết của sinh viên không kèm số thứ tự ra màn hình console.
     * Định dạng các cột hiển thị: Tên sinh viên, Học kỳ, Tên môn học.
     * Phương thức này được sử dụng trong chức năng Tìm kiếm và Sắp xếp (Find and Sort).
     */
    public void displayStudentInformation() {
        System.out.printf("| %-18s | %-12s | %-13s |%n", studentName, semester, courseName.getLanguage());
    }

    /**
     * Trả về chuỗi định dạng thông tin của sinh viên gồm tên, học kỳ và môn học.
     * 
     * @return Chuỗi biểu diễn thông tin sinh viên
     */
    @Override
    public String toString() {
        return String.format("%-18s | %-12s | %-13s", studentName, semester, courseName.getLanguage());
    }
}
