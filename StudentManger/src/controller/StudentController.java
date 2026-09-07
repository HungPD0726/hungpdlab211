package controller;

import entity.Report;
import entity.Student;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Controller trong mô hình MVC:
 * - Chuyên trách toàn bộ logic nghiệp vụ (thêm, sửa, xóa, tìm kiếm, sắp xếp, thống kê báo cáo).
 * - Quản lý nguồn dữ liệu (ArrayList<Student>).
 * - TUYỆT ĐỐI KHÔNG chứa mã hiển thị (System.out) hay nhập liệu (Scanner/Validator).
 */
public class StudentController {

    private final ArrayList<Student> listStudent;

    public StudentController() {
        this.listStudent = new ArrayList<>();
        generateSampleData();
    }

    /**
     * Dữ liệu mẫu ban đầu đúng với ví dụ trong đề bài
     */
    private void generateSampleData() {
        listStudent.add(new Student("S01", "Nguyen Van A", "Fall2023", "Java"));
        listStudent.add(new Student("S01", "Nguyen Van A", "Spring2024", "Java"));
        listStudent.add(new Student("S02", "Nguyen Van B", "Fall2023", ".Net"));
        listStudent.add(new Student("S02", "Nguyen Van B", "Spring2024", "Java"));
        listStudent.add(new Student("S03", "Nguyen Van C", "Fall2023", "Java"));
        listStudent.add(new Student("S04", "Tran Thi Mai", "Summer2023", "C/C++"));
        listStudent.add(new Student("S05", "Le Hoang Nam", "Fall2023", "Java"));
        listStudent.add(new Student("S06", "Pham Minh Tuan", "Spring2024", ".Net"));
        listStudent.add(new Student("S07", "Vu Quoc Anh", "Fall2023", "C/C++"));
        listStudent.add(new Student("S08", "Doan Bao Ngoc", "Spring2024", "Java"));
    }

    public boolean isEmpty() {
        return listStudent.isEmpty();
    }

    public int getRecordCount() {
        return listStudent.size();
    }

    /**
     * Lấy tên sinh viên theo ID nếu đã tồn tại trong danh sách
     */
    public String getStudentNameById(String id) {
        for (Student s : listStudent) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s.getStudentName();
            }
        }
        return null;
    }

    /**
     * Kiểm tra bản ghi trùng lặp (Cùng ID, cùng Semester, cùng Course)
     */
    public boolean isDuplicateRecord(String id, String semester, String course) {
        for (Student s : listStudent) {
            if (s.getId().equalsIgnoreCase(id)
                    && s.getSemester().equalsIgnoreCase(semester)
                    && s.getCourseName().equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 1. CREATE: Thêm một sinh viên mới
     * @return true nếu thêm thành công, false nếu bản ghi bị trùng lặp
     */
    public boolean addStudent(Student student) {
        if (isDuplicateRecord(student.getId(), student.getSemester(), student.getCourseName())) {
            return false;
        }
        listStudent.add(student);
        return true;
    }

    /**
     * 2. FIND AND SORT: Tìm kiếm theo tên và sắp xếp tăng dần A-Z
     * @return danh sách sinh viên phù hợp đã được sắp xếp
     */
    public ArrayList<Student> searchAndSortByName(String keyword) {
        ArrayList<Student> matchedList = new ArrayList<>();
        for (Student s : listStudent) {
            if (s.getStudentName().toLowerCase().contains(keyword.toLowerCase())) {
                matchedList.add(s);
            }
        }
        Collections.sort(matchedList);
        return matchedList;
    }

    /**
     * Lấy tất cả các bản ghi có cùng ID
     */
    public ArrayList<Student> getRecordsById(String id) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : listStudent) {
            if (s.getId().equalsIgnoreCase(id)) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * 3. DELETE: Xóa tất cả các bản ghi của sinh viên có ID chỉ định
     * @return true nếu xóa thành công ít nhất 1 bản ghi
     */
    public boolean deleteStudentById(String id) {
        return listStudent.removeIf(s -> s.getId().equalsIgnoreCase(id));
    }

    /**
     * 3. UPDATE: Cập nhật thông tin cho 1 bản ghi sinh viên
     * - Tự động đồng bộ tên mới cho tất cả bản ghi có cùng ID
     * @return true nếu cập nhật thành công, false nếu vi phạm trùng lặp với bản ghi khác
     */
    public boolean updateStudentRecord(Student targetStudent, String newName, String newSemester, String newCourse) {
        // Kiểm tra xem dữ liệu mới có bị trùng với bản ghi khác của cùng sinh viên hay không
        for (Student s : listStudent) {
            if (s != targetStudent && s.getId().equalsIgnoreCase(targetStudent.getId())
                    && s.getSemester().equalsIgnoreCase(newSemester)
                    && s.getCourseName().equalsIgnoreCase(newCourse)) {
                return false;
            }
        }

        // Đồng bộ tên sinh viên cho mọi bản ghi cùng ID
        String studentId = targetStudent.getId();
        for (Student s : listStudent) {
            if (s.getId().equalsIgnoreCase(studentId)) {
                s.setStudentName(newName);
            }
        }

        // Cập nhật kỳ học và môn học
        targetStudent.setSemester(newSemester);
        targetStudent.setCourseName(newCourse);
        return true;
    }

    /**
     * 4. REPORT: Thống kê số lượng môn học của từng sinh viên
     * @return danh sách các đối tượng Report
     */
    public ArrayList<Report> generateReports() {
        ArrayList<Report> reportList = new ArrayList<>();
        for (Student s : listStudent) {
            Report existingReport = findReport(reportList, s.getId(), s.getCourseName());
            if (existingReport != null) {
                existingReport.setTotalCourse(existingReport.getTotalCourse() + 1);
            } else {
                reportList.add(new Report(s.getId(), s.getStudentName(), s.getCourseName(), 1));
            }
        }
        return reportList;
    }

    private Report findReport(ArrayList<Report> reportList, String id, String courseName) {
        for (Report r : reportList) {
            if (r.getId().equalsIgnoreCase(id) && r.getCourseName().equalsIgnoreCase(courseName)) {
                return r;
            }
        }
        return null;
    }
}
