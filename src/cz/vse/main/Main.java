package cz.vse.main;

import cz.vse.logic.Game;
import cz.vse.logic.IGame;
import cz.vse.uiText.TextInterface;

/**
 * Entry point for the game
 * Creates an instance of game
 * Creates an instance of Text interface - passing game as parameter
 * calls method play
 */
public class Main {
    public static void main(String[] args) {

        IGame game = new Game();
        TextInterface ui = new TextInterface(game);
        ui.play();

    }
}
