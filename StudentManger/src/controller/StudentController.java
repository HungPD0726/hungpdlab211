/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Course;
import entity.Student;
import java.util.List;
import java.util.Map;
import model.StudentManager;
import ui.StudentInput;
import utils.Validator;

/**
 * Controller trong mô hình kiến trúc MVC (Model - View - Controller).
 * Đóng vai trò điều phối luồng thực thi giữa giao diện người dùng (UI/View)
 * và tầng xử lý nghiệp vụ/dữ liệu (Model - StudentManager).
 *
 * @author Administrator
 */
public class StudentController {

    private final StudentManager studentManager;
    private final StudentInput studentInput;

    /**
     * Khởi tạo đối tượng StudentController.
     * Tạo mới instance của StudentManager (quản lý nghiệp vụ dữ liệu sinh viên)
     * và StudentInput (xử lý tiếp nhận dữ liệu đầu vào từ người dùng).
     */
    public StudentController() {
        studentManager = new StudentManager();
        studentInput = new StudentInput();
    }

    /**
     * Xử lý chức năng tạo mới sinh viên (Chức năng 1 - Create).
     * <p>
     * Luồng xử lý:
     * 1. Nhận thông tin sinh viên từ người dùng thông qua studentInput.
     * 2. Gọi studentManager.addStudent() để kiểm tra tính trùng lặp và thêm vào danh sách.
     * 3. Nếu bản ghi trùng (cùng ID, cùng kỳ, cùng môn học) thì thông báo lỗi và yêu cầu nhập lại.
     * 4. Khi tổng số sinh viên đạt từ 10 trở lên, hỏi người dùng có muốn tiếp tục hay không (Y/N).
     *    Nếu chọn 'N', kết thúc chức năng và quay về menu chính.
     */
    public void createStudent() {
        while (true) {
            Student newStudent = studentInput.inputStudent(studentManager);
            if (!studentManager.addStudent(newStudent)) {
                System.out.println("This student record (ID, semester, course) already exists!");
                continue;
            }
            System.out.println("Create student successfully! (Total students: " + studentManager.getTotalStudentCount() + ")");
            // Đề bài: "User has to create at least 10 students, if number of students greater than 10, the program shows message: Do you want to continue (Y/N)?"
            if (studentManager.getTotalStudentCount() >= 10) {
                String continueChoice = Validator.getString(
                        "Do you want to continue (Y/N)? ",
                        "Please choose Y or N.",
                        "[yYnN]");
                if (continueChoice.equalsIgnoreCase("N")) {
                    break;
                }
            } else {
                System.out.println("-> Notice: Minimum 10 students required (Current: " + studentManager.getTotalStudentCount() + ").");
            }
        }
    }

    /**
     * Xử lý chức năng tìm kiếm và sắp xếp sinh viên theo tên (Chức năng 2 - Find and Sort).
     * <p>
     * Luồng xử lý:
     * 1. Yêu cầu người dùng nhập tên hoặc một phần tên cần tìm kiếm.
     * 2. Tìm kiếm các sinh viên thỏa mãn điều kiện thông qua studentManager.findStudentsByName().
     * 3. Nếu không tìm thấy, thông báo cho người dùng và kết thúc hàm.
     * 4. Nếu tìm thấy, thực hiện sắp xếp danh sách kết quả theo tên tăng dần từ A-Z.
     * 5. Hiển thị danh sách kết quả đã sắp xếp ra màn hình dưới dạng bảng.
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
        System.out.println("Found Students:");
        System.out.println("| Student Name       | Semester     | Course Name   |");
        for (Student currentStudent : matchingStudentList) {
            currentStudent.displayStudentInformation();
        }
    }

    /**
     * Xử lý chức năng Cập nhật hoặc Xóa sinh viên (Chức năng 3 - Update/Delete).
     * <p>
     * Luồng xử lý:
     * 1. Yêu cầu người dùng nhập mã sinh viên (ID) cần tìm kiếm.
     * 2. Tìm kiếm toàn bộ các bản ghi của sinh viên có ID đó qua studentManager.findStudentsById().
     * 3. Nếu không tìm thấy sinh viên, thông báo lỗi và kết thúc hàm.
     * 4. Nếu tìm thấy, hiển thị danh sách các bản ghi kèm số thứ tự (No.).
     * 5. Cho phép người dùng chọn số thứ tự của bản ghi cần thao tác.
     * 6. Hỏi người dùng muốn cập nhật (U) hay xóa (D):
     *    - Chọn 'U': gọi studentManager.updateStudentInformation() để cập nhật tên, kỳ học, môn học.
     *    - Chọn 'D': gọi studentManager.deleteStudent() để xóa bản ghi khỏi hệ thống.
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
        System.out.println("Found Students:");
        System.out.println("| No. | Student Name       | Semester     | Course Name   |");
        for (int i = 0; i < matchingStudentList.size(); i++) {
            matchingStudentList.get(i).displayStudentWithOrder(i + 1);
        }
        int studentNumber = Validator.getInt("Please choose student ( 1 -" + matchingStudentList.size() + "): "
                , "Choice out of range.", "Please enter a valid integer.", 1, matchingStudentList.size());
        Student chooseStudent = matchingStudentList.get(studentNumber - 1);
        String choice = Validator.getString("Do you want to update (U) or delete (D)?: ",
                "Invalid choice. Please enter U or D.", "[uUdD]");
        if (choice.equalsIgnoreCase("U")) {
            studentManager.updateStudentInformation(chooseStudent);
            System.out.println("Update student successfully!");
        } else if (choice.equalsIgnoreCase("D")) {
            studentManager.deleteStudent(chooseStudent);
            System.out.println("Delete student successfully!");
        }
    }

    /**
     * Xử lý chức năng báo cáo và thống kê số lượng khóa học theo từng sinh viên (Chức năng 4 - Report).
     * <p>
     * Luồng xử lý:
     * 1. Lấy dữ liệu thống kê từ studentManager.generateCourseReportData().
     * 2. Nếu danh sách sinh viên rỗng, in thông báo chưa có dữ liệu báo cáo.
     * 3. Hiển thị bảng báo cáo gồm các cột: Tên sinh viên, Tên khóa học và Tổng số lần học môn đó.
     */
    public void displayCourseReport() {
        Map<String, Map<Course, Integer>> courseReportMap = studentManager.generateCourseReportData();
        if (courseReportMap.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }

        System.out.println("Report:");
        System.out.println("| Student Name       | Course       | Total of Course |");
        courseReportMap.forEach((studentName, courses)
                -> courses.forEach((course, total)
                        -> System.out.printf("| %-18s | %-12s | %-15s |%n",
                                 studentName, course.getLanguage(), total)
                )
        );
    }
}
