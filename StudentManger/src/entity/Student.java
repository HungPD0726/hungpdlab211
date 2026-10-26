package entity;

/**
 *
 * @author HP
 */
public class Student implements Comparable<Student> {

    private String id;
    private String studentName;
    private String semester;
    private Course courseName;

    public Student() {
    }

    public Student(String id, String studentName, String semester, Course courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void displayStudentWithOrder(int number) {
        System.out.printf("| %-3d |%-18s | %-12s | %-13s|%n", number, studentName, semester, courseName.getLanguage());
    }

    public void displayStudentInfor() {
        System.out.printf("| %-18s | %-12s | %-13s|%n", studentName, semester, courseName.getLanguage());
    }

    @Override
    public String toString() {
        return String.format("| %-18s | %-12s | %-13s", studentName, semester, courseName.getLanguage());
    }

    @Override
    public int compareTo(Student newStudent) {
        return this.studentName.compareToIgnoreCase(newStudent.studentName);
    }

}
