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
 * Model class quản lý danh sách sinh viên và các nghiệp vụ xử lý dữ liệu.
 * Cung cấp các chức năng: thêm, tìm kiếm, sắp xếp, cập nhật, xóa và thống kê khóa học.
 * 
 * @author Administrator
 */
public class StudentManager {

    private final List<Student> studentList = new ArrayList<>();
    private final CourseInput courseInput = new CourseInput();

    /**
     * Khởi tạo đối tượng StudentManager và nạp dữ liệu mẫu ban đầu.
     * Tự động gọi phương thức initializeSampleStudentData() để nạp sẵn danh sách sinh viên ban đầu.
     */
    public StudentManager() {
        initializeSampleStudentData();
    }

    /**
     * Nạp sẵn danh sách sinh viên mẫu theo yêu cầu đề bài vào danh sách quản lý.
     * Tạo sẵn các bản ghi với thông tin sinh viên, học kỳ và các khóa học (Java, .Net, C/C++)
     * nhằm phục vụ mục đích kiểm thử và đáp ứng số lượng dữ liệu khởi tạo tối thiểu.
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
     * Lấy tổng số lượng bản ghi sinh viên hiện có trong danh sách quản lý.
     * 
     * @return Tổng số lượng bản ghi sinh viên (int)
     */
    public int getTotalStudentCount() {
        return studentList.size();
    }

    /**
     * Lấy danh sách toàn bộ các sinh viên đang được quản lý trong hệ thống.
     * 
     * @return Danh sách các đối tượng Student (List&lt;Student&gt;)
     */
    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Lấy tên của sinh viên dựa trên mã sinh viên (ID) nếu đã tồn tại trong danh sách.
     * Phương thức duyệt qua danh sách và so sánh mã không phân biệt chữ hoa, chữ thường.
     * 
     * @param studentId Mã sinh viên cần tìm kiếm
     * @return Tên sinh viên (String) nếu tìm thấy, hoặc null nếu mã sinh viên chưa tồn tại
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
     * Đồng bộ và cập nhật tên sinh viên cho tất cả các bản ghi có cùng mã sinh viên (ID).
     * Đảm bảo tính nhất quán dữ liệu (một sinh viên chỉ có một tên duy nhất dù học nhiều môn hay nhiều kỳ).
     * 
     * @param studentId Mã sinh viên cần cập nhật tên
     * @param updatedStudentName Tên mới cần áp dụng cho sinh viên
     */
    public void updateStudentNameById(String studentId, String updatedStudentName) {
        for (Student existingStudent : studentList) {
            if (existingStudent.getId().equalsIgnoreCase(studentId)) {
                existingStudent.setStudentName(updatedStudentName);
            }
        }
    }

    /**
     * Kiểm tra xem một bản ghi sinh viên đã tồn tại trong danh sách hay chưa.
     * Một bản ghi được coi là trùng lặp nếu trùng cả 3 tiêu chí: Mã sinh viên (ID), Học kỳ (Semester), và Tên khóa học (CourseName).
     * 
     * @param newStudent Đối tượng sinh viên cần kiểm tra trùng lặp
     * @return true nếu bản ghi đã tồn tại trong danh sách, ngược lại trả về false
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
     * Thêm một sinh viên mới vào danh sách quản lý nếu không bị trùng lặp.
     * Thực hiện kiểm tra trùng lặp trước khi thêm để tránh ghi nhận dữ liệu trùng (cùng ID, cùng kỳ, cùng môn).
     * 
     * @param newStudent Đối tượng sinh viên mới cần thêm
     * @return true nếu thêm thành công vào danh sách, false nếu bản ghi bị trùng lặp
     */
    public boolean addStudent(Student newStudent) {
        if (isDuplicateRecord(newStudent)) {
            return false;
        }
        return studentList.add(newStudent);
    }

    /**
     * Hiển thị toàn bộ danh sách các sinh viên hiện có ra màn hình console.
     * In dạng bảng bao gồm số thứ tự, tên sinh viên, học kỳ và tên môn học.
     * Nếu danh sách rỗng, hiển thị thông báo chưa có dữ liệu.
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
     * Tìm kiếm các sinh viên có tên chứa từ khóa tìm kiếm (không phân biệt chữ hoa, chữ thường).
     * 
     * @param searchKeyword Từ khóa hoặc một phần tên sinh viên cần tìm
     * @return Danh sách các sinh viên thỏa mãn điều kiện tìm kiếm (List&lt;Student&gt;)
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
     * Sắp xếp danh sách sinh viên theo thứ tự tăng dần của tên (từ A đến Z).
     * Sử dụng phương thức sắp xếp mặc định dựa trên phương thức compareTo đã được cài đặt trong lớp Student.
     * 
     * @param studentListToSort Danh sách sinh viên cần sắp xếp
     */
    public void sortStudentsByName(List<Student> studentListToSort) {
        Collections.sort(studentListToSort);
    }

    /**
     * Tìm kiếm tất cả các bản ghi học tập của một sinh viên dựa trên mã sinh viên (ID).
     * So sánh mã không phân biệt chữ hoa hay chữ thường.
     * 
     * @param studentId Mã sinh viên cần tìm kiếm
     * @return Danh sách các bản ghi của sinh viên có mã tương ứng (List&lt;Student&gt;)
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
     * Cập nhật thông tin cho một bản ghi sinh viên đã chọn (tên sinh viên, học kỳ, khóa học).
     * Cho phép giữ nguyên giá trị cũ nếu người dùng để trống.
     * Nếu tên sinh viên thay đổi, tự động đồng bộ tên mới cho tất cả các bản ghi có cùng mã sinh viên (ID).
     * 
     * @param studentToUpdate Bản ghi sinh viên được chọn để cập nhật thông tin
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
     * Xóa một bản ghi sinh viên được chỉ định khỏi danh sách quản lý.
     * 
     * @param studentToDelete Đối tượng bản ghi sinh viên cần xóa
     * @return true nếu xóa thành công, false nếu không tìm thấy bản ghi cần xóa
     */
    public boolean deleteStudent(Student studentToDelete) {
        return studentList.remove(studentToDelete);
    }

    /**
     * Thống kê và tổng hợp tổng số lượng từng khóa học mà mỗi sinh viên đã đăng ký học.
     * Dữ liệu được nhóm theo tên sinh viên và số lần học từng loại khóa học.
     * 
     * @return Map với khóa chính là tên sinh viên, giá trị là Map chứa từng loại môn học (Course) và số lượng tương ứng (Integer)
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
