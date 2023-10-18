package cz.vse.logic;

public interface ICommand {

    public String executeCommand(String... parameters);

    public String getName();
}
