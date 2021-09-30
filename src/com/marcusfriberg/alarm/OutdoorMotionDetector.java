package com.marcusfriberg.alarm;
// Imports
import com.marcusfriberg.estate.OutdoorEnvironment;

// OutdoorMotionDetector, belongs to only one outdoorenvironment-object.
// Will answer true to the central units request if the IR-camera of the
// component detects movement. Will only answer true if the moving object
// is of a given volume and not recognized as an animal. An external cloud
// service is responsible for this.
public class OutdoorMotionDetector extends AlarmComponent implements DetectorComponent {
    // Variables
    private OutdoorEnvironment outdoorEnvironment;
    private boolean hasSentAlert = false;

    // Constructor
    public OutdoorMotionDetector(String name, OutdoorEnvironment outdoorEnvironment) {
        super(name);
        this.outdoorEnvironment = outdoorEnvironment;
        // Add this detector to the central units list of outdoor motion detectors.
        this.outdoorEnvironment.getEstate().getCentralUnit().connectOutdoorMotionDetector(this);
    }

    // Setters & Getters
    public OutdoorEnvironment getGarden() {
        return outdoorEnvironment;
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
        if(!hasSentAlert && outdoorEnvironment.isHasLargeMassIntruder()) {
            hasSentAlert = true;
            return true;
        } else {
            return false;
        }
    }
}
