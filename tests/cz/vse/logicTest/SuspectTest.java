package cz.vse.logicTest;

import cz.vse.logic.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the suspect class
 * This class tests various boolean methods
 * Most important is the method getProofs, which is used for accusing the suspect
 *
 * @author Tuan Ha
 * @version 2023
 */

public class SuspectTest {

    @Test
    public void testGetName() {
        Suspect suspect = new Suspect("John Doe", false, "A potential suspect");
        assertEquals("John Doe", suspect.getName());
    }

    @Test
    public void testIsGuilty() {
        Suspect innocentSuspect = new Suspect("John Doe", false, "A potential suspect");
        Suspect guiltySuspect = new Suspect("Jane Smith", true, "A prime suspect");

        assertFalse(innocentSuspect.isGuilty());
        assertTrue(guiltySuspect.isGuilty());
    }

    @Test
    public void testGetDescription() {
        Suspect suspect = new Suspect("John Doe", false, "A potential suspect");
        assertEquals("A potential suspect", suspect.getDescription());
    }

    @Test
    public void testAddProof() {
        Suspect suspect = new Suspect("John Doe", false, "A potential suspect");
        Objective objective = new Objective("Key", true, "A key as evidence");

        suspect.addProof(objective);
        List<Objective> proofs = suspect.getProofs();

        assertTrue(proofs.contains(objective));
    }

    /**
     * Testing, whether we can get the list of proof
     * important for Command Accuse, where we compare inventory objectives with suspect proofs
     */
    @Test
    public void testGetProofs() {
        Suspect suspect = new Suspect("John Doe", false, "A potential suspect");
        Objective objective1 = new Objective("Key", true, "A key as evidence");
        Objective objective2 = new Objective("Note", true, "A note as evidence");

        suspect.addProof(objective1);
        suspect.addProof(objective2);

        List<Objective> proofs = suspect.getProofs();

        assertTrue(proofs.contains(objective1));
        assertTrue(proofs.contains(objective2));
    }
}
