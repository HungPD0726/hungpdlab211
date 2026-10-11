package controller;

import entity.Course;
import entity.Student;
import java.util.List;
import java.util.Map;
import model.StudentManager;
import ui.CourseInput;
import ui.StudentInput;
import ui.StudentView;
import utils.Validator;

/**
 * Controller trong mô hình MVC: Điều phối tương tác giữa View và Model.
 * Nhận input từ người dùng, xử lý luồng, gọi Model cho business logic,
 * và dùng View để hiển thị kết quả.
 *
 * @author Administrator
 */
public class StudentController {

    private final StudentManager studentManager;
    private final StudentInput studentInput;
    private final StudentView studentView;
    private final CourseInput courseInput;

    /**
     * Khởi tạo StudentController.
     */
    public StudentController() {
        studentManager = new StudentManager();
        studentInput = new StudentInput();
        studentView = new StudentView();
        courseInput = new CourseInput();
    }

    /**
     * Tạo mới sinh viên. Khi tổng số sinh viên >= 10, hỏi người dùng có muốn
     * tiếp tục hay không.
     */
    public void createStudent() {
        while (true) {
            Student newStudent = studentInput.inputStudent(studentManager);
            if (!studentManager.addStudent(newStudent)) {
                System.out.println(
                        "This student record (ID, semester, course) already exists!");
                continue;
            }
            int totalCount = studentManager.getTotalStudentCount();
            System.out.println("Create student successfully! (Total: " + totalCount + ")");

            // Đề bài: "User has to create at least 10 students,
            // if number of students > 10, ask Do you want to continue (Y/N)?"
            if (totalCount >= 10) {
                String continueChoice = Validator.getString(
                        "Do you want to continue (Y/N)? ",
                        "Please choose Y or N.",
                        "[yYnN]");
                if (continueChoice.equalsIgnoreCase("N")) {
                    break;
                }
            } else {
                System.out.println("-> Notice: Minimum 10 students required (Current: "
                        + totalCount + ").");
            }
        }
    }

    /**
     * Tìm kiếm sinh viên theo tên và hiển thị danh sách đã sắp xếp.
     */
    public void findAndSortStudents() {
        String searchKeyword = Validator.getString(
                "Enter name to search: ",
                "Invalid name.",
                "[A-Za-z\\s]+");
        List<Student> matchingStudentList = studentManager.findStudentsByName(searchKeyword);
        if (matchingStudentList.isEmpty()) {
            System.out.println("No students found with name containing: " + searchKeyword);
            return;
        }
        studentManager.sortStudentsByName(matchingStudentList);
        studentView.displayStudents(matchingStudentList);
    }

    /**
     * Tìm sinh viên theo ID và cho phép chọn Update (U) hoặc Delete (D).
     */
    public void updateOrDeleteStudent() {
        String searchStudentId = Validator.getString(
                "Enter Student ID (e.g., he187004): ",
                "Invalid ID format (alphanumeric only).",
                "[a-zA-Z0-9]+");
        List<Student> matchingStudentList = studentManager.findStudentsById(searchStudentId);
        if (matchingStudentList.isEmpty()) {
            System.out.println("Student with ID " + searchStudentId + " not found.");
            return;
        }
        studentView.displayStudentsWithOrder(matchingStudentList);

        int selectedRecordIndex = Validator.getInt(
                "Select student record to update/delete (1-"
                        + matchingStudentList.size() + "): ",
                "Please choose a number between 1 and " + matchingStudentList.size() + ".",
                "Invalid integer number.",
                1, matchingStudentList.size());
        Student selectedStudent = matchingStudentList.get(selectedRecordIndex - 1);

        String userActionChoice = Validator.getString(
                "Do you want to Update (U) or Delete (D) student? ",
                "Please enter U or D.",
                "[uUdD]");
        if (userActionChoice.equalsIgnoreCase("U")) {
            handleUpdateStudent(selectedStudent);
            System.out.println("Student updated successfully!");
        }
        if (userActionChoice.equalsIgnoreCase("D")) {
            studentManager.deleteStudent(selectedStudent);
            System.out.println("Student deleted successfully!");
        }
    }

    /**
     * Xử lý cập nhật thông tin sinh viên.
     * Controller nhận input từ người dùng và truyền data đã validate xuống Model.
     *
     * @param studentToUpdate Bản ghi sinh viên cần cập nhật
     */
    private void handleUpdateStudent(Student studentToUpdate) {
        String updatedName = Validator.getString(
                "Update Student name [" + studentToUpdate.getStudentName() + "]: ",
                "Invalid Student name.",
                "[A-Za-z\\s]*");
        if (updatedName.isEmpty()) {
            updatedName = studentToUpdate.getStudentName();
        }

        String updatedSemester = Validator.getString(
                "Update Semester [" + studentToUpdate.getSemester() + "]: ",
                "Invalid Semester (e.g., Fall 2023).",
                "[a-zA-Z0-9\\s]*");
        if (updatedSemester.isEmpty()) {
            updatedSemester = studentToUpdate.getSemester();
        }

        String confirmationToUpdateCourse = Validator.getString(
                "Do you want to update the course? (Y/N): ",
                "Please enter Y or N.",
                "[yYnN]");
        Course updatedCourse = null;
        if (confirmationToUpdateCourse.equalsIgnoreCase("Y")) {
            updatedCourse = courseInput.inputCourse();
        }

        studentManager.updateStudentInformation(
                studentToUpdate, updatedName, updatedSemester, updatedCourse);
    }

    /**
     * Báo cáo thống kê số lượng khóa học theo từng sinh viên.
     */
    public void displayCourseReport() {
        Map<String, Map<Course, Integer>> courseReportMap =
                studentManager.generateCourseReportData();
        studentView.displayCourseReport(courseReportMap);
    }
}
