package cz.vse.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a suspect in the game
 * class represents a suspect with a name, guilty status, a list of proofs, and a description.
 *
 * @author Tuan Ha
 * @version 2023
 */
public class Suspect {

    private String name;
    private boolean isGuilty;
    private List<Objective> proofs;
    private String description;

    /**
     * create a new Suspect object. It takes the suspect's name, guilty status,
     * and description as parameters
     *
     * @param name - name of the suspect
     * @param isGuilty - guilty status
     * @param description - description of the suspect
     */
    public Suspect(String name, boolean isGuilty, String description) {
        this.name = name;
        this.isGuilty = isGuilty;
        this.description = description;
        this.proofs = new ArrayList<>();
    }

    /**
     * method returns the name of the suspect.
     *
     * @return - name of the suspect
     */
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * method returns a boolean value indicating whether the suspect is guilty.
     *
     * @return - boolean value
     */
    public boolean isGuilty() {
        return isGuilty;
    }

    /**
     * method is used to add a proof represented by an Objective object to the suspect's list of proofs.
     *
     * @param objective - objective being added to the suspect
     */
    public void addProof(Objective objective) {
       proofs.add(objective);
    }

    public List<Objective> getProofs() {
        return proofs;
    }


}
