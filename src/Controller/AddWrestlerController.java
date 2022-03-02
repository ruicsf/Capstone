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


    public TextField notesAdd;
    @FXML
    private TextField idAdd;

    @FXML
    private TextField usawIdAdd;

    @FXML
    private TextField firstNameAdd;

    @FXML
    private TextField lastNameAdd;

    @FXML
    private TextField parentNameAdd;

    @FXML
    private DatePicker dobDPadd;

    @FXML
    private TextField schoolAdd;

    @FXML
    private TextField phoneAdd;

    @FXML
    private TextField emailAdd;

    @FXML
    private ComboBox<String> genderAddCB;

    @FXML
    private ComboBox<String> ageGroupCBadd;

    Stage stage;
    Parent scene;
    PreparedStatement ps;
    Connection myConn = JDBC.getConnection();

    @FXML
    void cancelWrestlerBtn(ActionEvent event) {


    }

    @FXML
    void saveWrestlerBtn(ActionEvent event) throws SQLException, IOException {

        int usawID = Integer.parseInt(usawIdAdd.getText());
        String firstName = firstNameAdd.getText();
        String lastName = lastNameAdd.getText();
        String parentName = parentNameAdd.getText();
        LocalDate dob = dobDPadd.getValue();
        String ageGroup = ageGroupCBadd.getValue();
        String school = schoolAdd.getText();
        String phone = phoneAdd.getText();
        String gender = genderAddCB.getValue();
        String email = emailAdd.getText();
        String notes = notesAdd.getText();

        if (Wrestler.tempWrestler != null){
                                                //1            2            3           4             5        6      7       8       9        10        11             12
            String update = "UPDATE roster SET USAW_ID = ?,First_Name=?,Last_Name=?,Parent_Name=?,Age_Group=?,DOB=?,Email=?,Phone=?,School=?,Gender=?, Notes=? WHERE Member_ID=?;";
            ps = myConn.prepareStatement(update);
            ps.setInt(1, usawID);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, parentName);
            ps.setString(5, ageGroup);
            ps.setDate(6, Date.valueOf(dob));
            ps.setString(7, email);
            ps.setString(8, phone);
            ps.setString(9, school);
            ps.setString(10, gender);
            ps.setString(11, notes);
            ps.setInt(12, Wrestler.tempWrestler.getMemberId());

            Wrestler.tempWrestler = null;
            ps.executeUpdate();

        }else{
            // 1        2               3       4           5          6    7       8   9          10     11
            String insert = "INSERT INTO roster (USAW_ID, First_Name, Last_Name, Parent_Name, Age_Group, DOB, Email, Phone, School, Gender, Notes) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            ps = myConn.prepareStatement(insert);
            ps.setInt(1, usawID);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, parentName);
            ps.setString(5, ageGroup);
            ps.setDate(6, Date.valueOf(dob));
            ps.setString(7, email);
            ps.setString(8, phone);
            ps.setString(9, school);
            ps.setString(10, gender);
            ps.setString(11, notes);

            ps.executeUpdate();
        }

        
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainMenuView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> genderList = FXCollections.observableArrayList("M", "F");
        ObservableList<String> ageGroupList = FXCollections.observableArrayList("8U", "10U", "12U", "14U", "16U", "18U");

        ageGroupCBadd.setItems(ageGroupList);
        genderAddCB.setItems(genderList);

        if (Wrestler.tempWrestler != null){
            Wrestler wrestler = Wrestler.tempWrestler;

            usawIdAdd.setText(String.valueOf(wrestler.getUsawId()));
            firstNameAdd.setText(wrestler.getFirstName());
            lastNameAdd.setText(wrestler.getLastName());
            parentNameAdd.setText(wrestler.getParentName());
            ageGroupCBadd.setValue(wrestler.getAgeGroup());
            dobDPadd.setValue(wrestler.getDateOfBirth());
            emailAdd.setText(wrestler.getEmail());
            phoneAdd.setText(wrestler.getPhone());
            schoolAdd.setText(wrestler.getSchool());
            genderAddCB.setValue(wrestler.getGender());
            notesAdd.setText(wrestler.getRosterNotes());
            int wrestlerId = wrestler.getMemberId();
            idAdd.setText(String.valueOf(wrestlerId));

        }










    }
}
