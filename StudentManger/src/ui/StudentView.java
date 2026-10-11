package ui;

import entity.Course;
import entity.Student;
import java.util.List;
import java.util.Map;

/**
 * Lớp View chịu trách nhiệm hiển thị dữ liệu sinh viên ra console.
 * KHÔNG chứa business logic, chỉ xử lý I/O.
 *
 * @author Administrator
 */
public class StudentView {

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
