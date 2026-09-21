/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Candidate;
import java.util.ArrayList;
import java.util.List;
import model.CandidateManager;
import ui.CandidateInputter;
import ui.CandidateView;

/**
 * Controller class coordinating interactions between Model and View.
 * Follows the MVC pattern strictly: no direct console printing or direct Scanner logic.
 */
public class Controller {

    private final CandidateManager candidateManager;
    private final CandidateInputter candidateInputter;
    private final CandidateView candidateView;

    /**
     * Initializes a new Controller instance.
     */
    public Controller() {
        this.candidateManager = new CandidateManager();
        this.candidateInputter = new CandidateInputter();
        this.candidateView = new CandidateView();
    }

    /**
     * Starts creation process for Experience candidate (type 0).
     */
    public void createExperienceCandidate() {
        createCandidate(0);
    }

    /**
     * Starts creation process for Fresher candidate (type 1).
     */
    public void createFresherCandidate() {
        createCandidate(1);
    }

    /**
     * Starts creation process for Intern candidate (type 2).
     */
    public void createInternCandidate() {
        createCandidate(2);
    }

    /**
     * Handles candidate creation loop until user enters 'N' or 'n'.
     *
     * @param type 0 for Experience, 1 for Fresher, 2 for Intern.
     */
    private void createCandidate(int type) {
        while (true) {
            Candidate candidate = candidateInputter.inputCandidate(type, candidateManager::isUniqueId);
            if (candidateManager.addCandidate(candidate)) {
                candidateView.showMessage("Candidate added successfully!");
            } else {
                candidateView.showMessage("Failed to add candidate (ID may already exist)!");
                continue;
            }

            boolean continueAdd = candidateInputter.confirmContinue();
            if (!continueAdd) {
                candidateView.displayCandidateList(candidateManager.getCandidates());
                break;
            }
        }
    }

    /**
     * Coordinates the search process: displays all candidates first,
     * gathers search criteria, calls Model to search, and passes results to View.
     */
    public void searchCandidate() {
        if (candidateManager.getCandidates().isEmpty()) {
            candidateView.showMessage("No candidates available.");
            return;
        }

        // Display list of candidates first as required by assignment specification
        candidateView.displayCandidateList(candidateManager.getCandidates());

        String name = candidateInputter.inputSearchName();
        int type = candidateInputter.inputSearchType();

        List<Candidate> foundCandidates = candidateManager.findCandidatesByNameAndType(name, type);
        candidateView.displayFoundCandidates(foundCandidates);
    }

    /**
     * Returns the candidate list from the Model.
     *
     * @return List of candidates.
     */
    public ArrayList<Candidate> getCandidates() {
        return candidateManager.getCandidates();
    }
}
