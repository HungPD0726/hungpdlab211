package controller;

import entity.Course;
import entity.Student;
import model.StudentManager;
import java.util.List;
import java.util.Map;
import ui.StudentInput;
import utils.Validator;

/**
 * Controller class that bridges user input (UI) and the data layer (Model).
 * Coordinates key application flows: creating students, searching & sorting,
 * updating/deleting records, and generating statistical reports.
 *
 * @author HP
 */
public class StudentController {

    private final StudentManager studentManager = new StudentManager();
    private final StudentInput studentInput = new StudentInput();

    /**
     * Orchestrates the creation of new student records via console interaction.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Runs an infinite loop {@code while (true)} allowing consecutive student additions.</li>
     *   <li>Step 1: Prompts user to input student attributes (ID, Name, Semester, Course)
     *       via {@code studentInput.inputStudent(studentManager)}.</li>
     *   <li>Step 2: Invokes {@code studentManager.addStudent(newStudent)}:
     *     - If duplicate: Prints "Student already exists!" and restarts loop (continue).
     *     - If successful: Prints "Create successfully.".</li>
     *   <li>Step 3: When total students reach 10 or more (LAB211 requirement),
     *       prompts user to continue (Y/N). If 'N', breaks the loop.</li>
     * </ul>
     */
    public void createStudent() {
        while (true) {
            Student newStudent = studentInput.inputStudent(studentManager);
            if (!studentManager.addStudent(newStudent)) {
                System.out.println("Student already exists!");
                continue;
            }
            System.out.println("Create successfully.");

            if (studentManager.getTotalStudent() >= 10) {
                String choice = Validator.getString(
                        "Do you want to continue (Y/N)? ",
                        "Please choose Y or N.",
                        "[yYnN]");
                if (choice.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("Current total students: " + studentManager.getTotalStudent());
                }
            }
        }
    }

    /**
     * Searches for students by name and displays results sorted in ascending alphabetical order (A-Z).
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Step 1: Prompts user to enter a name or search keyword via Validator.</li>
     *   <li>Step 2: Calls {@code studentManager.findStudentByName(keyWord)} to retrieve matching records.</li>
     *   <li>Step 3: If no matches are found, prints notification and exits early.</li>
     *   <li>Step 4: If matches exist, sorts them via {@code studentManager.sortStudentsByName(findList)}.</li>
     *   <li>Step 5: Displays the sorted records in a formatted console table.</li>
     * </ul>
     */
    public void findAndSortStudents() {
        String keyWord = Validator.getString("Enter name: ", "Invalid name format.", "[A-Za-z\\s]+");
        List<Student> findList = studentManager.findStudentByName(keyWord);
        if (findList.isEmpty()) {
            System.out.println("Cannot find student with name: " + keyWord);
            return;
        }
        studentManager.sortStudentsByName(findList);
        System.out.println("Found students:");
        System.out.printf("| %-18s | %-12s | %-13s|%n", "Student Name", "Semester", "Course Name");
        for (Student currentStudent : findList) {
            currentStudent.displayStudentInfor();
        }
    }

    /**
     * Searches for students by ID and allows the user to either Update (U) or Delete (D) a selected record.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Step 1: Prompts for a student ID.</li>
     *   <li>Step 2: Fetches all records associated with that ID via {@code studentManager.findStudentById(id)}.</li>
     *   <li>Step 3: If not found, prints notification and exits early.</li>
     *   <li>Step 4: Displays found records indexed from 1 to N.</li>
     *   <li>Step 5: Prompts user to select a record index (1 to size).</li>
     *   <li>Step 6: Prompts user to choose Update (U) or Delete (D):
     *     - If 'U': Calls {@code studentManager.updateStudentInformation(selectStudent)}.
     *     - If 'D': Calls {@code studentManager.deleteStudent(selectStudent)}.</li>
     * </ul>
     */
    public void updateOrDeleteStudent() {
        String searchStudentId = Validator.getString(
                "Enter Student ID (e.g., he187004): ",
                "Invalid ID format (alphanumeric only).",
                "[a-zA-Z0-9]+");
        List<Student> findList = studentManager.findStudentById(searchStudentId);
        if (findList.isEmpty()) {
            System.out.println("Cannot find student with ID: " + searchStudentId);
            return;
        }
        System.out.println("Found students:");
        System.out.printf("| %-3s |%-18s | %-12s | %-13s|%n", "No.", "Student Name", "Semester", "Course Name");
        for (int i = 0; i < findList.size(); i++) {
            findList.get(i).displayStudentWithOrder(i + 1);
        }
        int selectRecordIndex = Validator.getInt("Select record (1 - " + findList.size() + "): ",
                "Please choose from 1 to " + findList.size(), "Invalid integer input.", 1, findList.size());
        Student selectStudent = findList.get(selectRecordIndex - 1);
        String choice = Validator.getString("Do you want to Update (U) or Delete (D) student? ",
                "Please enter U or D.",
                "[uUdD]");
        if (choice.equalsIgnoreCase("U")) {
            studentManager.updateStudentInformation(selectStudent);
            System.out.println("Update successfully.");
        } else if (choice.equalsIgnoreCase("D")) {
            studentManager.deleteStudent(selectStudent);
            System.out.println("Delete successfully.");
        }
    }

    /**
     * Displays a summarized course enrollment report for each student.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Step 1: Retrieves aggregated enrollment data from {@link StudentManager#generrateCourseData()}
     *       as a nested map: {@code Map<StudentName, Map<Course, TotalCount>>}.</li>
     *   <li>Step 2: Validates whether the report map is empty; prints a notice and terminates early if so.</li>
     *   <li>Step 3: Prints the formatted report table header.</li>
     *   <li>Step 4: Iterates over the nested map using functional {@code forEach}:
     *       outer iteration traverses each student, and inner iteration traverses each course and its count.</li>
     *   <li>Step 5: Formats each row with left-aligned columns via {@link java.io.PrintStream#printf}.</li>
     * </ul>
     */
    public void displayCourseReport() {
        // Step 1: Retrieve aggregated course enrollment data from Model
        Map<String, Map<Course, Integer>> courseReportMap = studentManager.generrateCourseData();
        
        // Step 2: Validate if there is any data to report
        if (courseReportMap.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }

        // Step 3: Print report table header
        System.out.println("Report:");
        System.out.println("| Student Name       | Course       | Total of Course |");
        
        // Step 4: Traverse nested map and print each student's enrollment count per course
        courseReportMap.forEach((studentName, courses)
                -> courses.forEach((course, total)
                        -> System.out.printf("| %-18s | %-12s | %-15d |%n",
                                studentName,
                                course.getLanguage(),
                                total)
                )
        );
    }
}
