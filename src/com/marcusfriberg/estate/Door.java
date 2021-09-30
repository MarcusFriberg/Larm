package com.marcusfriberg.estate;
// Imports
import com.marcusfriberg.alarm.DoorDetector;

// Door, belongs to a room-object. Can be open/closed and locked/unlocked.
// Can also be used to configure a gate on a garage. Same principle so the
// software does not care. If a door is configured, a door detector will be
// created and added with it.
public class Door {
    // Variables
    private String name;
    private boolean isOpen;
    private boolean isLocked;
    private Room room;

    // Constructor
    public Door(String name, Room room, boolean isOpen, boolean isLocked) {
        this.name = name;
        this.room = room;
        this.isOpen = isOpen;
        this.isLocked = isLocked;
        System.out.println(" -Skapade " + name + " i " + room.getName());
        DoorDetector doorDetector = new DoorDetector("Dörrdetektor", this);
    }

    // Setters & Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isLocked() {
        return isLocked;
    }

    // Event Methods
    public void openDoor() {
        this.isOpen = true;
        System.out.println("Händelse: " + name + " i " + room.getName() + " öppnades.");
    }

    public void closeDoor() {
        this.isOpen = false;
        System.out.println("Händelse: " + name + " i " + room.getName() + " stängdes.");
    }

    public void lockDoor() {
        this.isLocked = true;
        System.out.println("Åtgärd: Låser " + name + " i " + room.getName());
    }

    public void unlockDoor() {
        this.isLocked = false;
        System.out.println("Åtgärd: upp " + name + " i " + room.getName());
    }
}
