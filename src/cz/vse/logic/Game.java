package cz.vse.logic;

/**
 * Game implements the IGame interface.
 * It represents the game logic and controls the flow of the game
 * class implements the IGame interface, which defines the basic operations for a game.
 *
 * @author Tuan Ha
 * @version 2023
 */
public class Game implements IGame{

    private ListOfCommands validCommands;
    private GamePlan gamePlan;
    private boolean endGame = false;

    /**
     * The constructor Game() is used to create a new Game object.
     * It initializes the gamePlan object, which represents the game plan,
     * and the validCommands object, which represents the list of valid commands in the game.
     */
    public Game() {
        gamePlan = new GamePlan();
        validCommands = new ListOfCommands();
        validCommands.insertCommand(new CommandHelp(validCommands));
        validCommands.insertCommand(new CommandEnd(this));
        validCommands.insertCommand(new CommandGo(gamePlan));
        validCommands.insertCommand(new CommandSearch(gamePlan));
        validCommands.insertCommand(new CommandTake(gamePlan));
        validCommands.insertCommand(new CommandDrop(gamePlan));
        validCommands.insertCommand(new CommandOpenDoor(gamePlan));
        validCommands.insertCommand(new CommandOpenSafe(gamePlan));
        validCommands.insertCommand(new CommandHearOut(gamePlan));
        validCommands.insertCommand(new CommandAccuse(gamePlan, this));
    }

    /**
     * method returns a welcome message that introduces the player to the game
     *
     * @return - String message welcome
     */
    public String getWelcome() {
        return "\n"+"Vítej detektive!\n" +
                "Máme tu vraždu, obětí je manžel. Nevíme si rady a proto vás potřebujeme.\n" +
                "Kdybyste si nevěděl rady s vašimi schopnostmi, napište \"nápověda\"." +
                "\n" +
                gamePlan.getCurrentRoom().extendedDescription();
    }

    /**
     * method returns a message to be displayed at the end of the game.
     *
     * @return - String message epilogue
     */
    public String getEpilogue() {
        return "\nDěkuji, že jste si zahráli tuto hru:)";
    }

    /**
     * method returns a boolean value indicating whether the game should end or not.
     *
     * @return - boolean value
     */
    public boolean endGame() {
        return endGame;
    }

    /**
     * method processes a command entered by the player.
     * It splits the command into individual words, extracts the command word and
     * its parameters, and then executes the corresponding command using the validCommands object.
     * The method returns a string representing the output or response of the executed command.
     * @param row - command entered by the player
     * @return - String
     */
    public String processCommand (String row) {
        String[] words = row.split("[ \t]+");
        String commandWord = words[0];
        String[] parameters = new String[words.length-1];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = words[i+1];
        }
        String writtenText = " .... ";
        if (validCommands.isValidCommand(commandWord)) {
            ICommand command = validCommands.getCommand(commandWord);
            writtenText = command.executeCommand(parameters);
        }
        else {
            writtenText = "Nevím co tím myslíš, tento příkaz neznám.:(";
        }
        return writtenText;
    }

    /**
     * method is used to set the endGame variable, which determines whether the game should end or not.
     *
     * @param endGame - true or false
     */
    void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    /**
     * method returns the gamePlan object,
     * which represents the game plan and contains information about the current room and shared inventory.
     *
     * @return - gamePlan
     */
    public GamePlan getGamePlan() {
        return gamePlan;
    }
}
