package model;

import entity.Course;
import entity.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ui.CourseInput;
import utils.Validator;

/**
 * Manages business logic and in-memory storage of student records.
 * Provides core operations: adding, deleting, updating, searching, sorting,
 * and aggregating course enrollment statistics.
 *
 * @author HP
 */
public final class StudentManager {

    private final List<Student> studentList = new ArrayList<>();
    private final CourseInput courseInput = new CourseInput();

    /**
     * Constructs a new StudentManager instance and initializes it with sample data.
     * <p>
     * Execution flow:
     * - Automatically invoked upon instantiating a new StudentManager object.
     * - Immediately calls {@link #avaiableStudent()} to populate 10 mock student records.
     */
    public StudentManager() {
        avaiableStudent();
    }

    /**
     * Pre-populates the internal student list with predefined mock data for testing and demonstration.
     * <p>
     * Execution flow:
     * - Creates and appends 10 {@link Student} instances into {@code studentList}.
     * - Covers multiple semesters (Fall, Spring, Summer) and courses (JAVA, DOT_NET, CPP)
     *   to facilitate testing search, update, sort, and reporting functions.
     */
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

//    public List<Student> getStudentList() {
//        return studentList;
//    }

    /**
     * Retrieves the total count of student enrollment records currently stored.
     * <p>
     * Execution flow:
     * - Calls {@code size()} on {@code studentList} to return the number of elements.
     *
     * @return The total count of student records in the list.
     */
    public int getTotalStudent() {
        return studentList.size();
    }

    /**
     * Searches for a student's name by their unique student ID.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Iterates sequentially through {@code studentList} using a for-each loop.</li>
     *   <li>Compares the student ID using {@code equalsIgnoreCase(id)} for case-insensitive matching.</li>
     *   <li>If a match is found, captures the student name and breaks the loop immediately for performance.</li>
     *   <li>Returns {@code null} if no matching record is found.</li>
     * </ul>
     *
     * @param id The student ID to search for (case-insensitive).
     * @return The matching student's name, or {@code null} if not found.
     */
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

    /**
     * Updates and synchronizes the student name across all records matching the given student ID.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>In real-world business logic, a student can enroll in multiple courses across semesters (same ID, multiple rows).</li>
     *   <li>Iterates through the entire {@code studentList}.</li>
     *   <li>For every record matching {@code id}, updates the name via {@code setStudentName(updateStudentName)}
     *       to maintain data consistency across all course enrollments.</li>
     * </ul>
     *
     * @param id The student ID whose records should be updated.
     * @param updateStudentName The new name to assign to the student.
     */
    public void updateStudentById(String id, String updateStudentName) {
        for (Student existingStudent : studentList) {
            if (existingStudent.getId().equalsIgnoreCase(id)) {
                existingStudent.setStudentName(updateStudentName);
            }
        }
    }

    /**
     * Checks whether an identical student enrollment record already exists in the system.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Iterates through the current student list.</li>
     *   <li>Compares all four attributes simultaneously:
     *     <ol>
     *       <li>Student ID (case-insensitive).</li>
     *       <li>Student Name (case-insensitive).</li>
     *       <li>Semester (case-insensitive).</li>
     *       <li>Course enum identity via {@code ==}.</li>
     *     </ol>
     *   </li>
     *   <li>If all four match, sets {@code isDuplicated = true} and terminates the loop.</li>
     * </ul>
     *
     * @param newStudent The student instance to validate against existing records.
     * @return {@code true} if an identical record exists; {@code false} otherwise.
     */
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

    /**
     * Adds a new student record to the system if it is not a duplicate.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Step 1: Validates uniqueness by calling {@link #isDuplicatedStudent(Student)}.</li>
     *   <li>Step 2: If unique, appends the record to {@code studentList} via {@code studentList.add(newStudent)}.</li>
     *   <li>Step 3: Returns the boolean outcome ({@code true} for success, {@code false} for duplicate).</li>
     * </ul>
     *
     * @param newStudent The student entity to add.
     * @return {@code true} if added successfully; {@code false} if a duplicate was detected.
     */
    public boolean addStudent(Student newStudent) {
        boolean isAdded = false;
        if (!isDuplicatedStudent(newStudent)) {
            isAdded = studentList.add(newStudent);
        }
        return isAdded;
    }

    /**
     * Removes a specific student enrollment record from the system.
     * <p>
     * Execution flow:
     * - Uses {@code remove(deleteStudent)} from {@link ArrayList} to find and remove the first matching instance.
     *
     * @param deleteStudent The student instance to be removed.
     * @return {@code true} if the record was found and removed; {@code false} otherwise.
     */
    public boolean deleteStudent(Student deleteStudent) {
        return studentList.remove(deleteStudent);
    }

    /**
     * Prints all student records to the console in a formatted table.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Checks whether {@code studentList} is empty using {@code isEmpty()}.</li>
     *   <li>If empty, prints "No students found.".</li>
     *   <li>If populated, prints the table header and iterates over the list,
     *       invoking {@code displayStudentWithOrder(i + 1)} to display a 1-based index with each record.</li>
     * </ul>
     */
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

    /**
     * Searches for student records whose names contain the specified keyword.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Initializes a new {@link ArrayList} named {@code foundStudentList}.</li>
     *   <li>Iterates through each student in {@code studentList}.</li>
     *   <li>Converts both the student name and keyword to lowercase via {@code toLowerCase()},
     *       then checks for containment using {@code contains()}.</li>
     *   <li>Collects all matching records and returns the result list.</li>
     * </ul>
     *
     * @param keyName The keyword or partial name to search for (case-insensitive).
     * @return A {@link List} of matching {@link Student} objects; empty if none match.
     */
    public List<Student> findStudentByName(String keyName) {
        List<Student> foundStudentList = new ArrayList<>();
        for (Student currentStudent : studentList) {
            if (currentStudent.getStudentName().toLowerCase().contains(keyName.toLowerCase())) {
                foundStudentList.add(currentStudent);
            }
        }
        return foundStudentList;
    }

    /**
     * Searches for all enrollment records associated with a specific student ID.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Initializes a new result list {@code foundStudentList}.</li>
     *   <li>Iterates through {@code studentList} comparing each record's ID using {@code equalsIgnoreCase(id)}.</li>
     *   <li>Collects all matching records for this ID (supporting multiple semester/course enrollments).</li>
     *   <li>Returns the filtered list of records.</li>
     * </ul>
     *
     * @param id The student ID to filter by.
     * @return A {@link List} of {@link Student} records belonging to this ID.
     */
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
     * Sorts the provided list of students in ascending alphabetical order by student name.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Applies {@link Collections#sort(List)}, which uses TimSort.</li>
     *   <li>The sorting order is determined by {@link Student#compareTo(Student)},
     *       which compares student names case-insensitively.</li>
     *   <li>Sorts the list in-place.</li>
     * </ul>
     *
     * @param studentListToSort The list of students to sort in-place.
     */
    public void sortStudentsByName(List<Student> studentListToSort) {
        Collections.sort(studentListToSort);
    }

    /**
     * Interactively updates fields (name, semester, course) of a selected student record via console input.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Step 1: Prompts for a new student name via {@link Validator#getString}. If provided,
     *       calls {@link #updateStudentById(String, String)} to synchronize the name across all records of that ID.</li>
     *   <li>Step 2: Prompts for a new semester via {@link Validator#getString}. If provided, updates the semester of this record.</li>
     *   <li>Step 3: Prompts confirmation (Y/N) to change course. If confirmed, invokes {@link CourseInput#courseInput()}
     *       to select and assign a new course to this record.</li>
     * </ul>
     *
     * @param updateStudent The student record selected for modification.
     */
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

    /**
     * Aggregates course enrollment statistics grouped by student name.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Step 1: Initializes a nested map: {@code Map<StudentName, Map<Course, TotalCount>>}.</li>
     *   <li>Step 2: Iterates through each student record in {@code studentList}.</li>
     *   <li>Step 3: Uses {@code computeIfAbsent}: checks if the student exists in the outer map;
     *       if not, instantiates a new inner {@link HashMap}.</li>
     *   <li>Step 4: Uses {@code merge}: increments the course count in the inner map using {@code Integer::sum},
     *       defaulting to 1 if the course is first recorded.</li>
     *   <li>Step 5: Returns the fully aggregated nested map.</li>
     * </ul>
     *
     * @return A nested {@link Map} of student names mapped to their enrolled courses and frequencies.
     */
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
