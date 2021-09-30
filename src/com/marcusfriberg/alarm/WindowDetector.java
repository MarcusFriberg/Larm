package com.marcusfriberg.alarm;
// Imports
import com.marcusfriberg.estate.Window;

// WindowDetector, belongs to only one window-object. Will answer true
// to the central units request if the window is open while this
// component is active.
public class WindowDetector extends AlarmComponent implements DetectorComponent {
    // Variables
    private Window window;
    private boolean hasSentAlert = false;

    // Constructor
    public WindowDetector(String name, Window window) {
        super(name);
        this.window = window;
        // Add the this detector to the central units list of window detectors.
        this.window.getRoom().getEstate().getCentralUnit().connectWindowDetector(this);
    }

    // Setters & Getters
    public Window getWindow() {
        return window;
    }

    public boolean isHasSentAlert() {
        return hasSentAlert;
    }

    public void setHasSentAlert(boolean hasSentAlert) {
        this.hasSentAlert = hasSentAlert;
    }

    // Event Methods
    @Override
    public boolean detectingProblem() {
        if(!hasSentAlert && window.isOpen()) {
            hasSentAlert = true;
            return true;
        } else {
            return false;
        }
    }
}
