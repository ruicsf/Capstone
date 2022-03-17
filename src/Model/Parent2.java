package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Parent2 extends Parent1 {

    private static ObservableList<Parent2> DBparent2ObservableList = FXCollections.observableArrayList();
    public static Parent2 tempParent2;

    public static ObservableList<Parent2> getDBparent2ObservableList() {
        return DBparent2ObservableList;
    }

    public Parent2(String p1name, String p1email, String p1phone, String p1address, int p1zip, int p1ID) {
        super(p1name, p1email, p1phone, p1address, p1zip, p1ID);
    }

    public static void parent2ToEdit(int wrestlerID) {

        for (Parent2 parent: getDBparent2ObservableList()){
            if (parent.getP1WrestlerId() == wrestlerID){
                tempParent2 = parent;
            }
        }
    }

}
