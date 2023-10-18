package cz.vse.logic;

/**
 * This class implements the interface ICommand
 * It handles the "polož" command in the game
 *
 * @author Tuan Ha
 * @version 2023
 */
public class CommandDrop implements ICommand{

    private static final String NAME = "polož";
    private GamePlan gamePlan;

    /**
     * The constructor initializes a CommandDrop object with a reference to a GamePlan object.
     * It allows the command to access the game plan and modify its state.
     *
     * @param gamePlan - game plan
     */
    public CommandDrop(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * method is called when the "polož" command is executed in the game.
     * It checks if any parameters are provided and returns an appropriate error message if they are.
     * If no parameters are provided, it returns an error message indicating that the player should specify
     * the name of the item to be dropped.
     *
     * @param parameters - name of the object
     * @return - String message
     */
    @Override
    public String executeCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Nevím co mám položit. Musíš zadat: polož <název věci>";
        }
        if (parameters.length > 1) {
            return "Můžes položit jen jednu věc najednou!";
        }

        String objectiveName = parameters[0];
        Inventory inventory = gamePlan.getInventory();
        Objective objective = inventory.findObjective(objectiveName);

        if (objective == null) {
            return "Věc s názvem " + objectiveName + " se nenachází v inventáři";
        }

        Room currentRoom = gamePlan.getCurrentRoom();
        currentRoom.addObjective(objective);
        inventory.removeObjective(objective);

        return "Položil jsi věc: " + objectiveName;
    }

    /**
     * method returns the name of the command, which is "polož"
     *
     * @return - String message name of command
     */
    @Override
    public String getName() {
        return NAME;
    }
}
