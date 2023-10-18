package cz.vse.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Safe
 *
 * @author Tuan Ha
 * @version 2023
 */
public class Safe {

    private List<Objective> objectives;

    private final int capacity;

    private final String password;

    private boolean locked = true;

    private final Room room;

    /**
     * Creates a safe object with the specified capacity, password, objectives, and room.
     * It sets the given objectives to the safe, associates the safe with the room by calling
     * room.setSafe(this), and initializes the locked state to true.
     *
     * @param capacity - capacity of the safe
     * @param password - password to the safe (String)
     * @param objectives - List of objectives located in the safe
     * @param room - associated room to safe
     */
    public Safe(int capacity, String password, List<Objective> objectives, Room room) {
        this.objectives = objectives;
        this.capacity = capacity;
        this.password = password;
        this.room = room;

        //adding this safe to the defined room
        room.setSafe(this);
    }

    public Safe(int capacity, String password, Room room) {
        this.objectives = new ArrayList<>();
        this.capacity = capacity;
        this.password = password;
        this.room = room;

        room.setSafe(this);
    }

    /**
     * Unlock safe
     * Attempts to unlock the safe by comparing the provided password with the stored password.
     *
     * @param password - provided password
     * @return boolean
     */
    public boolean tryUnlock(String password) {
        if (this.password.equals(password)) {
            this.locked = false;
            return true;
        }

        return false;
    }

    /**
     * Get objectives from safe
     * Returns a list of objectives stored in the safe if the safe is unlocked.
     * If the safe is locked, it returns an empty list.
     *
     * @return ArrayList<Objective>
     */
    public List<Objective> getObjectives() {
        if (this.locked) {
            return new ArrayList<>();
        }
        return this.objectives;
    }

    /**
     * Check if safe is locked, used for Tests
     *
     * @return boolean
     */
    public boolean isLocked() {
        return this.locked;
    }

}
