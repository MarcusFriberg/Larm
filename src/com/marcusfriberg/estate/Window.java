package com.marcusfriberg.estate;
// Imports
import com.marcusfriberg.alarm.WindowDetector;

// Window, belongs to a room-object. Can be open/closed.
// If a window is configured, a window detector will be
// created and added with it.
public class Window {
    // Variables
    private String name;
    private boolean isOpen;
    private Room room;

    // Constructor
    public Window(String name, Room room, boolean isOpen) {
        this.name = name;
        this.room = room;
        this.isOpen = isOpen;
        System.out.println(" -Skapade " + name + " i " + room.getName());
        WindowDetector windowDetector = new WindowDetector("Fönsterdetektor", this);
    }

    // Setters & Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return this.room;
    }

    public boolean isOpen() {
        return isOpen;
    }

    // Event Methods
    public void openWindow() {
        this.isOpen = true;
        System.out.println("Händelse: " + name + " i " + room.getName() + " öppnades.");
    }

    public void closeWindow() {
        this.isOpen = false;
        System.out.println("Händelse: " + name + " i " + room.getName() + " stängdes.");
    }
}
