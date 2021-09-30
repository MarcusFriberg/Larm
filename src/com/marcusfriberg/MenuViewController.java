package com.marcusfriberg;
// Imports
import com.marcusfriberg.alarm.CentralUnit;
import com.marcusfriberg.estate.Estate;

import java.util.Scanner;

// Presents menu's and handles user input.
public class MenuViewController {
    // Variables
    private final Estate estate;
    private CentralUnit centralUnit;
    private TestAlarm tester;
    private Scanner scanner = new Scanner(System.in);

    // Constructor
    public MenuViewController(Estate estate, CentralUnit centralUnit, TestAlarm tester) {
        this.estate = estate;
        this.centralUnit = centralUnit;
        this.tester = tester;
    }

    // View-methods
    public void presentMainMenu() {
        System.out.println("-------------------- Huvudmeny --------------------");
        System.out.println("Ägare: " + estate.getOwnerName() + ", " + estate.getAdress() + ", " + estate.getZip() + ", " + estate.getCity());
        System.out.println("Status: Larmet är " + (centralUnit.isActive() ? "aktiverat." : "inaktiverat."));
        System.out.println("Val: [1] Aktivera larmet,   [2] Avaktivera larmet");
        System.out.println("     [3] Återställ larmet,  [4] Testa larmet");
        System.out.println("     [5] Avsluta det här programmet");
        System.out.println("---------------------------------------------------");
        System.out.println("Gör ett val");
        getMainMenuInput();
    }

    public void presentTestMenu() {
        System.out.println("------------------------ Testmeny ------------------------");
        System.out.println("Info: Efter valt test flyttas du tillbka till huvudmenyn.");
        System.out.println("Val: [1] Öppna en slumpvis vald ytterdörr i huset");
        System.out.println("     [2] Öppna ett slumpvis valt fönster i huset");
        System.out.println("     [3] Starta en brand i ett slumpis valt rum i huset");
        System.out.println("     [4] Locka in en tjuv i slumpivs vald utomhusmiljö");
        System.out.println("     [5] Tryck på larmknappen på slumpvis vald telefon");
        System.out.println("     [6] Tryck på slumpvis valt överfallslarm (ej telefon)");
        System.out.println("     [7] Återgå till huvudmeny");
        System.out.println("----------------------------------------------------------");
        System.out.println("Gör ett val");
        getTestMenuInput();
    }

    // Controller-methods
    public void getMainMenuInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine());
            switch (value) {
                case 1 :
                    centralUnit.turnOnAlarmSystem();
                    presentMainMenu();
                    break;
                case 2 :
                    centralUnit.turnOffAlarmSystem();
                    presentMainMenu();
                    break;
                case 3 :
                    centralUnit.resetAlarmSystem();
                    presentMainMenu();
                    break;
                case 4 :
                    presentTestMenu();
                    break;
                case 5 :
                    System.exit(0);
                    break;
                default:
                    System.out.println("Meddelande: Ogiltigt tal, använd 1, 2, 3 eller 4 för val i huvudmenyn");
                    presentMainMenu();
                    break;
            }
        } catch (Exception e) {
            System.out.println("ERROR: Ogiltigt värde, använd siffrorna 1, 2, 3 eller 4 för val i huvudmenyn");
        }
    }

    public void getTestMenuInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine());
            switch (value) {
                case 1 :
                    tester.testOpenRandomDoor();
                    presentMainMenu();
                    break;
                case 2 :
                    tester.testOpenRandomWindow();
                    presentMainMenu();
                    break;
                case 3 :
                    tester.testSetRandomRoomOnFire();
                    presentMainMenu();
                    break;
                case 4 :
                    tester.testSpawningBurglarInRandomOutdoorEnvironment();
                    presentMainMenu();
                    break;
                case 5 :
                    tester.testPressingRandomPhonesAlarmButton();
                    presentMainMenu();
                    break;
                case 6 :
                    tester.testPressingRandomAlarmButton();
                    presentMainMenu();
                    break;
                case 7 :
                    presentMainMenu();
                    break;
                default:
                    System.out.println("Meddelande: Ogiltigt tal, använd 1, 2, 3 eller 4 för val i huvudmenyn");
                    presentMainMenu();
                    break;
            }
        } catch (Exception e) {
            System.out.println("ERROR: Ogiltigt värde, använd siffrorna 1, 2, 3 eller 4 för val i huvudmenyn");
        }
    }

}
