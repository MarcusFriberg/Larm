package com.marcusfriberg.alarm;

// Siren, a simple device that makes a horrible sound.
// Central unit knows about this object and tells it when
// to start making the noise and when to stop.
public class Siren extends AlarmComponent implements PreventionComponent {
    // Variables
    private boolean makingSound = false;

    // Constructor
    public Siren(String name) {
        super(name);
    }

    // Setters & Getters
    public boolean isMakingSound() {
        return makingSound;
    }

    public void setMakingSound(boolean makingSound) {
        this.makingSound = makingSound;
    }

    // Event Methods
    @Override
    public void startPreventiveAction() {
        System.out.println("Åtgärd: Larmet tjuter !!!!");
        makingSound = true;
    }

    @Override
    public void stopPreventiveAction() {
        System.out.println("Händelse: Larmet tystnar :)");
        makingSound = false;
    }
}
