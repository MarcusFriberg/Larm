package com.marcusfriberg.alarm;

// An abstract class that defines the basics of an alarm component.
// All components will inherit from this class.
// Detector components and preventive components with also implement interfaces.
public abstract class AlarmComponent {
    // Variables
    private String name;
    private boolean isActive = true;

    // Constructor
    public AlarmComponent(String name) {
        this.name = name;
    }

    // Setters & Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
