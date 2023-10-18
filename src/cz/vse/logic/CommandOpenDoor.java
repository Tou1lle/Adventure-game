package cz.vse.logic;

/**
 * This class implements the interface ICommand
 * It handles the action of opening a locked door in the game
 *
 * @author Tuan Ha
 * @version 2023
 */
public class CommandOpenDoor implements ICommand {
    private static final String NAME = "odemkni";
    private GamePlan gamePlan;

    /**
     * The constructor initializes a CommandOpenDoor object with a reference to the GamePlan object.
     * It allows the command to access and modify the game plan.
     *
     * @param gamePlan - used game plan
     */
    public CommandOpenDoor(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * method is called when the "odemkni" command (Czech for "unlock") is executed in the game.
     * It takes the parameters entered by the player, which should be the name of the locked door to unlock.
     *
     * @param parameters - name of the locked door
     * @return - String message, if success, door is set to unlocked
     */
    @Override
    public String executeCommand(String... parameters) {
        if (parameters.length < 1) {
            return "Nevím, co mám odemknout. Musíš zadat: odemkni <zamčený východ>.";
        }

        String roomToOpen = parameters[0];
        Room currentRoom = gamePlan.getCurrentRoom();
        Room targetRoom = currentRoom.getNextRoom(roomToOpen);

        if (targetRoom == null) {
            return "Místnost '" + roomToOpen + "' není sousedem aktuální místnosti.";
        }

        if (!targetRoom.isLocked()) {
            return "Místnost '" + roomToOpen + "' již je odemčená.";
        }

        Inventory inventory = gamePlan.getInventory();
        Objective key = targetRoom.getKey();
        if (key == null || !inventory.containsObjective(key.getName())) {
            return "Nemáš potřebný klíč k odemknutí místnosti '" + roomToOpen + "'.";
        }

        targetRoom.setLocked(false);
        inventory.removeObjective(key);
        return "Místnost '" + roomToOpen + "' byla úspěšně odemčena.";
    }

    /**
     * method returns the name of the command, which is "odemkni"
     *
     * @return - name of the command
     */
    @Override
    public String getName() {
        return NAME;
    }
}
