package model;

import entity.Course;
import entity.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model class quản lý danh sách sinh viên và các nghiệp vụ dữ liệu.
 * Chỉ chứa business logic, KHÔNG chứa I/O.
 *
 * @author Administrator
 */
public class StudentManager {

    private final List<Student> studentList = new ArrayList<>();

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
     * Kiểm tra xem bản ghi sinh viên đã tồn tại chưa (dựa trên equals của Student).
     *
     * @param newStudent Đối tượng sinh viên mới cần kiểm tra
     * @return true nếu trùng lặp, ngược lại false
     */
    public boolean isDuplicateRecord(Student newStudent) {
        return studentList.contains(newStudent);
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
     * Tìm kiếm sinh viên theo tên hoặc một phần tên.
     *
     * @param searchKeyword Từ khóa tìm kiếm
     * @return Danh sách sinh viên thỏa mãn
     */
    public List<Student> findStudentsByName(String searchKeyword) {
        List<Student> foundStudentList = new ArrayList<>();
        for (Student currentStudent : studentList) {
            if (currentStudent.getStudentName().toLowerCase()
                    .contains(searchKeyword.toLowerCase())) {
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
     * Chỉ nhận data đã được validate, không nhận input trực tiếp từ console.
     *
     * @param studentToUpdate Bản ghi sinh viên cần cập nhật
     * @param updatedName Tên mới (null hoặc empty = không cập nhật)
     * @param updatedSemester Học kỳ mới (null hoặc empty = không cập nhật)
     * @param updatedCourse Khóa học mới (null = không cập nhật)
     */
    public void updateStudentInformation(Student studentToUpdate, String updatedName,
            String updatedSemester, Course updatedCourse) {
        if (updatedName != null && !updatedName.isEmpty()) {
            updateStudentNameById(studentToUpdate.getId(), updatedName);
        }

        if (updatedSemester != null && !updatedSemester.isEmpty()) {
            studentToUpdate.setSemester(updatedSemester);
        }

        if (updatedCourse != null) {
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
            courseReportMap.computeIfAbsent(currentStudentName, key -> new HashMap<>())
                          .merge(currentStudentCourse, 1, Integer::sum);
        }
        return courseReportMap;
    }
}
