/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Course;
import entity.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ui.CourseInput;
import utils.Validator;

/**
 * Model class quản lý danh sách sinh viên và các nghiệp vụ dữ liệu.
 * 
 * @author Administrator
 */
public class StudentManager {

    private final List<Student> studentList = new ArrayList<>();
    private final CourseInput courseInput = new CourseInput();

    /**
     * Khởi tạo StudentManager và nạp dữ liệu mẫu ban đầu.
     */
    public StudentManager() {
        initializeSampleStudentData();
    }

    /**
     * Nạp sẵn danh sách sinh viên mẫu theo đề bài.
     */
    private void initializeSampleStudentData() {
        studentList.add(new Student("he187001", "Nguyen Van A", "Fall 2023", Course.JAVA));
        studentList.add(new Student("he187001", "Nguyen Van A", "Fall 2024", Course.JAVA));
        studentList.add(new Student("he187002", "Tran Thi B", "Spring 2023", Course.DOT_NET));
        studentList.add(new Student("he187003", "Le Van C", "Fall 2024", Course.CPP));
        studentList.add(new Student("he187004", "Pham Thi D", "Summer 2023", Course.JAVA));
        studentList.add(new Student("he187005", "Hoang Van E", "Fall 2023", Course.DOT_NET));
        studentList.add(new Student("he187006", "Nguyen Thi F", "Spring 2024", Course.JAVA));
        studentList.add(new Student("he187007", "Bui Van G", "Fall 2023", Course.CPP));
        studentList.add(new Student("he187008", "Dang Thi H", "Summer 2024", Course.JAVA));
        studentList.add(new Student("he187009", "Vo Van I", "Spring 2023", Course.DOT_NET));
    }

    /**
     * Lấy tổng số lượng sinh viên trong hệ thống.
     * 
     * @return Tổng số lượng sinh viên
     */
    public int getTotalStudentCount() {
        return studentList.size();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Lấy tên sinh viên theo ID nếu đã tồn tại trong danh sách.
     * 
     * @param studentId Mã sinh viên cần tìm
     * @return Tên sinh viên nếu tìm thấy, ngược lại null
     */
    public String getStudentNameById(String studentId) {
        for (Student existingStudent : studentList) {
            if (existingStudent.getId().equalsIgnoreCase(studentId)) {
                return existingStudent.getStudentName();
            }
        }
        return null;
    }

    /**
     * Đồng bộ tên sinh viên cho tất cả bản ghi có cùng ID.
     * 
     * @param studentId Mã sinh viên
     * @param updatedStudentName Tên mới cần cập nhật
     */
    public void updateStudentNameById(String studentId, String updatedStudentName) {
        for (Student existingStudent : studentList) {
            if (existingStudent.getId().equalsIgnoreCase(studentId)) {
                existingStudent.setStudentName(updatedStudentName);
            }
        }
    }

    /**
     * Kiểm tra xem bản ghi sinh viên đã tồn tại chưa (cùng ID, cùng kỳ, cùng môn).
     * 
     * @param newStudent Đối tượng sinh viên mới cần kiểm tra
     * @return true nếu trùng lặp, ngược lại false
     */
    public boolean isDuplicateRecord(Student newStudent) {
        for (Student existingStudent : studentList) {
            if (existingStudent.getId().equalsIgnoreCase(newStudent.getId())
                    && existingStudent.getSemester().equalsIgnoreCase(newStudent.getSemester())
                    && existingStudent.getCourseName() == newStudent.getCourseName()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Thêm sinh viên mới vào danh sách nếu không trùng lặp.
     * 
     * @param newStudent Đối tượng sinh viên cần thêm
     * @return true nếu thêm thành công, false nếu trùng lặp
     */
    public boolean addStudent(Student newStudent) {
        if (isDuplicateRecord(newStudent)) {
            return false;
        }
        return studentList.add(newStudent);
    }

    /**
     * Hiển thị toàn bộ danh sách sinh viên hiện có.
     */
    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        System.out.println("| No. | Student Name       | Semester     | Course Name   |");
        for (int index = 0; index < studentList.size(); index++) {
            studentList.get(index).displayStudentWithOrder(index + 1);
        }
    }

    /**
     * Tìm kiếm sinh viên theo tên hoặc một phần tên.
     * 
     * @param searchKeyword Từ khóa tìm kiếm
     * @return Danh sách sinh viên thỏa mãn
     */
    public List<Student> findStudentsByName(String searchKeyword) {
        List<Student> foundStudentList = new ArrayList<>();
        for (Student currentStudent : studentList) {
            if (currentStudent.getStudentName().toLowerCase().contains(searchKeyword.toLowerCase())) {
                foundStudentList.add(currentStudent);
            }
        }
        return foundStudentList;
    }

    /**
     * Sắp xếp danh sách sinh viên theo tên tăng dần A-Z.
     * 
     * @param studentListToSort Danh sách sinh viên cần sắp xếp
     */
    public void sortStudentsByName(List<Student> studentListToSort) {
        Collections.sort(studentListToSort);
    }

    /**
     * Tìm kiếm tất cả bản ghi của một sinh viên theo ID.
     * 
     * @param studentId Mã sinh viên cần tìm
     * @return Danh sách các bản ghi của sinh viên
     */
    public List<Student> findStudentsById(String studentId) {
        List<Student> matchingStudentList = new ArrayList<>();
        for (Student currentStudent : studentList) {
            if (currentStudent.getId().equalsIgnoreCase(studentId)) {
                matchingStudentList.add(currentStudent);
            }
        }
        return matchingStudentList;
    }

    /**
     * Cập nhật thông tin cho một bản ghi sinh viên.
     * 
     * @param studentToUpdate Bản ghi sinh viên cần cập nhật
     */
    public void updateStudentInformation(Student studentToUpdate) {
        String updatedName = Validator.getString(
                "Update Student name [" + studentToUpdate.getStudentName() + "]: ",
                "Invalid Student name.",
                ".*|[A-Za-z\\s]+");
        if (!updatedName.isEmpty()) {
            updateStudentNameById(studentToUpdate.getId(), updatedName);
        }

        String updatedSemester = Validator.getString(
                "Update Semester [" + studentToUpdate.getSemester() + "]: ",
                "Invalid Semester (e.g., Fall 2023).",
                ".*|[a-zA-Z0-9\\s]+");
        if (!updatedSemester.isEmpty()) {
            studentToUpdate.setSemester(updatedSemester);
        }

        String confirmationToUpdateCourse = Validator.getString(
                "Do you want to update the course? (Y/N): ",
                "Please enter Y or N.",
                "[yYnN]");
        if (confirmationToUpdateCourse.equalsIgnoreCase("Y")) {
            Course updatedCourse = courseInput.inputCourse();
            studentToUpdate.setCourseName(updatedCourse);
        }
    }

    /**
     * Xóa một bản ghi sinh viên khỏi danh sách.
     * 
     * @param studentToDelete Bản ghi cần xóa
     * @return true nếu xóa thành công
     */
    public boolean deleteStudent(Student studentToDelete) {
        return studentList.remove(studentToDelete);
    }

    /**
     * Thống kê số lượng khóa học theo từng sinh viên.
     * 
     * @return Map lưu trữ kết quả thống kê
     */
    public Map<String, Map<Course, Integer>> generateCourseReportData() {
        Map<String, Map<Course, Integer>> courseReportMap = new HashMap<>();
        for (Student currentStudent : studentList) {
            String currentStudentName = currentStudent.getStudentName();
            Course currentStudentCourse = currentStudent.getCourseName();
            courseReportMap.computeIfAbsent(currentStudentName, keyName -> new HashMap<>())
                           .merge(currentStudentCourse, 1, Integer::sum);
        }
        return courseReportMap;
    }
}