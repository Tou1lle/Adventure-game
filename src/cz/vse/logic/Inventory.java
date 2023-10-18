package cz.vse.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an inventory
 * The Inventory class manages a list of objectives, which represents the items stored in the inventory.
 * It has a default constructor that initializes the list of objectives as an empty ArrayList.
 *
 * @author Tuan Ha
 * @version 2023
 */
public class Inventory {

    private List<Objective> objectives;

    public Inventory() {
        objectives = new ArrayList<>();
    }

    /**
     * The addObjective method allows adding an objective to the inventory.
     *
     * @param objective - objective added to inventory
     */
    public void addObjective(Objective objective) {
        if (objectives.size() < 4) {
            objectives.add(objective);
        } else {
            System.out.println("Již je plno. Nelze přidat další věc do inventáře.");
        }
    }

    /**
     * method removes a specified objective from the inventory.
     *
     * @param objective - objective being removed from inventory
     */
    public void removeObjective(Objective objective) {
        objectives.remove(objective);
    }

    /**
     * method checks if the inventory contains an objective with a specific name.
     * It iterates over the objectives in the
     * inventory and compares each objective's name with the given nameOfObjective.
     * If a match is found, it returns true; otherwise, it returns false.
     *
     * @param nameOfObjective - a String of the objective
     * @return - boolean value
     */
    public boolean containsObjective(String nameOfObjective) {
        for (Objective objective : objectives) {
            if (objective.getName().equals(nameOfObjective)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method searches for an objective with a specific name in the inventory.
     * It iterates over the objectives in the inventory and compares each objective's
     * name with the given nameOfObjective.
     * If a match is found, it returns the objective; otherwise, it returns null.
     *
     * @param nameOfObjective - String of the objective name
     * @return - objective
     */
    public Objective findObjective(String nameOfObjective) {
        for (Objective objective : objectives) {
            if (objective.getName().equals(nameOfObjective)) {
                return objective;
            }
        }
        return null;
    }

    /**
     * method returns the list of objectives stored in the inventory.
     *
     * @return - list of objectives
     */
    public List<Objective> getObjectives() {
        return objectives;
    }

    /**
     * method generates a string representation of the inventory's contents.
     *
     * @return - String returned Text
     */
    public String inventoryContent() {
        String returnedText = "Inventář:";
        if (objectives.isEmpty()) {
            returnedText += " obsah inventáře je prázdný";
        }
        for (Objective objective : objectives) {
            returnedText += " " + objective.getName();
        }
        return returnedText;
    }
}
