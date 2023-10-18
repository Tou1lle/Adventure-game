package cz.vse.logic;

public interface IGame {

    public String getWelcome();

    public String getEpilogue();

    public boolean endGame();

    public String processCommand (String line);

    public GamePlan getGamePlan();

}
