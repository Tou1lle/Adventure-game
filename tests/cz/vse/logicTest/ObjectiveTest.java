package cz.vse.logicTest;

import cz.vse.logic.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This testing class is just for testing basic methods such as
 * - getName
 * - isCollectible
 * - getDescription
 *
 * @author Tuan Ha
 * @version 2023
 */

public class ObjectiveTest {

    @Test
    public void testGetName() {
        Objective objective = new Objective("Key", true, "A key to unlock a door");
        assertEquals("Key", objective.getName());
    }

    @Test
    public void testIsCollectible() {
        Objective objective1 = new Objective("Key", true, "A key to unlock a door");
        Objective objective2 = new Objective("Note", false, "A secret note");

        assertTrue(objective1.isCollectible());
        assertFalse(objective2.isCollectible());
    }

    @Test
    public void testGetDescription() {
        Objective objective = new Objective("Key", true, "A key to unlock a door");
        assertEquals("A key to unlock a door", objective.getDescription());
    }
}