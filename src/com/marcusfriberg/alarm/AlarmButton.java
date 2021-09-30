package com.marcusfriberg.alarm;
// Imports
import com.marcusfriberg.estate.Room;

// AlarmButton, belongs to only one room-object. Will answer true
// to the central units request if the alarm button has been pressed.
public class AlarmButton extends AlarmComponent implements DetectorComponent {
    // Variables
    private String name;
    private boolean alarmButtonPressed = false;
    private boolean hasSentAlert = false;
    private Room room;

    // Constructor
    public AlarmButton(String name, Room room) {
        super(name);
        this.room = room;
        // Add this phone with alarm button to the central units list of phones.
        this.room.getEstate().getCentralUnit().connectAlarmButton(this);
    }

    // Setters & Getters
    public void setAlarmButtonPressed(boolean alarmButtonPressed) {
        this.alarmButtonPressed = alarmButtonPressed;
        if(alarmButtonPressed) {
            System.out.println("Händelse: " + this.getName() + " i " + room.getName() + " har tryckts in.");
        }
    }

    public boolean isHasSentAlert() {
        return hasSentAlert;
    }

    public void setHasSentAlert(boolean hasSentAlert) {
        this.hasSentAlert = hasSentAlert;
    }

    public Room getRoom() {
        return room;
    }

    // Event Methods
    // Method detectingProblem, returns true if the detector is triggered.
    // Returns false if it is not triggered or if the central unit already
    // been notified about it.
    @Override
    public boolean detectingProblem() {
        if(!hasSentAlert && alarmButtonPressed) {
            hasSentAlert = true;
            return true;
        } else {
            return false;
        }
    }
}
