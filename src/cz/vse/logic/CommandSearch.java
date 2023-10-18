package cz.vse.logic;

/**
 * CommandSearch implements interface ICommand
 * It handles the action of searching or examining an object in the game
 *
 * @author Tuan Ha
 * @version 2023
 */

public class CommandSearch implements ICommand{

    private static final String NAME = "prozkoumat";
    private GamePlan gamePlan;

    /**
     * The constructor initializes a CommandSearch object with a reference to the GamePlan object.
     * It allows the command to access and modify the game plan.
     *
     * @param plan - game plan, where the player can search objects
     */
    public CommandSearch(GamePlan plan) {
        this.gamePlan = plan;
    }

    /**
     * method is called when the "prozkoumat" command is executed in the game.
     * It takes the parameters entered by the player, which should be the name of the object to be searched.
     *
     * @param parameters - name of the object
     * @return - String description
     */
    @Override
    public String executeCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Nevím, co mám prozkoumat. Musíš zadat prozkoumat <název věci>.";
        }
        if (parameters.length > 1) {
            return "Můžes prozkoumat jen jednu věc najednou!";
        }
        String objectiveName = parameters[0];
        Room currentRoom = gamePlan.getCurrentRoom();
        Objective objective = currentRoom.getObjective(objectiveName);

        if (objective == null) {
            //return "Věc s názvem " + objectiveName + " se tu nenachází";
            Inventory inventory = gamePlan.getInventory();
            objective = inventory.findObjective(objectiveName);
            if (objective == null) {
                return "Věc s názvem " + objectiveName + " se nenachází v místnosti a ani v inventáři";
            }
        }
        return objective.getDescription();
    }

    /**
     * method returns the name of the command, which is "prozkoumat"
     *
     * @return - String name of command
     */
    @Override
    public String getName() {
        return NAME;
    }
}
