package Controller;

import Database.JDBC;
import Model.Wrestler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class AddWrestlerController implements Initializable {


    public TextField wrestlerNameText;
    @FXML
    private TextField idAdd;

    @FXML
    private TextField usawIText;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField parentNameText;

    @FXML
    private DatePicker dobDP;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField emailText;

    @FXML
    private ComboBox<String> ageGroupCB;

    @FXML
    private TextField notesText;

    @FXML
    private ComboBox<String> genderCB;

    @FXML
    private TextField addressText;

    @FXML
    private TextField zipText;
    Stage stage;
    Parent scene;
    PreparedStatement ps;
    Connection myConn = JDBC.getConnection();


    public void insertDB() throws SQLException {
        int usawID = Integer.parseInt(usawIText.getText());
        String wrestlerName = wrestlerNameText.getText();
        String ageGroup = ageGroupCB.getValue();
        String parentName = parentNameText.getText();
        String email = emailText.getText();
        String phone = phoneText.getText();
        String address = addressText.getText();
        int zip = Integer.parseInt(zipText.getText());
        LocalDate dob = dobDP.getValue();
        String gender = genderCB.getValue();
        String notes = notesText.getText();

        //                                       1         2              3          4         5       6       7     8     9     10      11
        String insert = "INSERT INTO roster (USAW_ID, Wrestler_Name, Age_Group, Parent_Name, Email, Phone, Address, Zip, DOB, Gender,  Notes) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        ps = myConn.prepareStatement(insert);
        ps.setInt(1, usawID);
        ps.setString(2, wrestlerName);
        ps.setString(3, ageGroup);
        ps.setString(4, parentName);
        ps.setString(5, email);
        ps.setString(6, phone);
        ps.setString(7, address);
        ps.setInt(8, zip);
        ps.setDate(9, Date.valueOf(dob));
        ps.setString(10, gender);
        ps.setString(11, notes);

        ps.executeUpdate();
    }

    public void updateDB() throws SQLException {
        int usawID = Integer.parseInt(usawIText.getText());
        String wrestlerName = wrestlerNameText.getText();
        String ageGroup = ageGroupCB.getValue();
        String parentName = parentNameText.getText();
        String email = emailText.getText();
        String phone = phoneText.getText();
        String address = addressText.getText();
        int zip = Integer.parseInt(zipText.getText());
        LocalDate dob = dobDP.getValue();
        String gender = genderCB.getValue();
        String notes = notesText.getText();


        //                                 //1             2                3          4          5         6         7       8      9      10        11           12
        String update = "UPDATE roster SET USAW_ID = ?,Wrestler_Name=?,Age_Group=?,Parent_Name=?,Email=?, Phone=?, Address=?, Zip=?, DOB=?,Gender=?, Notes=? WHERE ID=?;";
        ps = myConn.prepareStatement(update);
        ps.setInt(1, usawID);
        ps.setString(2, wrestlerName);
        ps.setString(3, ageGroup);
        ps.setString(4, parentName);
        ps.setString(5, email);
        ps.setString(6, phone);
        ps.setString(7, address);
        ps.setInt(8, zip);
        ps.setDate(9, Date.valueOf(dob));
        ps.setString(10, gender);
        ps.setString(11, notes);
        ps.setInt(12, Wrestler.tempWrestler.getId());

        Wrestler.tempWrestler = null;
        ps.executeUpdate();
    }


    @FXML
    void saveWrestlerBtn(ActionEvent event) throws SQLException, IOException {

        if (Wrestler.tempWrestler != null) {

            updateDB();

        } else {

            insertDB();

        }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainMenuView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @FXML
    void cancelWrestlerBtn(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainMenuView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> genderList = FXCollections.observableArrayList("M", "F");
        ObservableList<String> ageGroupList = FXCollections.observableArrayList("8U", "10U", "12U", "14U", "16U", "18U");

        ageGroupCB.setItems(ageGroupList);
        genderCB.setItems(genderList);

        // Used for editing/ modify
        if (Wrestler.tempWrestler != null) {
            Wrestler wrestler = Wrestler.tempWrestler;

            usawIText.setText(String.valueOf(wrestler.getUsawID()));
            wrestlerNameText.setText(wrestler.getWrestlerName());
            parentNameText.setText(wrestler.getParentName());
            ageGroupCB.setValue(wrestler.getAgeGroup());
            dobDP.setValue(wrestler.getDob());
            emailText.setText(wrestler.getEmail());
            phoneText.setText(wrestler.getPhone());
            genderCB.setValue(wrestler.getGender());
            notesText.setText(wrestler.getNotes());
            idAdd.setText(String.valueOf(wrestler.getId()));

            // clears java list out but maintains tempwrestler
            Wrestler.tempWrestler = null;

        }


    }

}
