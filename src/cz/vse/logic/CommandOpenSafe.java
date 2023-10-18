package cz.vse.logic;

import java.util.List;

/**
 * This class implements the interface ICommand
 * It handles the action of opening a safe in the game
 *
 * @author Tuan Ha
 * @version 2023
 */
public class CommandOpenSafe implements ICommand {
    private static final String NAME = "otevři";

    private GamePlan plan;

    /**
     * The constructor initializes a CommandOpenSafe object with a reference to the GamePlan object.
     * It allows the command to access and modify the game plan.
     *
     * @param plan - game plan
     */
    public CommandOpenSafe(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * method is called when the "otevři" command is executed in the game.
     * It takes the parameters entered by the player, which should be the password for the safe.
     *
     * @param parameters - password for the safe
     * @return - String message, if success - set the safe to null
     */
    @Override
    public String executeCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Musíš zadat heslo k trezoru. Zadej: otevři <heslo>";
        }

        String password = parameters[0];
        Room currentRoom = plan.getCurrentRoom();

        if (currentRoom.getSafe() == null)
            return "Trezor tu není";

        if (currentRoom.getSafe().tryUnlock(password)) {

            List<Objective> safeObjectives = currentRoom.getSafe().getObjectives();

            for (Objective objective : safeObjectives) {
                plan.getCurrentRoom().addObjective(objective);
            }

            currentRoom.setSafe(null);
            return "Podařilo se ti otevřít safe, našel jsi nějaké předměty";
        }

        return "Zadal jsi špatné heslo!";
    }

    /**
     * method returns the name of the command, which is "otevři"
     *
     * @return - String name of command
     */
    @Override
    public String getName() {
        return NAME;
    }

}
