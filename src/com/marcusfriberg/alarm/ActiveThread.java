package com.marcusfriberg.alarm;

public class ActiveThread extends Thread {
    // Variables
    private CentralUnit centralUnit;
    private int callPoliceTimer = 0;
    private int soundAlarmTimer = 0;
    private boolean hasSoundedAlarm = false;
    private boolean hasCalledPolice = false;

    // Constructor
    public ActiveThread(CentralUnit centralUnit) {
        this.centralUnit = centralUnit;
        centralUnit.setActiveThread(this);
    }

    // Run-method
    public void run()
    {
        setPriority(Thread.MAX_PRIORITY);
        // Tråden får köras hela tiden, i en tänkt fortsättning på programmet
        // skulle jag bryta loss smokeDetectors från getStatusFromDetectors så att
        // brandlarmet alltid är aktivit per default och kräva att enstaka rum
        // deaktiveras ett bestämt antal minuter om herr Wigell exempelvis behöver
        // röka en segercigarr.
        while(true) {
            // Tell the central unit to send a request to all detectors each second
            // while the alarm system is turned on.
            if(centralUnit.isActive()) {
                centralUnit.getStatusFromDetectors();

                // Check if the central unit has requested a timer to delay
                // the soundTheAlarm method and if so, start a timer and sound
                // the alarm if it has not been turned of within 15 seconds.
                if(centralUnit.isSoundAlarmTimerStarted()) {
                    if(soundAlarmTimer >= 15 && !hasSoundedAlarm) {
                        centralUnit.soundTheAlarm();
                        hasSoundedAlarm = true;
                    } else {
                        soundAlarmTimer ++;
                    }

                }

                // Check if the central unit has requested a timer to delay
                // the callThePolice method and if so, start a timer and
                // call the police if the alarm has not been reset within the given time.
                if(centralUnit.isCallPoliceTimerStarted()) {
                    if(callPoliceTimer >= centralUnit.getCallPoliceDelay() && !hasCalledPolice) {
                        centralUnit.callThePolice();
                        hasCalledPolice = true;
                    } else {
                        callPoliceTimer ++;
                    }
                }
            }

            try {
                sleep(1000);
            } catch (Exception e) {
                System.out.println("Error in ActiveThread");
            }
        }
    }

    // Setters
    public void setCallPoliceTimer(int callPoliceTimer) {
        this.callPoliceTimer = callPoliceTimer;
    }

    public void setSoundAlarmTimer(int soundAlarmTimer) {
        this.soundAlarmTimer = soundAlarmTimer;
    }

    public void setHasSoundedAlarm(boolean hasSoundedAlarm) {
        this.hasSoundedAlarm = hasSoundedAlarm;
    }

    public void setHasCalledPolice(boolean hasCalledPolice) {
        this.hasCalledPolice = hasCalledPolice;
    }
}
