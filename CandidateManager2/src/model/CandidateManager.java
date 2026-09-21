/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Candidate;
import entity.Experience;
import entity.Fresher;
import entity.Intern;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class managing the candidate collection and business logic.
 * Contains purely data manipulation and logic without any console I/O.
 */
public class CandidateManager {

    private final ArrayList<Candidate> candidates;

    /**
     * Initializes a new CandidateManager with an empty candidate list and
     * populates it with predefined sample data.
     */
    public CandidateManager() {
        candidates = new ArrayList<>();
        initializeCandidates();
    }

    /**
     * Populates the candidate list with sample candidates matching the specification.
     */
    private void initializeCandidates() {
        candidates.add(new Experience("CAN001", "Aelbrecht", "Stefan", 1985, "Hanoi", "0901234567", "stefan.aelbrecht@company.com", 5, "Java"));
        candidates.add(new Experience("CAN002", "Aguirre", "Eva", 1990, "Sao Paulo", "0940394123", "eva.aguirre@asante.com", 3, "Python"));
        candidates.add(new Experience("CAN003", "Ahlgren", "Maria", 1988, "HCMC", "0987654321", "maria.ahlgren@tech.com", 7, "C++"));
        candidates.add(new Experience("CAN004", "Antošová", "Adeleva", 1989, "Rio de Janeiro", "0984933123", "adeleva.anto@janeo.com", 4, "JavaScript"));
        candidates.add(new Fresher("CAN005", "Barbosa", "De Souza", 1995, "Hanoi", "0912345678", "souza.barbosa@edu.com", "2020", "Excellence", "FPT University"));
        candidates.add(new Fresher("CAN006", "Cabrera", "Cornide", 1996, "HCMC", "0923456789", "cornide.cabrera@edu.com", "2021", "Good", "Hanoi University"));
        candidates.add(new Fresher("CAN007", "Calderon", "Cuevas", 1994, "Danang", "0934567890", "cuevas.calderon@edu.com", "2019", "Fair", "VNU"));
        candidates.add(new Fresher("CAN008", "Casulari", "Motta", 1997, "Can Tho", "0945678901", "motta.casulari@edu.com", "2022", "Poor", "HUST"));
        candidates.add(new Intern("CAN009", "Maria", "Madeleine", 2000, "Hanoi", "0956789012", "madeleine.maria@intern.com", "IT", "Fall 2023", "FPT University"));
        candidates.add(new Intern("CAN010", "Csokán", "Babett", 2001, "HCMC", "0967890123", "babett.csokan@intern.com", "Business", "Spring 2024", "Hanoi University"));
        candidates.add(new Intern("CAN011", "Joana", "Filipa", 1999, "Danang", "0978901234", "filipa.joana@intern.com", "Marketing", "Fall 2023", "VNU"));
        candidates.add(new Intern("CAN012", "Patricia", "Carine", 2002, "Can Tho", "0989012345", "carine.patricia@intern.com", "Design", "Spring 2024", "HUST"));
    }

    /**
     * Adds a candidate to the collection if ID is unique.
     *
     * @param candidate The candidate to add.
     * @return true if added successfully, false otherwise.
     */
    public boolean addCandidate(Candidate candidate) {
        if (!isUniqueId(candidate.getCandidateId())) {
            return false;
        }
        return candidates.add(candidate);
    }

    /**
     * Checks whether the given candidate ID already exists.
     *
     * @param id The ID to check.
     * @return true if ID is unique (not taken), false otherwise.
     */
    public boolean isUniqueId(String id) {
        for (Candidate candidate : candidates) {
            if (candidate.getCandidateId().equalsIgnoreCase(id.trim())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the complete list of candidates.
     *
     * @return An ArrayList of candidates.
     */
    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    /**
     * Filters candidates by candidate type (0: Experience, 1: Fresher, 2: Intern).
     *
     * @param type The candidate type.
     * @return List of matching candidates.
     */
    public List<Candidate> getCandidatesByType(int type) {
        List<Candidate> result = new ArrayList<>();
        for (Candidate candidate : candidates) {
            if (candidate.getCandidateType() == type) {
                result.add(candidate);
            }
        }
        return result;
    }

    /**
     * Searches candidates by name (first name, last name or full name) and type.
     *
     * @param name The keyword to search.
     * @param type The candidate type (0, 1, or 2).
     * @return A list of candidates matching the criteria.
     */
    public List<Candidate> findCandidatesByNameAndType(String name, int type) {
        List<Candidate> foundCandidates = new ArrayList<>();
        String searchKeyword = name.toLowerCase().trim();
        for (Candidate candidate : candidates) {
            String fullName = (candidate.getFirstName() + " " + candidate.getLastName()).toLowerCase();
            boolean matchName = fullName.contains(searchKeyword)
                    || candidate.getFirstName().toLowerCase().contains(searchKeyword)
                    || candidate.getLastName().toLowerCase().contains(searchKeyword);
            if (matchName && candidate.getCandidateType() == type) {
                foundCandidates.add(candidate);
            }
        }
        return foundCandidates;
    }
}
