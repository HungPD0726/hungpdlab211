package view;

import controller.StudentController;
import entity.Report;
import entity.Student;
import validator.Validator;

import java.util.ArrayList;

/**
 * Tầng View:
 * - Hiển thị menu, nhận dữ liệu nhập (sử dụng Validator.getString với REGEX)
 * - Gọi Controller để thực hiện các thao tác dữ liệu
 */
public class StudentView {

    private final StudentController controller;

    // Các biểu thức chính quy (REGEX) dùng cho Validator.getString
    private static final String REGEX_NOT_EMPTY = "^\\S.*$";
    private static final String REGEX_COURSE = "^(?i)(Java|\\.Net|C/C\\+\\+)$";
    private static final String REGEX_YES_NO = "^(?i)[yn]$";
    private static final String REGEX_UPDATE_DELETE = "^(?i)[ud]$";

    public StudentView() {
        this.controller = new StudentController();
    }

    /**
     * Vòng lặp chính của chương trình
     */
    public void run() {
        while (true) {
            printMenu();
            int choice = Validator.getInt(
                    "Please choose (1 - 5): ",
                    "Choice must be between 1 and 5. Please choose again!",
                    "Invalid input! Please enter a valid number (1 - 5).",
                    1, 5
            );

            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    findAndSort();
                    break;
                case 3:
                    updateOrDelete();
                    break;
                case 4:
                    report();
                    break;
                case 5:
                    System.out.println("\nThank you for using Student Management System. Goodbye!");
                    return;
            }
        }
    }

    private void printMenu() {
        System.out.println("\nWELCOME TO STUDENT MANAGEMENT");
        System.out.println("  1. Create");
        System.out.println("  2. Find and Sort");
        System.out.println("  3. Update/Delete");
        System.out.println("  4. Report");
        System.out.println("  5. Exit");
        System.out.println("(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program).");
    }

    /**
     * Chức năng 1: Tạo sinh viên
     */
    private void createStudent() {
        System.out.println("\n========== CREATE STUDENT ==========");
        while (true) {
            String id = Validator.getString("Enter Student ID: ", "ID cannot be empty!", REGEX_NOT_EMPTY);

            // Nếu ID đã tồn tại -> lấy tên cũ, ngược lại -> nhập tên mới
            String name = controller.getStudentNameById(id);
            if (name != null) {
                System.out.println("-> ID already exists in the system. Student Name is: " + name);
            } else {
                name = Validator.getString("Enter Student Name: ", "Name cannot be empty!", REGEX_NOT_EMPTY);
            }

            String semester = Validator.getString("Enter Semester: ", "Semester cannot be empty!", REGEX_NOT_EMPTY);
            String courseRaw = Validator.getString("Enter Course (Java, .Net, C/C++): ",
                    "There are only three courses: Java, .Net, C/C++. Please re-enter!", REGEX_COURSE);
            String course = normalizeCourse(courseRaw);

            boolean isAdded = controller.addStudent(new Student(id, name, semester, course));
            if (!isAdded) {
                System.out.println("-> Error: This student has already registered for course '" + course + "' in semester '" + semester + "'!");
            } else {
                System.out.println("-> Student record added successfully! (Total records: " + controller.getRecordCount() + ")");
            }

            // Đề bài: Nếu số lượng sinh viên >= 10, hỏi tiếp tục (Y/N)
            if (controller.getRecordCount() >= 10) {
                String choiceYN = Validator.getString("Do you want to continue (Y/N)? ", "Please choose Y or N!", REGEX_YES_NO);
                if (choiceYN.equalsIgnoreCase("N")) {
                    break;
                }
            } else {
                System.out.println("-> Notice: Minimum 10 students required (Current: " + controller.getRecordCount() + ").");
            }
        }
    }

    /**
     * Chức năng 2: Tìm kiếm và sắp xếp
     */
    private void findAndSort() {
        System.out.println("\n========== FIND AND SORT ==========");
        if (controller.isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }

        String keyword = Validator.getString("Enter student name or a part of student name: ", "Keyword cannot be empty!", REGEX_NOT_EMPTY);
        ArrayList<Student> matchedList = controller.searchAndSortByName(keyword);

        if (matchedList.isEmpty()) {
            System.out.println("-> No student found matching '" + keyword + "'.");
            return;
        }

        System.out.println("\n--- Search & Sorted Results ---");
        System.out.printf("%-20s | %-10s | %-10s\n", "Student Name", "Semester", "Course Name");
        System.out.println("--------------------------------------------------");
        for (Student s : matchedList) {
            System.out.printf("%-20s | %-10s | %-10s\n", s.getStudentName(), s.getSemester(), s.getCourseName());
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Chức năng 3: Cập nhật hoặc xóa
     */
    private void updateOrDelete() {
        System.out.println("\n========== UPDATE / DELETE ==========");
        if (controller.isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }

        String id = Validator.getString("Enter student ID to find: ", "ID cannot be empty!", REGEX_NOT_EMPTY);
        ArrayList<Student> records = controller.getRecordsById(id);

        if (records.isEmpty()) {
            System.out.println("-> Student not found with ID: " + id);
            return;
        }

        String action = Validator.getString("Do you want to update (U) or delete (D) student? ", "Please choose U or D!", REGEX_UPDATE_DELETE);

        if (action.equalsIgnoreCase("D")) {
            // Delete
            String confirm = Validator.getString("Are you sure you want to delete all records of student with ID " + id + " (Y/N)? ", "Please choose Y or N!", REGEX_YES_NO);
            if (confirm.equalsIgnoreCase("Y")) {
                controller.deleteStudentById(id);
                System.out.println("-> Student with ID " + id + " deleted successfully!");
            } else {
                System.out.println("-> Delete canceled.");
            }
        } else {
            // Update
            System.out.println("\n--- Records of Student ID: " + id + " ---");
            System.out.printf("%-4s | %-8s | %-20s | %-10s | %-10s\n", "No.", "ID", "Student Name", "Semester", "Course Name");
            System.out.println("---------------------------------------------------------------");
            for (int i = 0; i < records.size(); i++) {
                Student s = records.get(i);
                System.out.printf("%-4d | %-8s | %-20s | %-10s | %-10s\n", (i + 1), s.getId(), s.getStudentName(), s.getSemester(), s.getCourseName());
            }
            System.out.println("---------------------------------------------------------------");

            int selectedIndex = 1;
            if (records.size() > 1) {
                selectedIndex = Validator.getInt(
                        "Choose record number to update (1 - " + records.size() + "): ",
                        "Out of range! Please choose from 1 to " + records.size() + "!",
                        "Invalid number! Please re-enter.",
                        1, records.size()
                );
            }
            Student targetStudent = records.get(selectedIndex - 1);

            System.out.println("\n-- Updating Record: " + targetStudent.getStudentName() + " (" + targetStudent.getCourseName() + ") --");
            String newName = Validator.getString("Enter new Student Name: ", "Name cannot be empty!", REGEX_NOT_EMPTY);
            String newSemester = Validator.getString("Enter new Semester: ", "Semester cannot be empty!", REGEX_NOT_EMPTY);
            String newCourseRaw = Validator.getString("Enter new Course (Java, .Net, C/C++): ",
                    "There are only three courses: Java, .Net, C/C++. Please re-enter!", REGEX_COURSE);
            String newCourse = normalizeCourse(newCourseRaw);

            boolean updated = controller.updateStudentRecord(targetStudent, newName, newSemester, newCourse);
            if (updated) {
                System.out.println("-> Student record updated successfully!");
            } else {
                System.out.println("-> Error: Update failed! Another record with course '" + newCourse + "' in semester '" + newSemester + "' already exists!");
            }
        }
    }

    /**
     * Chức năng 4: Báo cáo
     */
    private void report() {
        System.out.println("\n========== REPORT ==========");
        if (controller.isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }

        ArrayList<Report> reportList = controller.generateReports();

        System.out.println("\n--- Student Course Report ---");
        System.out.printf("%-20s | %-10s | %s\n", "Student Name", "Course", "Total of Course");
        System.out.println("---------------------------------------------");
        for (Report r : reportList) {
            System.out.printf("%-20s | %-10s | %d\n", r.getStudentName(), r.getCourseName(), r.getTotalCourse());
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * Chuẩn hóa tên khóa học về đúng định dạng chữ hoa/thường: Java, .Net, C/C++
     */
    private String normalizeCourse(String raw) {
        if (raw.equalsIgnoreCase("Java")) {
            return "Java";
        }
        if (raw.equalsIgnoreCase(".Net")) {
            return ".Net";
        }
        return "C/C++";
    }
}
