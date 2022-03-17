package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Parent1 {

    private String p1name;
    private String p1email;
    private String p1phone;
    private String p1address;
    private int p1zip;
    private int p1WrestlerId;

    private static ObservableList<Parent1> DBparent1ObservableList = FXCollections.observableArrayList();
    public static Parent1 tempParent1;

    public Parent1(String p1name, String p1email, String p1phone, String p1address, int p1zip, int p1ID) {
        this.p1name = p1name;
        this.p1email = p1email;
        this.p1phone = p1phone;
        this.p1address = p1address;
        this.p1zip = p1zip;
        this.p1WrestlerId = p1ID;
    }


    public static void parent1ToEdit(int wrestlerID) {

        for (Parent1 parent: getDBparent1ObservableList()){
            if (parent.p1WrestlerId == wrestlerID){
                tempParent1 = parent;
            }
        }
    }

    public static ObservableList<Parent1> getDBparent1ObservableList() {
        return DBparent1ObservableList;
    }

    public String getP1name() {
        return p1name;
    }

    public String getP1email() {
        return p1email;
    }

    public String getP1phone() {
        return p1phone;
    }

    public String getP1address() {
        return p1address;
    }

    public int getP1zip() {
        return p1zip;
    }

    public int getP1WrestlerId() {
        return p1WrestlerId;
    }
}
