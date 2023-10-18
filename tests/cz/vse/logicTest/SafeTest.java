package cz.vse.logicTest;

import cz.vse.logic.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test the class Safe
 * The class tests the safe's situation depending on password (correct, incorrect)
 * It also tests, whether we can get object depending on its state
 *
 * @author Tuan Ha
 * @version 2023
 */

public class SafeTest {

    private Safe safe;
    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room("Living Room", "A cozy living room", new Inventory());
        safe = new Safe(10, "1234", room);
    }

    @Test
    public void testTryUnlock_CorrectPassword() {
        boolean result = safe.tryUnlock("1234");
        assertTrue(result);
        assertFalse(safe.isLocked());
    }

    @Test
    public void testTryUnlock_IncorrectPassword() {
        boolean result = safe.tryUnlock("4321");
        assertFalse(result);
        assertTrue(safe.isLocked());
    }

    @Test
    public void testGetObjectives_UnlockedSafe() {
        Objective objective1 = new Objective("Key", true, "A key to unlock a door");
        Objective objective2 = new Objective("Note", false, "A secret note");
        List<Objective> objectives = new ArrayList<>();
        objectives.add(objective1);
        objectives.add(objective2);
        safe = new Safe(10, "1234", objectives, room);

        safe.tryUnlock("1234");
        List<Objective> result = safe.getObjectives();
        assertEquals(objectives, result);
    }

    @Test
    public void testGetObjectives_LockedSafe() {
        Objective objective1 = new Objective("Key", true, "A key to unlock a door");
        Objective objective2 = new Objective("Note", false, "A secret note");
        List<Objective> objectives = new ArrayList<>();
        objectives.add(objective1);
        objectives.add(objective2);
        safe = new Safe(10, "1234", objectives, room);

        List<Objective> result = safe.getObjectives();
        assertTrue(result.isEmpty());
    }

}
