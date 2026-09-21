/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Candidate;
import entity.Experience;
import entity.Fresher;
import entity.Intern;
import java.util.Calendar;
import java.util.function.Predicate;
import utils.Validator;

/**
 * View class responsible for collecting and validating candidate input from the user.
 */
public class CandidateInputter {

    /**
     * Collects user input to create a candidate of the specified type, ensuring
     * valid data and unique ID.
     *
     * @param type The type of candidate (0 for Experience, 1 for Fresher, 2 for Intern).
     * @param isUniqueIdChecker A Predicate to check if an ID is unique without tightly coupling to the Model.
     * @return A new Candidate object of the specified type.
     */
    public Candidate inputCandidate(int type, Predicate<String> isUniqueIdChecker) {
        String id;
        while (true) {
            id = Validator.getString("Enter Candidate ID: ",
                    "Invalid ID format (letters and numbers only)",
                    "[A-Za-z0-9]+");
            if (isUniqueIdChecker == null || isUniqueIdChecker.test(id)) {
                break;
            }
            System.out.println("Candidate ID already exists! Please enter another ID.");
        }

        String firstName = Validator.getString("Enter First Name: ",
                "Invalid name (letters and spaces only)",
                "[A-Za-z\\s]+");
        String lastName = Validator.getString("Enter Last Name: ",
                "Invalid name (letters and spaces only)",
                "[A-Za-z\\s]+");

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int birthDate = Validator.getInt("Enter Birth Date (1900-" + currentYear + "): ",
                "Birth Date must be between 1900 and " + currentYear,
                "Invalid number",
                1900, currentYear);

        String address = Validator.getString("Enter Address: ",
                "Invalid address (letters, numbers, spaces, and punctuation only)",
                "[A-Za-z0-9\\s,./-]+");

        String phone = Validator.getString("Enter Phone (minimum 10 digits): ",
                "Phone must be at least 10 digits",
                "[0-9]{10,}");

        String email = Validator.getString("Enter Email (e.g., name@domain.com): ",
                "Invalid email format",
                "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

        switch (type) {
            case 0: // Experience
                int expInYear = Validator.getInt("Enter Years of Experience (0-100): ",
                        "Years must be between 0 and 100",
                        "Invalid number",
                        0, 100);
                String proSkill = Validator.getString("Enter Professional Skill: ",
                        "Invalid skill",
                        "[A-Za-z0-9\\s,+#.-]+");
                return new Experience(id, firstName, lastName, birthDate, address, phone, email, expInYear, proSkill);

            case 1: // Fresher
                int graduationYear = Validator.getInt("Enter Graduation Year (1950-" + currentYear + "): ",
                        "Year must be between 1950 and " + currentYear,
                        "Invalid year",
                        1950, currentYear);
                String graduationDate = String.valueOf(graduationYear);
                String graduationRank = Validator.getString("Enter Graduation Rank (Excellence/Good/Fair/Poor): ",
                        "Rank must be Excellence, Good, Fair, or Poor",
                        "(?i)Excellence|Good|Fair|Poor");
                graduationRank = normalizeRank(graduationRank);
                String education = Validator.getString("Enter Education (University): ",
                        "Invalid university name",
                        "[A-Za-z0-9\\s,.-]+");
                return new Fresher(id, firstName, lastName, birthDate, address, phone, email, graduationDate, graduationRank, education);

            case 2: // Intern
                String majors = Validator.getString("Enter Majors: ",
                        "Invalid majors",
                        "[A-Za-z0-9\\s,.-]+");
                String semester = Validator.getString("Enter Semester (e.g., Fall 2023): ",
                        "Invalid semester format (e.g., Fall 2023)",
                        "[A-Za-z0-9\\s]+");
                String universityName = Validator.getString("Enter University Name: ",
                        "Invalid university name",
                        "[A-Za-z0-9\\s,.-]+");
                return new Intern(id, firstName, lastName, birthDate, address, phone, email, majors, semester, universityName);

            default:
                throw new IllegalArgumentException("Invalid candidate type: " + type);
        }
    }

    /**
     * Prompts user for search keyword (first name or last name).
     *
     * @return Search keyword.
     */
    public String inputSearchName() {
        return Validator.getString("Input Candidate name (First name or Last name): ",
                "Invalid name",
                "[A-Za-z\\s]+");
    }

    /**
     * Prompts user for candidate type to search (0: Experience, 1: Fresher, 2: Intern).
     *
     * @return Integer candidate type.
     */
    public int inputSearchType() {
        return Validator.getInt("Input type of candidate: ",
                "Type must be 0, 1, or 2",
                "Invalid number",
                0, 2);
    }

    /**
     * Prompts the user whether they want to continue (Y/N).
     * Accepts both uppercase and lowercase 'y' and 'n'.
     *
     * @return true if user chooses Y/y, false if user chooses N/n.
     */
    public boolean confirmContinue() {
        String choice = Validator.getString("Do you want to continue (Y/N)? ",
                "Please enter Y or N!",
                "(?i)[yn]");
        return choice.equalsIgnoreCase("y");
    }

    private String normalizeRank(String rank) {
        if (rank.equalsIgnoreCase("excellence")) return "Excellence";
        if (rank.equalsIgnoreCase("good")) return "Good";
        if (rank.equalsIgnoreCase("fair")) return "Fair";
        if (rank.equalsIgnoreCase("poor")) return "Poor";
        return rank;
    }
}
