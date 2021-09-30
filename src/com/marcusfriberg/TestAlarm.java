package com.marcusfriberg;
// Imports
import com.marcusfriberg.alarm.*;
import com.marcusfriberg.estate.*;
import java.util.ArrayList;
import java.util.List;

// A class for testing the alarm system by modifying the state of the objects
// that an alarm component monitors.
public class TestAlarm {
    // Variables
    private Estate estate;
    private CentralUnit centralUnit;
    private GetRandomNumberInRange randomizer = new GetRandomNumberInRange();
    // Create lists for all windows and doors to run the tests
    // from because all rooms may not include these objects. Other events may
    // be tested directly from the estates list of rooms, outdoor environments
    // as all rooms have smoke detectors and a phone with alarm button and all
    // outdoor environments have a motion detector.
    private List<Window> testWindows = new ArrayList<>();
    private List<Door> testDoors = new ArrayList<>();


    // Constructor
    public TestAlarm(Estate estate, CentralUnit centralUnit) {
        this.estate = estate;
        this.centralUnit = centralUnit;
    }

    // Methods
    // Prepare for tests by adding all windows, doors and alarm buttons to the test lists.
    // Also creates a GetRandomnumberInRange-object to call for random numbers.
    public void prepareTest() {
        for (Room room : estate.getRooms()) {
            for (Window window : room.getWindows()) {
                testWindows.add(window);
            }
            for (Door door : room.getDoors()) {
                testDoors.add(door);
            }
        }
    }

    // Method to open a random door in the estate
    public void testOpenRandomDoor() {
        Door door = testDoors.get(randomizer.getRandomNumber(testDoors.size()));
        door.openDoor();
    }

    // Method to open a random window in the estate
    public void testOpenRandomWindow() {
        Window window = testWindows.get(randomizer.getRandomNumber(testWindows.size()));
        window.openWindow();
    }

    // Method to set a random room in the estate on fire
    public void testSetRandomRoomOnFire() {
        Room room = estate.getRooms().get(randomizer.getRandomNumber(estate.getRooms().size()));
        room.setOnFire(true);
    }

    // Method to spawn a burglar in a random outdoor environment
    public void testSpawningBurglarInRandomOutdoorEnvironment() {
        OutdoorEnvironment outdoorEnvironment = estate.getOutdoorEnvironments().get(randomizer.getRandomNumber(estate.getOutdoorEnvironments().size()));
        outdoorEnvironment.setHasLargeMassIntruder(true);
    }

    // Method to push the alarm button of a random phone
    public void testPressingRandomPhonesAlarmButton() {
        PhoneWithAlarmButton phoneWithAlarmButton = centralUnit.getPhoneWithAlarmButtons().get(randomizer.getRandomNumber(centralUnit.getPhoneWithAlarmButtons().size()));
        phoneWithAlarmButton.setAlarmButtonPressed(true);
    }

    // Method to push a random alarm button
    public void testPressingRandomAlarmButton() {
        AlarmButton alarmButton = centralUnit.getAlarmButtons().get(randomizer.getRandomNumber(centralUnit.getAlarmButtons().size()));
        alarmButton.setAlarmButtonPressed(true);
    }


}
