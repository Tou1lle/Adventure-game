package cz.vse.logic;

/**
 * This class implements the interface ICommand
 *
 * @author Tuan Ha
 * @version 2023
 */
public class CommandGo implements ICommand {

    private static final String NAME = "jdi";
    private GamePlan plan;

    /**
     * The constructor initializes a CommandGo object with a reference to a GamePlan object.
     * It allows the command to access the game plan,
     * which contains information about the current room and its connections to other rooms.
     *
     * @param plan - used game plan
     */
    public CommandGo(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * method is called when the "jdi" command is executed in the game.
     * It takes the parameters entered by the player, which should be the name of a direction or an exit to move to.
     *
     * @param parameters - name of the room
     * @return - String message
     */
    @Override
    public String executeCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Nevím, kam mám jít. Musíš zadat: jdi <východ>.";
        }

        String direction = parameters[0];

        Room nextRoom = plan.getCurrentRoom().getNextRoom(direction);

        if (nextRoom == null) {
            return "Tam se odsud jít nedá:(";
        }
        else {
            if (nextRoom.isLocked()) {
                return "Dveře do místnosti " + nextRoom.getName() + " jsou zamčené.";
            }
            plan.setCurrentRoom(nextRoom);
            return nextRoom.extendedDescription();
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
