package cz.vse.logic;

/**
 * This class implements the interface ICommand
 * It handles the "nápověda" command in the game.
 *
 * @author Tuan Ha
 * @version 2023
 */
public class CommandHelp implements ICommand{

    private static final String NAME = "nápověda";
    private ListOfCommands validCommands;

    /**
     * The constructor initializes a CommandHelp object with a reference to a ListOfCommands object.
     * It allows the command to access the list of valid commands in the game.
     *
     * @param validCommands - valid commands declared in game
     */
    public CommandHelp(ListOfCommands validCommands) {
        this.validCommands = validCommands;
    }

    /**
     * method is called when the "nápověda" command is executed in the game.
     * The method returns a string containing helpful information and instructions for the player.
     *
     * @param parameters - this command does not require any parameters
     * @return - String message for the player
     */
    @Override
    public String executeCommand(String... parameters) {
        return "Tvým úkolem je přijít na to, kdo je vrah.\n"
                + "Ukončit hru můžeš buď příkazem KONEC a nebo OBVINIT.\n"
                + "V případě obvinit pak záleží, zda máš potřebné důkazy anebo ne.\n"
                + "Pro úspěšný konec musíš 3 VĚCI, které naznačují, proč by podezřelý chtěl zabít oběť.\n"
                + "Také musíš obvinit správného pachatele.\n"
                + "\n"
                + "Seznam použitelných příkazů: \n"
                + validCommands.getCommandNames()
                + "\n"
                + "\n"
                + "Jednotlivé věci můžes prozkoumat pro získání dalších informací.\n"
                + "Můžeš vyslechnout podezřelé pro dodatečné informace. \n"
                + "V domě jsou \"lístečky\", které obsahují hádánky, které musíš vyluštit \n"
                + "Některé místnosti jsou zamčené, "
                + "abys je odemknul musíš mít klíč a použít příkaz odemkni + <název místnosti>"
                + "\n"
                + "Po odemknutí místnosti se ti klíč automaticky smaže z inventáře. \n"
                + "V některých místnostech jsou trezory s věcmi. \n"
                + "Abys odemknul trezor, zadej: otevři + <heslo>.\n"
                + "Po odemknutí trezoru automaticky trezor z místnosti zmizí (NE).";
    }

    /**
     * method returns the name of the command, which is "nápověda"
     *
     * @return - String name
     */
    @Override
    public String getName() {
        return NAME;
    }
}
