package ui;

import entity.Course;
import entity.Student;
import java.util.List;
import java.util.Map;
import model.StudentManager;
import utils.Validator;

/**
 * Lớp View quản lý toàn bộ giao diện Console của thực thể Student.
 * Gộp chung cả phần tiếp nhận dữ liệu đầu vào (Prompt/Input) và hiển thị (Display).
 *
 * @author Administrator
 */
public class StudentView {

    private final CourseInput courseInput;

    /**
     * Khởi tạo StudentView với CourseInput mặc định.
     */
    public StudentView() {
        this(new CourseInput());
    }

    /**
     * Constructor hỗ trợ Dependency Injection.
     *
     * @param courseInput Đối tượng tiếp nhận thông tin môn học
     */
    public StudentView(CourseInput courseInput) {
        this.courseInput = courseInput;
    }

    /**
     * Nhập thông tin sinh viên mới từ bàn phím.
     * Tự động nhận diện tên nếu ID đã tồn tại trong hệ thống.
     *
     * @param studentManager Đối tượng quản lý sinh viên để tra cứu ID
     * @return Đối tượng Student mới
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
            System.out.println(
                    "-> ID already exists in the system. Student Name: " + studentName);
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

    /**
     * Hiển thị danh sách sinh viên kèm số thứ tự (dùng cho Update/Delete).
     *
     * @param studentList Danh sách sinh viên cần hiển thị
     */
    public void displayStudentsWithOrder(List<Student> studentList) {
        if (studentList == null || studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        System.out.println("Found Students:");
        System.out.println("| No. | Student Name       | Semester     | Course Name   |");
        for (int index = 0; index < studentList.size(); index++) {
            Student student = studentList.get(index);
            System.out.printf("| %-3d | %-18s | %-12s | %-13s |%n",
                    index + 1,
                    student.getStudentName(),
                    student.getSemester(),
                    student.getCourseName().getLanguage());
        }
    }

    /**
     * Hiển thị danh sách sinh viên không kèm số thứ tự (dùng cho Find/Sort).
     *
     * @param studentList Danh sách sinh viên cần hiển thị
     */
    public void displayStudents(List<Student> studentList) {
        if (studentList == null || studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        System.out.println("Found Students:");
        System.out.println("| Student Name       | Semester     | Course Name   |");
        for (Student currentStudent : studentList) {
            System.out.printf("| %-18s | %-12s | %-13s |%n",
                    currentStudent.getStudentName(),
                    currentStudent.getSemester(),
                    currentStudent.getCourseName().getLanguage());
        }
    }

    /**
     * Hiển thị bảng báo cáo thống kê số khóa học theo từng sinh viên.
     *
     * @param courseReportMap Bản đồ dữ liệu thống kê khóa học
     */
    public void displayCourseReport(Map<String, Map<Course, Integer>> courseReportMap) {
        if (courseReportMap == null || courseReportMap.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }
        System.out.println("Report:");
        System.out.println("| Student Name       | Course       | Total of Course |");
        for (Map.Entry<String, Map<Course, Integer>> studentReportEntry
                : courseReportMap.entrySet()) {
            String studentName = studentReportEntry.getKey();
            for (Map.Entry<Course, Integer> courseDetailEntry
                    : studentReportEntry.getValue().entrySet()) {
                System.out.printf("| %-18s | %-12s | %-15d |%n",
                        studentName,
                        courseDetailEntry.getKey().getLanguage(),
                        courseDetailEntry.getValue());
            }
        }
    }
}
