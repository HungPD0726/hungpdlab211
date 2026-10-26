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
 *
 * @author HP
 */
public final class StudentManager {

    private final List<Student> studentList = new ArrayList<>();
    private final CourseInput courseInput = new CourseInput();

    public StudentManager() {
        avaiableStudent();
    }

    public void avaiableStudent() {
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

    public int getTotalStudent() {
        return studentList.size();
    }

    public String getStudentById(String id) {
        String studentName = null;
        for (Student existingStudent : studentList) {
            if (existingStudent.getId().equalsIgnoreCase(id)) {
                studentName = existingStudent.getStudentName();
                break;
            }
        }
        return studentName;
    }

    public void updateStudentById(String id, String updateStudentName) {
        for (Student existingStudent : studentList) {
            if (existingStudent.getId().equalsIgnoreCase(id)) {
                existingStudent.setStudentName(updateStudentName);
            }
        }
    }

    public boolean isDuplicatedStudent(Student newStudent) {
        boolean isDuplicated = false;
        for (Student existingStudent : studentList) {
            if (existingStudent.getId().equalsIgnoreCase(newStudent.getId())
                    && existingStudent.getStudentName().equalsIgnoreCase(newStudent.getStudentName())
                    && existingStudent.getSemester().equalsIgnoreCase(newStudent.getSemester())
                    && existingStudent.getCourseName() == newStudent.getCourseName()) {
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }

    public boolean addStudent(Student newStudent) {
        boolean isAdded = false;
        if (!isDuplicatedStudent(newStudent)) {
            isAdded = studentList.add(newStudent);
        }
        return isAdded;
    }

    public boolean deleteStudent(Student deleteStudent) {
        return studentList.remove(deleteStudent);
    }

    public void displayAllStudent() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("| No. | Student Name   | Semester    | Course Name   |");
            for (int i = 0; i < studentList.size(); i++) {
                studentList.get(i).displayStudentWithOrder(i + 1);
            }
        }
    }

    public List<Student> findStudentByName(String keyName) {
        List<Student> foundStudentList = new ArrayList<>();
        for (Student currentStudent : studentList) {
            if (currentStudent.getStudentName().toLowerCase().contains(keyName.toLowerCase())) {
                foundStudentList.add(currentStudent);
            }
        }
        return foundStudentList;
    }

    public List<Student> findStudentById(String id) {
        List<Student> foundStudentList = new ArrayList<>();
        for (Student currentStudent : studentList) {
            if (currentStudent.getId().equalsIgnoreCase(id)) {
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

    public void updateStudentInformation(Student updateStudent) {
        String updateName = Validator.getString(
                "Update Student name [" + updateStudent.getStudentName() + "]: ",
                "Invalid student ID format.",
                "[a-zA-Z\\s]+");
        if (!updateName.isEmpty()) {
            updateStudentById(updateStudent.getId(), updateName);
        }

        String updateSemester = Validator.getString(
                "Update Semester [" + updateStudent.getSemester() + "]: ",
                "Invalid semester format.",
                "[a-zA-Z\\s]+");
        if (!updateSemester.isEmpty()) {
            updateStudent.setSemester(updateSemester);
        }
        String confirmInformation = Validator.getString(
                "Do you want to update course ? (Y/N): ",
                "Please Y or N",
                "[yYNn]");
        if (confirmInformation.equalsIgnoreCase("Y")) {
            Course updateCourse = courseInput.courseInput();
            updateStudent.setCourseName(updateCourse);
        }
    }

    public Map<String, Map<Course, Integer>> generrateCourseData() {
        Map<String, Map<Course, Integer>> courseReport = new HashMap<>();
        for (Student currentStudent : studentList) {
            String currentStudentName = currentStudent.getStudentName();
            Course currentStudentCourse = currentStudent.getCourseName();
            courseReport.computeIfAbsent(currentStudentName, keyName -> new HashMap<>())
                    .merge(currentStudentCourse, 1, Integer::sum);
        }
        return courseReport;
    }

}
