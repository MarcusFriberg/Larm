package com.marcusfriberg.estate;

import com.marcusfriberg.alarm.AlarmButton;
import com.marcusfriberg.alarm.FireSprinkler;
import com.marcusfriberg.alarm.PhoneWithAlarmButton;
import com.marcusfriberg.alarm.SmokeDetector;
import java.util.ArrayList;
import java.util.List;

// Class Room, holding information about the windows and doors
// belonging to the room as well as installed smoke detector,
// fire sprinkler, the name of the room and the Estate it belongs to.
public class Room  {
    // Variables
    private String name;
    private boolean isOnFire = false;
    private List<Window> windows = new ArrayList<>();
    private List<Door> doors = new ArrayList<>();
    private SmokeDetector smokeDetector;
    private FireSprinkler fireSprinkler;
    private PhoneWithAlarmButton phoneWithAlarmButton;
    private Estate estate;

    // Constructor
    public Room(String name, int numberOfWindows, int numberOfDoors, boolean hasAlarmButton, Estate estate) {
        this.name = name;
        this.estate = estate;
        System.out.println("Skapade rummet " + name);
        // Create the provided number of windows and add to this rooms list of windows
        for (int i = 1; i <= numberOfWindows; i++) {
            Window window = new Window("fönster "+i, this, false);
            windows.add(window);
        }

        // Create the provided number of doors and add to this rooms list of doors
        for (int i = 1; i <= numberOfDoors; i++) {
            Door door = new Door("ytterdörr "+i, this, false, false);
            doors.add(door);
        }

        // Create a smoke detector for this room and store it in this rooms smokeDetector variable
        SmokeDetector smokeDetector = new SmokeDetector("Rökdetektor", this);
        this.smokeDetector = smokeDetector;

        // Create a fire sprinkler for this room and store it in this rooms fireSprinkler variable
        FireSprinkler fireSprinkler = new FireSprinkler("Vattensprinkler", this);
        this.fireSprinkler = fireSprinkler;

        // Create a phone with alarm button for this room and store it in this rooms phoneWithAlarmButton variable
        PhoneWithAlarmButton phoneWithAlarmButton = new PhoneWithAlarmButton("Telefon med larmknapp", this);
        this.phoneWithAlarmButton = phoneWithAlarmButton;

        // Create an alarmButton if the parameter hasAlarmButton is set to true
        if(hasAlarmButton) { AlarmButton alarmButton = new AlarmButton("Larmknapp", this); }

        // Add this room to the rooms-list of the estate
        estate.addRoom(this);
    }

    // Setters & Getters
    public String getName(){
        return this.name;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public SmokeDetector getSmokeDetector() {
        return smokeDetector;
    }

    public FireSprinkler getFireSprinkler() {
        return fireSprinkler;
    }

    public Estate getEstate() {
        return this.estate;
    }

    public boolean isOnFire() {
        return isOnFire;
    }

    public void setOnFire(boolean onFire) {
        isOnFire = onFire;
        if (onFire) {
            System.out.println("Händelse: Det börjar brinna i " + name + ".");
        } else {

            System.out.println("Händelse: Det slutade brinna i " + name + ".");
        }
    }
}
