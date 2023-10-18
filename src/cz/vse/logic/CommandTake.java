package cz.vse.logic;

/**
 * This class implements the ICommand interface
 * It handles the action of taking an objective or item in the game
 *
 * @author Tuan Ha
 * @version 2023
 */
public class CommandTake implements ICommand{

    private static final String NAME = "seber";
    private final GamePlan gamePlan;

    /**
     * The constructor initializes a CommandTake object with a reference to the GamePlan object.
     * It allows the command to access and modify the game plan.
     *
     * @param gamePlan - game plan
     */
    public CommandTake(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * method is called when the "seber" command is executed in the game.
     * It takes the parameters entered by the player, which should be the name of the item to be taken.
     *
     * @param parameters - name of the object
     * @return - String message for player
     */
    @Override
    public String executeCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Nevím co mám sebrat, musíš zadat: seber <sbíraná věc>";
        }
        else if (parameters.length > 1) {
            return "Můžes sebrat jen jednu věc najednou!";
        }

        String nameOfObjective = parameters[0];
        Objective takenObjective = gamePlan.getCurrentRoom().getObjective(nameOfObjective);
        if (takenObjective == null) {
            return"Věc s názvem " + nameOfObjective + " se v prostoru nenachází";
        }
        else if (!takenObjective.isCollectible()) {
            return "Věc s názvem " + nameOfObjective + " není možné sebrat";
        }
        Inventory inventory = gamePlan.getInventory();
        inventory.addObjective(takenObjective);
        gamePlan.getCurrentRoom().removeObjective(nameOfObjective);
        return "Věc " + nameOfObjective + " byla sebraná";
    }

    /**
     * method returns the name of the command, which is "seber"
     *
     * @return - String name of command
     */
    @Override
    public String getName() {
        return NAME;
    }
}
