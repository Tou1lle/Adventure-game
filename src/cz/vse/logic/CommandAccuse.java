package cz.vse.logic;

/**
 * This class implements the interface ICommand
 * It handles the "obvinit" command in the game
 *
 * @author Tuan Ha
 * @version 2023
 */
public class CommandAccuse implements ICommand{

    private static final String NAME = "obvinit";
    private GamePlan gamePlan;

    private Game game;

    /**
     * The constructor initializes a CommandAccuse, it can access the game plan for suspects and game
     * for ending the game
     *
     * @param gamePlan - this game plan, where the player is
     * @param game - this game, that the player is playing
     */
    public CommandAccuse(GamePlan gamePlan, Game game) {
        this.gamePlan = gamePlan;
        this.game = game;
    }

    @Override
    public String executeCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Musíš zadat název podezřelého, kterého chceš obvinit. Zadej obvinit <podezřelý>";
        }
        if (parameters.length > 1) {
            return "Můžes obvinit jen jednoho podezřelého!";
        }

        String suspectName = parameters[0];
        Room room = gamePlan.getCurrentRoom();
        Suspect suspect = room.getSuspect(suspectName);

        if (suspect == null) {
            return "Podezřelý/á " + suspectName + " se tu nenachází";
        }
        if (suspect.isGuilty() && gamePlan.getInventory().getObjectives().containsAll(suspect.getProofs())) {
            game.setEndGame(true);
            return "Obvinil jsi správného podezřelého! Jsi dobrý detektiv!!!";
        }
        else {
            game.setEndGame(true);
            return "Bohužel se ti nepovedlo vyřešit případ správně:(\n" +
                    "Buď jsi neměl dostatek důkazů nebo jsi obvinil špatného člověka.";
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
