/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Candidate;
import java.util.List;
import utils.Validator;

/**
 * View class responsible for presenting menus, candidate information,
 * search results, and notifications to the console according to the assignment format.
 */
public class CandidateView {

    /**
     * Displays the main menu exactly as specified in the assignment requirements
     * and prompts the user for their choice (1-5).
     *
     * @return User's integer choice between 1 and 5.
     */
    public int getMenuChoice() {
        String menu = "CANDIDATE MANAGEMENT SYSTEM\n"
                + "1. Experience\n"
                + "2. Fresher\n"
                + "3. Internship\n"
                + "4. Searching\n"
                + "5. Exit\n"
                + "(Please choose 1 to Create Experience Candidate, 2 to Create Fresher Candidate, 3 to Internship Candidate, 4 to Searching and 5 to Exit program).\n"
                + "Your option: ";
        return Validator.getInt(menu, "Please choose from 1 to 5!", "Invalid number format!", 1, 5);
    }

    /**
     * Displays all candidates grouped into Experience, Fresher, and Intern categories.
     *
     * @param candidates The list of all candidates.
     */
    public void displayCandidateList(List<Candidate> candidates) {
        if (candidates == null || candidates.isEmpty()) {
            System.out.println("No candidates available.");
            return;
        }

        System.out.println("List of candidate:");
        System.out.println("===========EXPERIENCE CANDIDATE============");
        for (Candidate candidate : candidates) {
            if (candidate.getCandidateType() == 0) {
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName());
            }
        }

        System.out.println("==========FRESHER CANDIDATE==============");
        for (Candidate candidate : candidates) {
            if (candidate.getCandidateType() == 1) {
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName());
            }
        }

        System.out.println("===========INTERN CANDIDATE==============");
        for (Candidate candidate : candidates) {
            if (candidate.getCandidateType() == 2) {
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName());
            }
        }
    }

    /**
     * Displays found candidates following the exact assignment output format:
     * Candidate name | Birth Date | Address | Phone | Email | Candidate type
     *
     * @param foundCandidates The list of matched candidates.
     */
    public void displayFoundCandidates(List<Candidate> foundCandidates) {
        if (foundCandidates == null || foundCandidates.isEmpty()) {
            System.out.println("No candidates found.");
            return;
        }
        System.out.println("The candidates found:");
        for (Candidate candidate : foundCandidates) {
            System.out.println(candidate.toString());
        }
    }

    /**
     * Prints a general message or feedback to the console.
     *
     * @param message The message text.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
