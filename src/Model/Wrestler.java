package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Wrestler
{

    private int usawID;
    private String wrestlerName;
    private String ageGroup;
    private String parentName;
    private String email;
    private String phone;
    private String address;
    private int zip;
    private LocalDate dob;
    private String gender;
    private int id;
    private String notes;


    private static ObservableList<Wrestler> DBwrestlerObservableList = FXCollections.observableArrayList();

    public ObservableList<Wrestler> filteredWrestlerList = FXCollections.observableArrayList();


    public static Wrestler tempWrestler;


    public Wrestler(int usawID, String wrestlerName, String ageGroup, String parentName, String email, String phone, String address, int zip, LocalDate dob, String gender, int id, String notes) {
        this.usawID = usawID;
        this.wrestlerName = wrestlerName;
        this.ageGroup = ageGroup;
        this.parentName = parentName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.zip = zip;
        this.dob = dob;
        this.gender = gender;
        this.id = id;
        this.notes = notes;
    }

    public static void toEdit(Wrestler wrestler) {
        if (DBwrestlerObservableList.contains(wrestler)) {
            tempWrestler = wrestler;
        }
    }

    public static ObservableList<Wrestler> getListByAgeGroup(String ageGroup) {
        ObservableList<Wrestler> wrestlerFromGroup = FXCollections.observableArrayList();

        for (Wrestler wrestler : getDBwrestlerObservableList()) {
            if (wrestler.getAgeGroup().equals(ageGroup)) {
                wrestlerFromGroup.add(wrestler);

            }
        }
        return wrestlerFromGroup;
    }


    public static ObservableList<Wrestler> lookupWrestler(String name) {
        ObservableList<Wrestler> filteredWrestlerList = FXCollections.observableArrayList();

        for (Wrestler wrestler : Wrestler.getDBwrestlerObservableList()) {
            if (wrestler.getWrestlerName().toLowerCase().contains(name.toLowerCase())) {
                filteredWrestlerList.add(wrestler);
            }
        }
        return filteredWrestlerList;
    }





    public int getUsawID() {
        return usawID;
    }

    public String getWrestlerName() {
        return wrestlerName;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getParentName() {
        return parentName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getZip() {
        return zip;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public static ObservableList<Wrestler> getDBwrestlerObservableList() {
        return DBwrestlerObservableList;
    }

}
