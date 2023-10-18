package cz.vse.logic;

/**
 * This class represents individual object in a room
 * Each object has its name and description and whether it is collectible
 *
 * @author Tuan Ha
 * @version 2023
 */

public class Objective {

    private final String name;
    private final boolean isCollectible;
    private final String description;

    /**
     * The constructor Objective is used to create a new objective instance
     *
     * @param name -the name of the objective
     * @param isCollectible - a boolean indicating if the objective can be collected
     * @param description - a string describing the objective
     */
    public Objective(String name, boolean isCollectible, String description) {
        this.name = name;
        this.isCollectible = isCollectible;
        this.description = description;
    }

    /**
     * Getter for objectives, returns the name of the objective as a String
     *
     * @return - name
     */
    public String getName() {
        return name;
    }

    /**
     * returns a boolean indicating whether the objective is collectible or not
     *
     * @return - boolean value
     */
    public boolean isCollectible() {
        return isCollectible;
    }

    /**
     * returns the description of the objective as a string
     *
     * @return - description
     */
    public String getDescription() {
        return description;
    }
}
