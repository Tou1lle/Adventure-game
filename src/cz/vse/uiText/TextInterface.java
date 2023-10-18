package cz.vse.uiText;

import cz.vse.logic.IGame;

import java.util.Scanner;

/**
 * This class is responsible for providing a text-based user interface for the game
 */
public class TextInterface {

    private IGame game;

    /**
     * The TextInterface class takes an IGame object as a parameter in its constructor,
     * allowing it to interact with the game logic.
     *
     * @param game - game
     */
    public TextInterface(IGame game) {
        this.game = game;
    }

    /**
     * Start by printing getWelcome()
     * Loop until game ends, reads players input readString()
     * player's input is based through processCommand()
     */
    public void play() {
        System.out.println(game.getWelcome());

        while (!game.endGame()){
            String row = readString();
            System.out.println(game.processCommand(row));
        }
        System.out.println(game.getEpilogue());
    }

    /**
     * The readString() method creates a Scanner object to read input from the console.
     * It displays the prompt symbol (">") and waits for the player to enter a line of text.
     * The entered text is then returned as a string.
     *
     * @return - entered text from the player, used in play
     */
    private String readString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }
}
