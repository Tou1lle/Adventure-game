package cz.vse.logic;

/**
 * This class implements the interface ICommand
 * It handles the "konec" command in the game.
 *
 * @author Tuan Ha
 * @version 2023
 */
public class CommandEnd implements ICommand{

    private static final String NAME = "konec";

    private Game game;

    /**
     * The constructor initializes a CommandEnd object with a reference to a Game object.
     * It allows the command to access the game and modify its state.
     *
     * @param game - game
     */
    public CommandEnd(Game game) {
        this.game = game;
    }

    /**
     * method is called when the "konec" command is executed in the game.
     *
     * @param parameters - no parameters needed
     * @return - ending the game and returns a message
     */
    @Override
    public String executeCommand(String... parameters) {
        if (parameters.length > 0) {
            return "Pro ukončení hry stačí napsat jen \"konec\"";
        }
        else {
            game.setEndGame(true);
            return "Hra ukončena bez obvinění podezřelého.";
        }
    }

    /**
     * method returns the name of the command, which is "konec"
     *
     * @return - name of the command
     */
    @Override
    public String getName() {
        return NAME;
    }
}
