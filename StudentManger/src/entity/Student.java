package entity;

import java.util.Objects;

/**
 * Entity class đại diện cho đối tượng Sinh viên.
 * Pure POJO không chứa logic I/O Console.
 *
 * @author Administrator
 */
public class Student implements Comparable<Student> {

    private String studentId;
    private String studentName;
    private String semester;
    private Course courseName;

    public Student() {
    }

    public Student(String studentId, String studentName, String semester, Course courseName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return studentId;
    }

    public void setId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Course getCourseName() {
        return courseName;
    }

    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    @Override
    public int compareTo(Student otherStudent) {
        return this.studentName.compareToIgnoreCase(otherStudent.studentName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student other = (Student) obj;
        return (studentId != null && studentId.equalsIgnoreCase(other.studentId))
                && (semester != null && semester.equalsIgnoreCase(other.semester))
                && courseName == other.courseName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                studentId == null ? null : studentId.toLowerCase(),
                semester == null ? null : semester.toLowerCase(),
                courseName);
    }

    @Override
    public String toString() {
        return String.format("%-18s | %-12s | %-13s",
                studentName, semester, courseName.getLanguage());
    }
}
