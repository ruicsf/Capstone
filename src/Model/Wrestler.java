package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Wrestler {

    private int memberId;
    private int usawId;
    private String firstName;
    private String lastName;
    private String ParentName;
    private LocalDate dateOfBirth;
    private String ageGroup;
    private String school;
    private String phone;
    private String gender;
    private String email;
    private String rosterNotes;


    private static ObservableList<Wrestler> wrestlerObservableList = FXCollections.observableArrayList();

    public static ObservableList<Wrestler> getWrestlerObservableList() {
        return wrestlerObservableList;
    }

    public static Wrestler tempWrestler;


    public Wrestler(int databaseId, int usawId, String firstName, String lastName, String parentName, LocalDate dateOfBirth, String ageGroup, String school, String phone, String gender, String email, String rosterNotes) {
        this.memberId = databaseId;
        this.usawId = usawId;
        this.firstName = firstName;
        this.lastName = lastName;
        ParentName = parentName;
        this.dateOfBirth = dateOfBirth;
        this.ageGroup = ageGroup;
        this.school = school;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.rosterNotes = rosterNotes;
    }

    public static void toEdit(Wrestler wrestler) {
        if (wrestlerObservableList.contains(wrestler)) {
           tempWrestler  = wrestler;
        }
    }


    public int getMemberId() {
        return memberId;
    }

    public int getUsawId() {
        return usawId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getParentName() {
        return ParentName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getSchool() {
        return school;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getRosterNotes() {
        return rosterNotes;
    }
}
