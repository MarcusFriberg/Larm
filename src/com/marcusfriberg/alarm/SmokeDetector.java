package com.marcusfriberg.alarm;
// Imports
import com.marcusfriberg.estate.Room;

// SmokeDetector, belongs to only one room-object. Will answer true
// to the central units request if the room is on fire while this
// component is active.
public class SmokeDetector extends AlarmComponent implements DetectorComponent {
    // Variables
    private Room room;
    private boolean hasSentAlert = false;

    // Constructor
    public SmokeDetector(String name, Room room) {
        super(name);
        this.room = room;
        // Add this detector to the central units list of smoke detectors.
        this.room.getEstate().getCentralUnit().connectSmokeDetector(this);
    }

    // Setters & Getters
    public Room getRoom() {
        return room;
    }

    public boolean isHasSentAlert() {
        return hasSentAlert;
    }

    public void setHasSentAlert(boolean hasSentAlert) {
        this.hasSentAlert = hasSentAlert;
    }

    // Event Methods
    // Method detectingProblem, returns true if the detector is triggered.
    // Returns false if it is not triggered or if the central unit already
    // been notified about it.
    @Override
    public boolean detectingProblem() {
        if(!hasSentAlert && room.isOnFire()) {
            hasSentAlert = true;
            return true;
        } else {
            return false;
        }
    }
}
