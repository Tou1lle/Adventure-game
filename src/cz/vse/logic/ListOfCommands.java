package cz.vse.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * This class manages the list of commands used in game
 * The ListOfCommands class utilizes a Map called commandMap to store command objects.
 * Each command is associated with a unique name.
 *
 * @author Tuan Ha
 * @version 2023
 */
public class ListOfCommands {

    private Map<String, ICommand> commandMap;

    /**
     * The constructor ListOfCommands() initializes the commandMap as an empty HashMap.
     */
    public ListOfCommands() {
        commandMap = new HashMap<>();
    }

    /**
     * method takes an ICommand object as a parameter and inserts it into the commandMap.
     * The command's name is used as the key in the map.
     *
     * @param command - commands getting added to list
     */
    public void insertCommand(ICommand command) {
        commandMap.put(command.getName(),command);
    }

    /**
     * method retrieves a command from the commandMap based on the provided name
     * (specified by the str parameter). If a command with the given name exists in the map,
     * it is returned as an ICommand object; otherwise, null is returned.
     *
     * @param str - name of the command (key)
     * @return - ICommand object
     */
    public ICommand getCommand(String str) {
        if (commandMap.containsKey(str)) {
            return commandMap.get(str);
        }
        else {
            return null;
        }
    }

    /**
     * method checks whether a command with the provided name (str) exists in the commandMap.
     * It returns true if the command exists, and false otherwise.
     *
     * @param str - name of the Command
     * @return - boolean value
     */
    public boolean isValidCommand(String str) {
        return commandMap.containsKey(str);
    }

    /**
     * method generates a string containing the names of all commands stored in the commandMap.
     * It iterates over the keys of the map and appends each command name, separated by spaces, to the list string.
     * The resulting string of command names is then returned.
     *
     * @return - String of commands
     */
    public String getCommandNames() {
        String list = "";
        for (String commandWord : commandMap.keySet()) {
            list += commandWord + " ";
        }
        return list;
    }
}
