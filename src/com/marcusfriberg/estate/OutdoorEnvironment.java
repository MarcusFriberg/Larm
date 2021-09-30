package com.marcusfriberg.estate;
// Imports
import com.marcusfriberg.alarm.OutdoorMotionDetector;

// Class OutdoorEnvironment, holding information about the
// outdoor motion detector and if there are any burglars in the environment.
public class OutdoorEnvironment {
    // Variable
    private String name;
    private boolean hasLargeMassIntruder = false;
    private OutdoorMotionDetector outdoorMotionDetector;
    private Estate estate;

    // Constructor
    public OutdoorEnvironment(String name, Estate estate) {
        this.name = name;
        this.estate = estate;
        System.out.println("Skapade " + name);
        // Add a outdoor motion detector to this outdoor environment and store it
        OutdoorMotionDetector outdoorMotionDetector = new OutdoorMotionDetector("Utomhuskamera", this);
        this.outdoorMotionDetector = outdoorMotionDetector;
        // Add this room to the rooms-list of the estate
        estate.addOutdoorEnvironment(this);
    }

    // Setters & Getters
    public String getName() {
        return name;
    }

    public Estate getEstate() {
        return estate;
    }

    public OutdoorMotionDetector getOutdoorMotionDetector() {
        return outdoorMotionDetector;
    }

    public boolean isHasLargeMassIntruder() {
        return hasLargeMassIntruder;
    }

    public void setHasLargeMassIntruder(boolean hasLargeMassIntruder) {
        this.hasLargeMassIntruder = hasLargeMassIntruder;
        if (hasLargeMassIntruder) {
            System.out.println("Händelse: Det smyger in en tjuv i " + name + ".");
        } else {
            System.out.println("Händelse: Tjuven lämnar " + name + ".");
        }
    }
}
