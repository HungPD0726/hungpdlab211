/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 * Entity class đại diện cho đối tượng Sinh viên.
 * 
 * @author Administrator
 */
public class Student implements Comparable<Student> {

    private String studentId;
    private String studentName;
    private String semester;
    private Course courseName;

    public Student() {
    }

    public Student(String studentId, String studentName, String semester, Course courseName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return studentId;
    }

    public void setId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Course getCourseName() {
        return courseName;
    }

    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    @Override
    public int compareTo(Student otherStudent) {
        return this.studentName.compareToIgnoreCase(otherStudent.studentName);
    }

    /**
     * Hiển thị thông tin sinh viên kèm số thứ tự (dùng cho bảng Update/Delete).
     * 
     * @param orderNumber Số thứ tự bản ghi hiển thị
     */
    public void displayStudentWithOrder(int orderNumber) {
        System.out.printf("| %-3d | %-18s | %-12s | %-13s |%n", orderNumber, studentName, semester, courseName.getLanguage());
    }

    /**
     * Hiển thị thông tin sinh viên không kèm số thứ tự (dùng cho Find/Sort).
     */
    public void displayStudentInformation() {
        System.out.printf("| %-18s | %-12s | %-13s |%n", studentName, semester, courseName.getLanguage());
    }

    @Override
    public String toString() {
        return String.format("%-18s | %-12s | %-13s", studentName, semester, courseName.getLanguage());
    }
}