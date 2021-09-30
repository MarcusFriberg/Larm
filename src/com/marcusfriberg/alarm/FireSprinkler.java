package com.marcusfriberg.alarm;
// Imports
import com.marcusfriberg.estate.Room;

// FireSprinkler, belongs to only one room-object.
// Is controlled by the central unit.
public class FireSprinkler extends AlarmComponent implements PreventionComponent {
    // Variables
    private Room room;
    private boolean takingAction = false;

    // Constructor
    public FireSprinkler(String name, Room room) {
        super(name);
        this.room = room;
        // Add this sprinkler to the central units list of sprinklers.
        this.room.getEstate().getCentralUnit().connectFireSprinkler(this);
    }

    // Getters
    public Room getRoom() {
        return room;
    }

    public boolean isTakingAction() {
        return takingAction;
    }

    // Event Methods
    @Override
    public void startPreventiveAction() {
        System.out.println("Sprinklersystemet har startat i " + room.getName());
        takingAction = true;
    }

    @Override
    public void stopPreventiveAction() {
        System.out.println("Sprinklersystemet stoppat i " + room.getName());
        takingAction = false;
    }
}
