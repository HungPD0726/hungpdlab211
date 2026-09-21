/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;

/**
 * Entry point of the Candidate Management application.
 */
public class Main {

    /**
     * Main method running the menu loop.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        CandidateView view = new CandidateView();

        while (true) {
            int choice = view.getMenuChoice();
            switch (choice) {
                case 1:
                    controller.createExperienceCandidate();
                    break;
                case 2:
                    controller.createFresherCandidate();
                    break;
                case 3:
                    controller.createInternCandidate();
                    break;
                case 4:
                    controller.searchCandidate();
                    break;
                case 5:
                    view.showMessage("Exit successfully.");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
