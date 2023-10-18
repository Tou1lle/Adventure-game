package cz.vse.logicTest;

import cz.vse.logic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class Accuse test the command accuse
 * if the player chooses to accuse one of the suspects, there are 3 possibilities
 *  - suspect was not guilty - doesn't matter if he has the right proofs
 *  - suspect was guilty, but the player has not enough proofs, or not the right ones
 *  - suspect was guilty and the player has all needed proofs
 *  This test class tests all 3 situations
 *
 * @author Tuan Ha
 * @version 2023
 */

public class CommandAccuseTest {

    private GamePlan gamePlan;
    private Game game;
    private Room room;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        room = new Room("Living Room", "A cozy living room", inventory);
        gamePlan = new GamePlan();
        gamePlan.setCurrentRoom(room);
        game = new Game();
    }

    @Test
    public void testExecuteCommand_NoParameters() {
        CommandAccuse command = new CommandAccuse(gamePlan, game);
        String result = command.executeCommand();
        assertEquals("Musíš zadat název podezřelého, kterého chceš obvinit. Zadej obvinit <podezřelý>", result);
    }

    @Test
    public void testExecuteCommand_MultipleParameters() {
        CommandAccuse command = new CommandAccuse(gamePlan, game);
        String result = command.executeCommand("Suspect1", "Suspect2");
        assertEquals("Můžes obvinit jen jednoho podezřelého!", result);
    }

    @Test
    public void testExecuteCommand_SuspectNotFound() {
        CommandAccuse command = new CommandAccuse(gamePlan, game);
        String result = command.executeCommand("UnknownSuspect");
        assertEquals("Podezřelý/á UnknownSuspect se tu nenachází", result);
    }

    @Test
    public void testExecuteCommand_SuspectNotGuilty() {
        Suspect innocentSuspect = new Suspect("Innocent", false, "An innocent suspect");
        room.addSuspect(innocentSuspect);

        CommandAccuse command = new CommandAccuse(gamePlan, game);
        String result = command.executeCommand("Innocent");
        assertEquals("Bohužel se ti nepovedlo vyřešit případ správně:(\n" +
                "Buď jsi neměl dostatek důkazů nebo jsi obvinil špatného člověka.", result);
        assertTrue(game.endGame());
    }

    @Test
    public void testExecuteCommand_SuspectGuilty_NoProofs() {
        Suspect guiltySuspect = new Suspect("Guilty", true, "A guilty suspect");
        room.addSuspect(guiltySuspect);

        CommandAccuse command = new CommandAccuse(gamePlan, game);
        String result = command.executeCommand("Guilty");
        assertEquals("Bohužel se ti nepovedlo vyřešit případ správně:(\n" +
                "Buď jsi neměl dostatek důkazů nebo jsi obvinil špatného člověka.", result);
        assertTrue(game.endGame());
    }

    @Test
    public void testExecuteCommand_SuspectGuilty_WithProofs() {
        Objective proof1 = new Objective("Proof1", true, "A piece of evidence");
        Objective proof2 = new Objective("Proof2", true, "Another piece of evidence");

        Suspect guiltySuspect = new Suspect("Guilty", true, "A guilty suspect");
        guiltySuspect.addProof(proof1);
        guiltySuspect.addProof(proof2);
        room.addSuspect(guiltySuspect);

        inventory.addObjective(proof1);
        inventory.addObjective(proof2);

        CommandAccuse command = new CommandAccuse(gamePlan, game);
        String result = command.executeCommand("Guilty");
        assertEquals("Obvinil jsi správného podezřelého! Jsi dobrý detektiv!!!", result);
        assertTrue(game.endGame());
    }

}