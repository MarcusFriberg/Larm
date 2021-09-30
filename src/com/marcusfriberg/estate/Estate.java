package com.marcusfriberg.estate;
// Imports
import com.marcusfriberg.alarm.CentralUnit;
import java.util.ArrayList;
import java.util.List;

// Class Estate, holding information about the rooms and outdoor environments
// belonging to the estate as well as the location and name of the owner.
public class Estate {
    // Variables
    private String ownerName;
    private String adress;
    private int zip;
    private String city;
    private List<Room> rooms = new ArrayList<Room>();
    private List<OutdoorEnvironment> outdoorEnvironments = new ArrayList<OutdoorEnvironment>();
    private CentralUnit centralUnit;

    // Constructor
    public Estate(String ownerName, String adress, int zip, String city) {
        this.ownerName = ownerName;
        this.adress = adress;
        this.zip = zip;
        this.city = city;
    }

    // Setters & Getters
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addOutdoorEnvironment(OutdoorEnvironment outdoorEnvironment) {
        outdoorEnvironments.add(outdoorEnvironment);
    }

    public List<OutdoorEnvironment> getOutdoorEnvironments() {
        return outdoorEnvironments;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCentralUnit(CentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }

    public CentralUnit getCentralUnit() {
        return this.centralUnit;
    }
}
