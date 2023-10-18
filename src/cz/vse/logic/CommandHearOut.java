package cz.vse.logic;

/**
 * This class implements interface ICommand
 * It handles the "vyslechnout" command in the game.
 *
 * @author Tuan Ha
 * @version 2023
 */
public class CommandHearOut implements ICommand{

    private static final String NAME = "vyslechni";
    private GamePlan gamePlan;

    /**
     * The constructor initializes a CommandHearOut object with a reference to a GamePlan object.
     * It allows the command to access the game plan,
     * which contains information about the suspects in the current room.
     *
     * @param plan - game plan
     */
    public CommandHearOut(GamePlan plan) {
        this.gamePlan = plan;
    }

    /**
     * method is called when the "vyslechnout" command is executed in the game.
     * It takes the parameters entered by the player, which should be the name of a suspect to be heard out.
     *
     * @param parameters - name of the suspect
     * @return - String description of a suspect
     */
    @Override
    public String executeCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Nevím, co koho mám vyslechnout. Musíš zadat vyslechnout <název podezřelého>.";
        }
        if (parameters.length > 1) {
            return "Můžes vyslechnout jen jednoho podezřelého najednou!";
        }
        String suspectName = parameters[0];
        Room currentRoom = gamePlan.getCurrentRoom();
        Suspect suspect = currentRoom.getSuspect(suspectName);

        if (suspect == null) {
            return "Podezřelí s názvem " + suspectName + " tu není.";
        }
        return suspect.getDescription();
    }

    /**
     * method returns the name of the command, which is "vyslechnout"
     * @return - String name of command
     */
    @Override
    public String getName() {
        return NAME;
    }
}
