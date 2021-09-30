package com.marcusfriberg.alarm;
// Imports
import com.marcusfriberg.estate.Door;

// DoorDetector, belongs to only one door-object. Will answer true
// to the central units request if the door is open while this
// component is active.
public class DoorDetector extends AlarmComponent implements DetectorComponent {
    // Variables
    private Door door;
    private boolean hasSentAlert = false;

    // Constructor
    public DoorDetector(String name, Door door) {
        super(name);
        this.door = door;
        // Add this detector to the central units list of door detectors.
        this.door.getRoom().getEstate().getCentralUnit().connectDoorDetector(this);
    }

    // Setters & Getters
    public Door getDoor() {
        return door;
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
        if(!hasSentAlert && door.isOpen()) {
            hasSentAlert = true;
            return true;
        } else {
            return false;
        }

    }
}
