package cz.vse.logicTest;

import cz.vse.logic.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing Class for Room
 * This class test various methods such ass adding and removing different things
 *
 * @author Tuan Ha
 * @version 2023
 */
public class RoomTest {

    @Test
    public void connectedRooms() {

        Room room1 = new Room("house", "a big house", null);
        Room room2 = new Room("garden", "garden in front the house", null);

        room1.setExit(room2);
        room2.setExit(room1);

        assertEquals(room2, room1.getNextRoom("garden"));
        assertEquals(null, room2.getNextRoom("nothing"));
    }

    private Room room;
    private Inventory sharedInventory;

    @BeforeEach
    public void setUp() {
        sharedInventory = new Inventory();
        room = new Room("Living Room", "A cozy living room", sharedInventory);
    }

    /**
     * Testing the setExit method
     */
    @Test
    public void testSetExit() {
        Room exitRoom = new Room("Kitchen", "A spacious kitchen", sharedInventory);
        room.setExit(exitRoom);
        assertTrue(room.getExits().contains(exitRoom));
    }

    /**
     * Testing the getNextRoom method
     */
    @Test
    public void testGetNextRoom_ExistingExit() {
        Room exitRoom = new Room("Kitchen", "A spacious kitchen", sharedInventory);
        room.setExit(exitRoom);
        assertEquals(exitRoom, room.getNextRoom("Kitchen"));
    }

    @Test
    public void testGetNextRoom_NonExistingExit() {
        assertNull(room.getNextRoom("Bathroom"));
    }

    @Test
    public void testAddObjective() {
        Objective objective = new Objective("Key", true, "A key to unlock a door");
        room.addObjective(objective);
        assertEquals(objective, room.getObjective("Key"));
    }

    @Test
    public void testRemoveObjective() {
        Objective objective = new Objective("Key", true, "A key to unlock a door");
        room.addObjective(objective);
        Objective removedObjective = room.removeObjective("Key");
        assertEquals(objective, removedObjective);
        assertNull(room.getObjective("Key"));
    }

    @Test
    public void testIsLocked() {
        assertFalse(room.isLocked());
        room.setLocked(true);
        assertTrue(room.isLocked());
    }

    @Test
    public void testSetKey_GetKey() {
        Objective key = new Objective("Key",true, "A key to unlock the room");
        room.setKey(key);
        assertEquals(key, room.getKey());
    }

    @Test
    public void testSetSafe_GetSafe() {
        Safe safe = new Safe(100, "1234", room);
        assertEquals(safe, room.getSafe());
    }

    @Test
    public void testAddSuspect_GetSuspect() {
        Suspect suspect = new Suspect("John Doe",false, "A potential suspect");
        room.addSuspect(suspect);
        assertEquals(suspect, room.getSuspect("John Doe"));
    }

    @Test
    public void testGetInventory() {
        assertEquals(sharedInventory, room.getInventory());
    }

    @Test
    public void testGetName() {
        assertEquals("Living Room", room.getName());
    }

    /**
     * changing the room to room2
     */
    @Test
    public void testEquals() {
        Room room2 = new Room("Living Room", "A cozy living room", sharedInventory);
        Room room3 = new Room("Bedroom", "A comfortable bedroom", sharedInventory);

        assertEquals(room, room2);
        assertNotEquals(room, room3);
    }

    /**
     * testing the hash
     */
    @Test
    public void testHashCode() {
        Room room2 = new Room("Living Room", "A cozy living room", sharedInventory);

        assertEquals(room.hashCode(), room2.hashCode());
    }

}
