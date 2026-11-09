package entity;

/**
 * Entity class representing a student enrollment record in the system.
 * Implements {@link Comparable} to provide natural alphabetical ordering by student name.
 *
 * @author HP
 */
public class Student implements Comparable<Student> {

    private String id;
    private String studentName;
    private String semester;
    private Course courseName;

    /**
     * Default no-argument constructor.
     */
    public Student() {
    }

    /**
     * Parameterized constructor to instantiate a new student entity.
     *
     * @param id Unique identifier of the student.
     * @param studentName Full name of the student.
     * @param semester Semester of enrollment (e.g., Fall 2023, Spring 2024).
     * @param courseName Enrolled course enum ({@link Course}: JAVA, DOT_NET, CPP).
     */
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

    /**
     * Displays student information with a 1-based order index.
     * Typically used for full list views or search results where selection by index is needed.
     *
     * @param number 1-based order number to display.
     */
    public void displayStudentWithOrder(int number) {
        System.out.printf("| %-3d |%-18s | %-12s | %-13s|%n", number, studentName, semester, courseName.getLanguage());
    }

    /**
     * Displays student information without an order index.
     * Used for general search result displays and sorted listings.
     */
    public void displayStudentInfor() {
        System.out.printf("| %-18s | %-12s | %-13s|%n", studentName, semester, courseName.getLanguage());
    }

    /**
     * Formats the student entity into a aligned table row string.
     *
     * @return Formatted string representation of the student.
     */
    @Override
    public String toString() {
        return String.format("| %-18s | %-12s | %-13s", studentName, semester, courseName.getLanguage());
    }

    /**
     * Compares natural ordering between two student objects by student name (A-Z).
     * <p>
     * Execution flow:
     * - Uses {@link String#compareToIgnoreCase(String)} on {@code studentName}.
     * - Returns negative if this student's name precedes the other.
     * - Returns 0 if names are identical (case-insensitive).
     * - Returns positive if this student's name succeeds the other.
     *
     * @param newStudent The student instance to compare against.
     * @return A negative integer, zero, or a positive integer representing sort order.
     */
    @Override
    public int compareTo(Student newStudent) {
        return this.studentName.compareToIgnoreCase(newStudent.studentName);
    }

}
