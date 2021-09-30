/*  Inlämningsuppgift - Objektorienterad programmering 2021
*   @author Marcus Friberg
*   @author marcus.friberg@edu.edugrade.se
*/

package com.marcusfriberg;
// Imports
import com.marcusfriberg.alarm.ActiveThread;
import com.marcusfriberg.alarm.CentralUnit;
import com.marcusfriberg.estate.Estate;
import com.marcusfriberg.estate.OutdoorEnvironment;
import com.marcusfriberg.estate.Room;

// Class Main, program will start here.
public class Main {
    // Main method, creates an estate, a central unit and calls configureSystem to set up the rooms
    // and alarm components and finally calls showSummary to print out the configuration.
    public static void main(String[] args) {
        // Create a new estate, room and garden objects will be part of the estate as well as the central unit.
        Estate estate = new Estate("Tomas Wigell", "Gräddhyllan 1", 81122, "Öregrund");

        // Create a new central unit and connect it to the estate created above.
        CentralUnit centralUnit = new CentralUnit("Centralenheten", estate, 20);

        // Call method configureSystem to create estate, central unit, rooms and outdoor environments
        configureSystem(estate, centralUnit);

        // Call method showSummary to print a detailed summary of the created objects
        showSummary(estate, centralUnit);

        ActiveThread activeThread = new ActiveThread(centralUnit);
        activeThread.start();

        // Create a new testObject that can run different tests
        TestAlarm tester = new TestAlarm(estate, centralUnit);
        tester.prepareTest();

        // Create a new MenuViewController-object that will present menu's and handle user input.
        MenuViewController viewController = new MenuViewController(estate, centralUnit, tester);
        viewController.presentMainMenu();
    }

    // Method configureSystem, edit this to create fewer of more rooms.
    // Creates rooms in the estate with supplied number of windows and doors, each room will be
    // configured with smoke detector, fire sprinkler and phone with alarm button connected to
    // the central unit.
    // The provided number of windows and doors will be created within the room-class.
    // The window and door-classes will create the suitable alarm component and connect it
    // to the central unit.
    public static void configureSystem(Estate estate, CentralUnit centralUnit) {
        Room room1 = new Room("master bedroom", 4, 0, true, estate);
        Room room2 = new Room("sovrum 2", 2, 0,false, estate);
        Room room3 = new Room("sovrum 3", 2, 0, false, estate);
        Room room4 = new Room("sovrum 4", 2, 0, false, estate);
        Room room5 = new Room("sovrum 5", 2, 0, false, estate);
        Room room6 = new Room("toalett övervåning", 1, 0, false, estate);
        Room room7 = new Room("badrum", 1, 0, false, estate);
        Room room8 = new Room("vardagsrum", 3, 0, false, estate);
        Room room9 = new Room("kök", 3, 1, false, estate);
        Room room10 = new Room("matsal", 4, 0, false, estate);
        Room room11 = new Room("allrum", 2, 0, false, estate);
        Room room12 = new Room("toalett nedervåning", 1, 0, false, estate);
        Room room13 = new Room("entre", 1, 1, false, estate);
        Room room14 = new Room("källare", 3, 1, false, estate);
        Room room15 = new Room("garage", 1, 3, false, estate);

        // Create gardens belonging to the estate. Each garden will get an outdoor motion detector
        // attached and connected to the central unit.
        OutdoorEnvironment outdoorEnvironment1 = new OutdoorEnvironment("trädgården", estate);
        OutdoorEnvironment outdoorEnvironment2 = new OutdoorEnvironment("uteköket", estate);
    }

    // Shows a summary of the installed components, central unit and siren are usually
    // only 1 per estate but the amount can be adjusted manually below. Other components
    // are counted.
    public static void showSummary(Estate estate, CentralUnit centralUnit) {
        System.out.println("----------------------------");
        System.out.println("Larmsystem installerat med:");
        System.out.println("1 centralenhet");
        System.out.println("1 siren");
        System.out.println(centralUnit.getWindowDetectors().size() + " fönsterdetektorer");
        System.out.println(centralUnit.getDoorDetectors().size() + " dörrdetektorer");
        System.out.println(centralUnit.getSmokeDetectors().size() + " rökdetektorer");
        System.out.println(centralUnit.getFireSprinklers().size() + " vattensprinklers");
        System.out.println(centralUnit.getOutdoorMotionDetectors().size() + " utomhuskameror");
        System.out.println(centralUnit.getPhoneWithAlarmButtons().size() + " telefoner med larmknapp");
        System.out.println(centralUnit.getAlarmButtons().size() + " larmknappar");
        System.out.println("Fördelat på: " + estate.getRooms().size() + " rum & " + estate.getOutdoorEnvironments().size() + " utomhusmiljöer.");
    }
}