package cz.vse.logic;

import java.util.Arrays;

/**
 * The provided code represents
 * a class called GamePlan that manages the game plan and the rooms within the game
 * class manages the game plan and holds information about the current room and the shared inventory
 *
 * @author Tuan Ha
 * @version 2023
 */

public class GamePlan {

    private Room currentRoom;

    private final Inventory sharedInventory;

    /**
     * The constructor GamePlan() is used to create a new GamePlan object.
     * It initializes the sharedInventory by creating a new Inventory object
     * and calls the private method createRoom() to create the rooms and set up the game environment.
     */
    public GamePlan() {
        sharedInventory = new Inventory();
        createRoom();
    }

    /**
     * createRoom() creates room in game
     * - setting exits, creating instances of rooms, adding objectives, locked rooms and safes
     */
    private void createRoom () {
        /*
        Creating Rooms
         */
        Room garden = new Room("venkov", "venkov, který je před domem. " +
                "Jsou tu podezřelé, které můžes vyslechnout.", sharedInventory);
        Room lobby = new Room("předsíň", "předsíň, zde leží mrtvola manžela.", sharedInventory);
        Room hostRoom = new Room("pokoj", "pokoj. Tento pokoj slouží převážně pro hosty.",sharedInventory);
        Room wardrobe = new Room("šatník", "šatník, je tu pověšené oblečení.",sharedInventory);
        Room toilet = new Room("záchod", "záchod, je tu umyvadlo se zrcadlovou skříňkou.",sharedInventory );
        Room livingRoom = new Room("obývák", "obývák s velkým černým gaučem, velkou plasmovou televizí.",sharedInventory);
        Room kitchen = new Room("kuchyň", "kuchyň s kuchyňským stolkem. " +
                "Kuchyň je hezky vybavena. Sada kuchyňských nožů se zdá nekompletní",sharedInventory);
        Room lobby2 = new Room("chodba", "chodba, nyní jsi v druhém patře domu.",sharedInventory);
        Room bathroom = new Room("koupelna", "koupelna, je tu velká vana a i záchod.",sharedInventory);
        Room bedroomSon = new Room("pokojíček","pokojíček, který patří synovi. " +
                "Má tu svojí televizi a PS5.",sharedInventory);
        Room bedroom = new Room("ložnice", "ložnice s velkou manželskou postelí.",sharedInventory);

        /*
        Adding exits to rooms
         */
        garden.setExit(lobby);
        lobby.setExit(hostRoom);
        lobby.setExit(garden);
        lobby.setExit(toilet);
        lobby.setExit(wardrobe);
        lobby.setExit(livingRoom);
        lobby.setExit(lobby2);
        hostRoom.setExit(lobby);
        toilet.setExit(lobby);
        wardrobe.setExit(lobby);
        livingRoom.setExit(lobby);
        livingRoom.setExit(kitchen);
        kitchen.setExit(livingRoom);
        lobby2.setExit(lobby);
        lobby2.setExit(bedroomSon);
        lobby2.setExit(bathroom);
        lobby2.setExit(bedroom);
        bedroom.setExit(lobby2);
        bedroomSon.setExit(lobby2);
        bathroom.setExit(lobby2);

        /*
         * creating objectives:
         */

        Objective toiletObj = new Objective("záchod", false, "Toto je záchod");
        Objective victim = new Objective("oběť", false, "Jmenuje se Martin Mladý. "
                + "34 let, úspešný a hezký." + "\n" +
                "Umřel na bodnou ránu nožem do zad." + "\n" + "Datum narození 11.03.1975");

        Objective key = new Objective("klíč", true, "Klíč pro odemčení dveří");
        Objective key1 = new Objective("klíč", true, "Klíč pro odemčení dveří");

        /*
        Proofs for suspects
         */
        Objective letter = new Objective("dopis", true,
                "Dobrý den pane T.T., tady Česká Národní Banka," + "\n" +
                        "pokud nám do měsíce nevrátíte dluh, budeme nuceni vám sebrat majetek.");
        Objective rose = new Objective("růže", true ,
                "Je tu zpráva: Miluju tě víc než on!!!!!, R.R.");
        Objective sonDiary = new Objective("deník", true, "Toto je deník syna," +
                " obsahuje: \n" +
                " - Favourite: Snorlax \n" +
                " - Přiznání: Ukradl jsem peníze z rodiného trezoru, táta na to přišel, nevím co mám dělat.");
        Objective stolenMoney = new Objective("peníze", true, "Toto jsou ukradené peníze");
        Objective phone = new Objective("telefon", true, "Telefon oběti, obsahuje: \n" +
                "Ahoj Lucko, Andrea nás začíná podezírat, ale chová se podezřele klidně, \n" +
                "bojím se, co provede. Musíme být opatrní. \n" +
                "Až se s ní rozvedu, můžeme bát spolu.");
        Objective lavender = new Objective("levandule", true, "Je tu zpráva: " +
                "udělam cokoliv!");
        Objective wifeDiary = new Objective("deníček", true, "Toto je deníček ženy, obsahuje: \n" +
                "- zabila jsem ho, nedokázala jsem přijmout, že mě podvedl");
        Objective pencil = new Objective("propiska", true, "Tato propiska je od krve");
        Objective letter1 = new Objective("zpráva", true, "ručně psaná zpráva, \"klidně ho pro tebe zabiju\"");

        /*
        Riddles for tips
         */
        Objective poster1 = new Objective("plakát", true, "plakát s filmem " +
                "Avenger Age of Ultron");
        Objective cards = new Objective("karty", true, "sada pokemon kartiřek: \n" +
                "Pikachu - 12\n" +
                "Snorlax - 1 \n" +
                "Charizard - 10");
        Objective cd = new Objective("desky", true, "desky hudební skupiny Beatles");
        Objective photo = new Objective("fotka", true, "Svatební fotka, na zadní straně: 30.11.2001");

        /*
        Riddles, tips are not collectible
         */
        Objective tip0 = new Objective("lístek", false,
                "Nápověda:\n" +
                         "Trezor šatník - Data narození (DD): MM-AS-TT");
        Objective tip1 = new Objective("lístek", false,
                "Nápověda: \n" +
                        "- Yellow Submarine, 1954 \n" +
                        "- Take on Me, 1985 \n" +
                        "- Careless Whisper, 1984");
        Objective tip2 = new Objective("lístek", false, "" +
                "Nápověda: \n" +
                "Trezor ženy - Měsíc výročí svatby,\n" +
                "Díl mého nejoblíbenějšího superhrdinského filmu\n " +
                "George Michael\n" +
                "Tomášova nejoblíbenější pokémon karta");
        Objective tip3 = new Objective("lístek", true, "Nápověda: Trezor syna - Rok skladky mé nejoblíbenější skupiny");

        /*
        adding objectives too rooms
         */
        toilet.addObjective(toiletObj);

        kitchen.addObjective(lavender);

        lobby.addObjective(victim);

        livingRoom.addObjective(poster1);
        livingRoom.addObjective(phone);
        livingRoom.addObjective(photo);
        livingRoom.addObjective(letter1);

        hostRoom.addObjective(tip0);
        hostRoom.addObjective(letter);
        hostRoom.addObjective(rose);

        bedroom.addObjective(tip2);

        bedroomSon.addObjective(cards);
        bedroomSon.addObjective(cd);
        bedroomSon.addObjective(tip3);

        /*
        Declaring locked rooms
         */
        bedroomSon.setLocked(true);
        bedroomSon.setKey(key);

        bedroom.setLocked(true);
        bedroom.setKey(key1);

        /*
        Creating safes, declaring password, adding objectives, defining the room for safes
         */
        Safe safeWardrobe = new Safe(100, "110619", Arrays.asList(key, tip1), wardrobe);
        Safe safeSon = new Safe(100, "1954", Arrays.asList(key1, sonDiary, stolenMoney), bedroomSon);
        Safe safeWife = new Safe(100, "11219841", Arrays.asList(pencil, wifeDiary), bedroom);

        /*
        list of suspects
         */
        Suspect wife = new Suspect("žena", true, "Jméno: Andrea Stará\n" +
                "Datum narození: 06.11.1979\n" +
                "Povolání: prodovačka");

        Suspect son = new Suspect("syn", false, "Jméno: Tomáš Tupý\n" +
                "Datum narození: 19.03.2000\n" +
                "Povolání: nezaměstnaný, hledá práci");

        Suspect neighbour = new Suspect("soused", false, "Jméno: Rober Robertovský\n" +
                "Datum narození: 02.02.1970\n" +
                "Povolání: fotograf");

        /*
        suspect and their proofs
         */
        son.addProof(letter);
        son.addProof(sonDiary);
        son.addProof(stolenMoney);

        neighbour.addProof(rose);
        neighbour.addProof(lavender);
        neighbour.addProof(letter1);

        wife.addProof(phone);
        wife.addProof(wifeDiary);
        wife.addProof(pencil);

        /*
        adding suspect to garden
         */
        garden.addSuspect(wife);
        garden.addSuspect(son);
        garden.addSuspect(neighbour);

        currentRoom = garden;
    }

    /**
     * method returns the current room in which the player is located.
     *
     * @return - current room, where the player is located
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * method is used to set the current room to a new room provided as a parameter.
     *
     * @param room - sets a room as the new current room
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * method returns the shared inventory object.
     *
     * @return shared invetory
     */
    public Inventory getInventory(){
        return sharedInventory;
    }

}
