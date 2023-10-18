package cz.vse.logic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class Room represents a room
 *
 * @author Tuan Ha
 * @version 2023
 */

public class Room {

    private String name;

    private String description;

    private Set<Room> exits;

    private final Map<String, Objective> objectives;

    private Safe safe;

    private final Map<String,Suspect> suspects;

    /**
     * Represents the shared inventory object that is common to all rooms
     * an inventory is set in GamePlan
     */
    private final Inventory sharedInventory;

    /**
     * Indicates whether the room is locked or not
     */
    private boolean locked;

    /**
     *  Represents the objective that serves as the key to unlock the room
     */
    private Objective key;

    /**
     * Initializes the Room object with a name, description, and shared
     * inventory. It sets the initial values for other variables like exits, objectives,
     * locked, and suspects
     *
     * @param name - name of room
     * @param description - a short description
     * @param sharedInventory - shared inventory across rooms
     */
    public Room(String name, String description, Inventory sharedInventory) {
        this.name = name;
        this.description = description;
        exits = new HashSet<>();
        objectives = new HashMap<>();
        this.sharedInventory = sharedInventory;
        locked = false;
        suspects = new HashMap<>();
    }

    /**
     * Adds an exit to the current room by specifying the next room to which it leads
     *
     * @param next - a room that is set as an exit to the current Room
     */
    public void setExit(Room next) {
        exits.add(next);
    }

    /**
     * Retrieves the next room based on the name of the exit provided
     *
     * @param nameNextDoor - (String): Represents the name of the exit or door
     *                       through which the next room is to be obtained.
     * @return - Room: Returns the Room object that corresponds
     *                 to the exit or door with the specified nameNextDoor.
     *                 If a matching room is found, it is returned. If no matching
     *                 room is found, it returns null
     */
    public Room getNextRoom(String nameNextDoor) {
        List<Room>nextRooms =
                exits.stream()
                        .filter(next -> next.getName().equals(nameNextDoor))
                        .collect(Collectors.toList());
        if(nextRooms.isEmpty()){
            return null;
        }
        else {
            return nextRooms.get(0);
        }
    }

    /**
     * Returns an unmodifiable collection of all the exits from the room
     *
     * @return - Collection of rooms
     */
    public Collection<Room> getExits() {
        return Collections.unmodifiableCollection(exits);
    }

    /**
     * Adds an objective or item to the room by providing an Objective object
     *
     * @param objective - objective that is added to the room
     */
    public void addObjective(Objective objective) {
        objectives.put(objective.getName(), objective);
    }

    /**
     * Retrieves the Objective object from the room's objectives using its name as a parameter
     *
     * @param nameObj -  name of the objective
     * @return - Objective
     */
    public Objective getObjective(String nameObj) {
        return objectives.get(nameObj);
    }

    /**
     * Removes and returns the Objective object from the room's objectives using its name as a parameter
     *
     * @param nameObj - name of the Objective
     * @return - Objective
     */
    public Objective removeObjective(String nameObj) {
        return objectives.remove(nameObj);
    }

    /**
     * Checks if the room is locked.
     *
     * @return - boolean value (true/false)
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets the locked status of the room
     *
     * @param locked - we set the locked room to either true or false
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Sets the objective that serves as the key to unlock the room
     * Each room has its unique key
     * Once the players uses the key to open a door, the key is removed and door opened
     *
     * @param key - key objective
     */
    public void setKey(Objective key) {
        this.key = key;
    }

    /**
     * Returns the objective that serves as the key to unlock the room
     *
     * @return - objective key
     */
    public Objective getKey() {
        return key;
    }

    /**
     * Sets the safe object in the room
     *
     * @param safe - safe
     */
    public void setSafe(Safe safe) {
        this.safe = safe;
    }

    /**
     * Returns the safe object present in the room
     *
     * @return
     */
    public Safe getSafe() {
        return safe;
    }

    /**
     * Adds a suspect to the room by providing a Suspect object
     * in this game suspects will be added just to garden
     *
     * @param suspect - suspect
     */
    public void addSuspect(Suspect suspect) {
        suspects.put(suspect.getName(), suspect);
    }

    /**
     * Retrieves the Suspect object from the room's suspects using the suspect's name as a parameter
     *
     * @param nameSus - name of the suspect
     * @return - Suspect
     */
    public Suspect getSuspect(String nameSus) {
        return suspects.get(nameSus);
    }

    /**
     * Returns the shared inventory object.
     *
     * @return - shared inventory
     */
    public Inventory getInventory() {
        return sharedInventory;
    }

    /**
     * Returns the name of the room
     *
     * @return - String name
     */
    public String getName() {
        return name;
    }

    /**
     * Overrides the equals method to compare two Room objects based on their names
     *
     * @param o - object that is being compared with the current one
     * @return - boolean value
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }

        Room second = (Room) o;

        return (java.util.Objects.equals(this.name, second.name));
    }

    /**
     * Overrides the hashCode method to generate a unique hash code
     * for the Room object based on its name
     *
     * @return - int value
     */
    @Override
    public int hashCode() {
        int result = 1;
        int hashOfName = java.util.Objects.hashCode(this.name);
        result = 17 * result + hashOfName;
        return result;
    }

    /**
     * Generates an extended description of the room,
     * including its name, description, exits, objectives, inventory, safe, and suspects
     *
     * @return - String description
     */
    public String extendedDescription() {
        return "\n" + "Nyní se nacházíš v prostoru/místnosti " + description + "\n" +
                "\n"
                + exitDescription()
                +"\n"
                + objectiveDescription()
                +"\n"
                + inventoryDescription()
                +"\n"
                + safeDescription()
                + "\n"
                + suspectDescription();

    }

    /**
     * Generates a description of the room's exits, listing their names separated by commas
     *
     * @return - String description of exits
     */
    private String exitDescription() {
        String returnedText = "Východy:";
        int count = 0;
        for (Room next : exits) {
            count++;
            if (count == exits.size()) {
                returnedText += " " + next.getName();
            } else {
                returnedText += " " + next.getName() + ",";
            }
        }
        return returnedText;
    }

    /**
     * Generates a description of the room's objectives, listing their names
     *
     * @return - String description of objectives
     */
    private String objectiveDescription() {
        String returnedText = "Věci:";
        if (objectives.isEmpty()){
            return returnedText += " tady se nic nenachází";
        }
        else {
            for (String nameObj : objectives.keySet()) {
                returnedText += " " + nameObj;
            }
        }
        return returnedText;
    }

    /**
     * Generates a description of the shared inventory's contents
     * its described in class Inventory
     *
     * @return - String description of inventory
     */
    private String inventoryDescription() {
        return sharedInventory.inventoryContent();
    }

    /**
     * Generates a description of whether the room contains a safe or not
     *
     * @return - String description of whether the safe is in the room or not
     */
    private String safeDescription() {
        if (this.safe == null) {
            return "Trezor: Ne";
        }
        else {
            return "Trezor: Ano";
        }
    }

    /**
     * Generates a description of the suspects present in the room
     *
     * @return - String description of suspects
     */
    private String suspectDescription() {
        String returnedText = "Podezřelí:";
        if (suspects.isEmpty()){
            return returnedText += " v místnosti žádní podezřelí nejsou. (Jsou na venkově)";
        }
        else {
            for (String nameSus: suspects.keySet()) {
                returnedText += " " + nameSus;
            }
        }
        return returnedText;
    }
}
