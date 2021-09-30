package com.marcusfriberg.alarm;
// Imports
import com.marcusfriberg.MenuViewController;
import com.marcusfriberg.TestAlarm;
import com.marcusfriberg.estate.*;

import java.util.ArrayList;
import java.util.List;

// Class CentralUnit, holds information about all alarm components.
// Each component-object is stored and made accessible in the lists
// for different kind of components. CentralUnit belongs to an estate-
// object. CentralUnit will request updates from all detector-components
// and decide on what action to take if the detectors are triggered or in
// case they do not respond.
public class CentralUnit extends AlarmComponent {
    // Variables
    private List<DoorDetector> doorDetectors = new ArrayList<>();
    private List<OutdoorMotionDetector> outdoorMotionDetectors = new ArrayList<>();
    private List<SmokeDetector> smokeDetectors = new ArrayList<>();
    private List<WindowDetector> windowDetectors = new ArrayList<>();
    private List<FireSprinkler> fireSprinklers = new ArrayList<>();
    private List<PhoneWithAlarmButton> phoneWithAlarmButtons = new ArrayList<>();
    private List<AlarmButton> alarmButtons = new ArrayList<>();
    private Estate estate;
    private Siren siren = new Siren("Siren");
    private ActiveThread activeThread;
    private int callPoliceDelay;
    private boolean callPoliceTimerStarted = false;
    private boolean soundAlarmTimerStarted = false;

    // Constructor
    public CentralUnit(String name, Estate estate, int callPoliceDelay) {
        super(name);
        this.estate = estate;
        this.callPoliceDelay = callPoliceDelay;
        estate.setCentralUnit(this);
        setActive(false);
    }

    // Setters & Getters
    public void setActiveThread(ActiveThread activeThread) {
        this.activeThread = activeThread;
    }

    public int getCallPoliceDelay() {
        return callPoliceDelay;
    }

    public boolean isCallPoliceTimerStarted() {
        return callPoliceTimerStarted;
    }

    public void setCallPoliceTimerStarted(boolean callPoliceTimerStarted) {
        this.callPoliceTimerStarted = callPoliceTimerStarted;
    }

    public boolean isSoundAlarmTimerStarted() {
        return soundAlarmTimerStarted;
    }

    public void setSoundAlarmTimerStarted(boolean soundAlarmTimerStarted) {
        this.soundAlarmTimerStarted = soundAlarmTimerStarted;
    }

    public List<DoorDetector> getDoorDetectors() {
        return doorDetectors;
    }

    public List<OutdoorMotionDetector> getOutdoorMotionDetectors() {
        return outdoorMotionDetectors;
    }

    public List<SmokeDetector> getSmokeDetectors() {
        return smokeDetectors;
    }

    public List<WindowDetector> getWindowDetectors() {
        return windowDetectors;
    }

    public List<FireSprinkler> getFireSprinklers() {
        return fireSprinklers;
    }

    public List<PhoneWithAlarmButton> getPhoneWithAlarmButtons() {
        return phoneWithAlarmButtons;
    }

    public List<AlarmButton> getAlarmButtons() {
        return alarmButtons;
    }

    // Methods to connect components to the alarm system
    public void connectDoorDetector(DoorDetector doorDetector) {
        doorDetectors.add(doorDetector);
        System.out.println("  --" + doorDetector.getName() + " för " + doorDetector.getDoor().getName() + " i " + doorDetector.getDoor().getRoom().getName() + " är installerad och ansluten till " + super.getName() + ".");
    }

    public void connectWindowDetector(WindowDetector windowDetector) {
        windowDetectors.add(windowDetector);
        System.out.println("  --" + windowDetector.getName() + " för " + windowDetector.getWindow().getName() + " i " + windowDetector.getWindow().getRoom().getName() + " är installerad och ansluten till " + super.getName() + ".");
    }

    public void connectSmokeDetector(SmokeDetector smokeDetector) {
        smokeDetectors.add(smokeDetector);
        System.out.println(" --" + smokeDetector.getName() + " i " + smokeDetector.getRoom().getName() + " är installerad och ansluten till " + super.getName() + ".");
    }

    public void connectOutdoorMotionDetector(OutdoorMotionDetector outdoorMotionDetector) {
        outdoorMotionDetectors.add(outdoorMotionDetector);
        System.out.println("  --" + outdoorMotionDetector.getName() + " i " + outdoorMotionDetector.getGarden().getName() + " är installerad och ansluten till " + super.getName() + ".");
    }

    public void connectFireSprinkler(FireSprinkler fireSprinkler) {
        fireSprinklers.add(fireSprinkler);
        System.out.println(" --" + fireSprinkler.getName() + " i " + fireSprinkler.getRoom().getName() + " är installerad och ansluten till " + super.getName() + ".");
    }

    public void connectPhoneWithAlarmButton(PhoneWithAlarmButton phoneWithAlarmButton) {
        phoneWithAlarmButtons.add(phoneWithAlarmButton);
        System.out.println(" --" + phoneWithAlarmButton.getName() + " i " + phoneWithAlarmButton.getRoom().getName() + " är installerad och ansluten till " + super.getName() + ".");
    }

    public void connectAlarmButton(AlarmButton alarmButton) {
        alarmButtons.add(alarmButton);
        System.out.println(" --" + alarmButton.getName() + " i " + alarmButton.getRoom().getName() + " är installerad och ansluten till " + super.getName() + ".");
    }

    // Method getStatusFromDetectors is called from the thread each 500 milliseconds.
    // All detectors will get a call to their detectingProblem()-method, if anything is
    // wrong and they have not already notified the central unit they return true. Else
    // they return false.
    public void getStatusFromDetectors() {
        // Check door detectors
        for (DoorDetector doorDetector : doorDetectors) {
            if(doorDetector.detectingProblem()) {
                System.out.println("Systemhändelse: " + doorDetector.getName() + " på " + doorDetector.getDoor().getName() + " i " + doorDetector.getDoor().getRoom().getName() + " har triggats.");
                doorDetectorAction();
            }
        }
        // Check window detectors
        for (WindowDetector windowDetector : windowDetectors) {
            if(windowDetector.detectingProblem()) {
                System.out.println("Systemhändelse: " + windowDetector.getName() + " på " + windowDetector.getWindow().getName() + " i " + windowDetector.getWindow().getRoom().getName() + " har triggats.");
                windowDetectorAction();
            }
        }
        // Check smoke detectors
        for (SmokeDetector smokeDetector : smokeDetectors) {
            if(smokeDetector.detectingProblem()) {
                System.out.println("Systemhändelse: " + smokeDetector.getName() + " i " + smokeDetector.getRoom().getName() + " har triggats.");
                smokeDetectorAction(smokeDetector.getRoom());
            }
        }
        // Check outdoor motion detectors
        for (OutdoorMotionDetector outdoorMotionDetector : outdoorMotionDetectors) {
            if(outdoorMotionDetector.detectingProblem()) {
                System.out.println("Systemhändelse: " + outdoorMotionDetector.getName() + " i " + outdoorMotionDetector.getGarden().getName() + " har triggats.");
                outdoorMotionDetectorAction();
            }
        }
        // Check phones with alarm button
        for (PhoneWithAlarmButton phoneWithAlarmButton : phoneWithAlarmButtons) {
            if(phoneWithAlarmButton.detectingProblem()) {
                System.out.println("Systemhändelse: " + phoneWithAlarmButton.getName() + " i " + phoneWithAlarmButton.getRoom().getName() + " har triggats.");
                phoneWithAlarmButtonAction();
            }
        }
        // Check alarm buttons
        for(AlarmButton alarmButton : alarmButtons) {
            if(alarmButton.detectingProblem()) {
                System.out.println("Systemhändelse: " + alarmButton.getName() + " i " + alarmButton.getRoom().getName() + " har triggats.");
                alarmButtonAction();
            }
        }
    }

    // Methods that gets called when detectors are triggered depending on
    // what kind of detector that was triggered.

    // Method to run when alarm button is pressed.
    public void alarmButtonAction() {
        callThePolice();
        lockAllDoors();
        soundTheAlarm();
    }

    // Method to run when the alarm button on a phone is pressed.
    public void phoneWithAlarmButtonAction() {
        callThePolice();
        soundTheAlarm();
    }

    // Method to run when a smoke detector is triggered.
    public void smokeDetectorAction(Room room) {
        startSprinklerInRoom(room);
        soundTheAlarm();
    }

    // Method to run when a door detector is triggered.
    public void doorDetectorAction() {
        soundTheAlarmWithDelay();
    }

    // Method to run when a window detector is triggered.
    public void windowDetectorAction() {
        soundTheAlarm();
    }

    // Method to run when an outdoor motion detector is triggered.
    public void outdoorMotionDetectorAction() {
        soundTheAlarmWithDelay();
    }

    // Preventive methods that can be called by the central unit in case of an alarm.
    // Different methods will be called depending on event.

    // Mehtod to start the siren.
    public void soundTheAlarm() {
        siren.startPreventiveAction();
        callThePoliceWithDelay();
    }

    // Mehtod called when doors are opened when the system is active, alarm has to be turned off within
    // 15 seconds, control units will beep to notify that the system is active and need to be turned off.
    // Timer is taken care of by the class ActiveThread that calls the soundTheAlarm method after the
    // given time.
    public void soundTheAlarmWithDelay() {
        setSoundAlarmTimerStarted(true);
        System.out.println("VARNING: Kontrollpanelen piper, du har 15 sekunder på dig att larma av");
    }

    // Method to call the police instantly.
    public void callThePolice() {
        System.out.println("Åtgärd: 112 kontaktas");
    }

    // Method to call the police with a given delay if the system is not reset.
    // Timer starts when the alarm starts sounding. Timer is taken care of by
    // class ActiveThread that calls the callThePolice method after the given time.
    public void callThePoliceWithDelay() {
        setCallPoliceTimerStarted(true);
    }

    // Method to lock all outer doors in the estate.
    public void lockAllDoors() {
        for(DoorDetector doorDetector : doorDetectors) {
            doorDetector.getDoor().lockDoor();
        }
    }

    // Method to start the fire sprinkler in a given room.
    public void startSprinklerInRoom(Room room) {
        room.getFireSprinkler().startPreventiveAction();
    }

    // Methods called by the user-menu.

    // Method to activate the alarm system.
    public void turnOnAlarmSystem() {
        if(!isActive()) {
            setActive(true);
        } else {
            System.out.println("Meddelande: Larmet är redan aktiverat!");
        }
    }

    // Method to inactivate the alarm system, only works when the system has not already
    // sounded the alarm.
    public void turnOffAlarmSystem() {
        if(isActive()) {
            setActive(false);
            // Återställ värden som kan ha ändrats om dörrdetektor eller rörelsedetektor triggats
            // men larmet avaktiverats inom angiven tidsgräns.
            if(!siren.isMakingSound()) {
                for(Room room : estate.getRooms()) {
                    for(Door door : room.getDoors()) {
                        if(door.isOpen()) {
                            door.closeDoor();
                        }
                        if(door.isLocked()) {
                            door.unlockDoor();
                        }
                    }
                }
                for(OutdoorEnvironment outdoorEnvironment : estate.getOutdoorEnvironments()) {
                    if(outdoorEnvironment.isHasLargeMassIntruder()) {
                        outdoorEnvironment.setHasLargeMassIntruder(false);
                    }
                }
                for(DoorDetector doorDetector : doorDetectors) {
                    doorDetector.setHasSentAlert(false);
                }
                // Reset all timers and states of the Active Thread
                activeThread.setCallPoliceTimer(0);
                activeThread.setSoundAlarmTimer(0);
                activeThread.setHasSoundedAlarm(false);
                activeThread.setHasCalledPolice(false);
                // Reset central units state
                soundAlarmTimerStarted = false;
                callPoliceTimerStarted = false;
            } else {
                System.out.println("Meddelande: Larmet har redan gått, du måste återställa!");
            }
        } else {
            System.out.println("Meddelande: Larmet är redan inaktiverat!");
        }
    }

    // Method resetAlarmSystem is called to turn off the system and reset all objects to
    // their default state.
    public void resetAlarmSystem() {
        if(siren.isMakingSound()) {
            setActive(false);
            // Turn of the siren
            siren.stopPreventiveAction();
            // Reset state of house components
            for(Room room : estate.getRooms()) {
                if(room.isOnFire()) {
                    room.setOnFire(false);
                }
                if(room.getFireSprinkler().isTakingAction()) {
                    room.getFireSprinkler().stopPreventiveAction();
                }
                for(Window window : room.getWindows()) {
                    if(window.isOpen()) {
                        window.closeWindow();
                    }
                }
                for(Door door : room.getDoors()) {
                    if(door.isOpen()) {
                        door.closeDoor();
                    }
                    if(door.isLocked()) {
                        door.unlockDoor();
                    }
                }
            }
            for(OutdoorEnvironment outdoorEnvironment : estate.getOutdoorEnvironments()) {
                if(outdoorEnvironment.isHasLargeMassIntruder()) {
                    outdoorEnvironment.setHasLargeMassIntruder(false);
                }
            }
            for(DoorDetector doorDetector : doorDetectors) {
                doorDetector.setHasSentAlert(false);
            }
            for(WindowDetector windowDetector : windowDetectors) {
                windowDetector.setHasSentAlert(false);
            }
            for(SmokeDetector smokeDetector : smokeDetectors) {
                smokeDetector.setHasSentAlert(false);
            }
            for(OutdoorMotionDetector outdoorMotionDetector : outdoorMotionDetectors) {
                outdoorMotionDetector.setHasSentAlert(false);
            }
            for(PhoneWithAlarmButton phoneWithAlarmButton : phoneWithAlarmButtons) {
                phoneWithAlarmButton.setAlarmButtonPressed(false);
                phoneWithAlarmButton.setHasSentAlert(false);
            }
            for(AlarmButton alarmButton : alarmButtons) {
                alarmButton.setAlarmButtonPressed(false);
                alarmButton.setHasSentAlert(false);
            }
            // Reset all timers and states of the Active Thread
            activeThread.setCallPoliceTimer(0);
            activeThread.setSoundAlarmTimer(0);
            activeThread.setHasSoundedAlarm(false);
            activeThread.setHasCalledPolice(false);
            // Reset central units state and turn off
            soundAlarmTimerStarted = false;
            callPoliceTimerStarted = false;
        } else {
            System.out.println("Meddelande: Du kan bara återställa under pågående larm!");
        }
    }
}


