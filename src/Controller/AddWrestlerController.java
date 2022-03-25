package Controller;

import Database.JDBC;
import Model.Parent1;
import Model.Parent2;
import Model.Wrestler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class AddWrestlerController implements Initializable {


    public TextField wrestlerNameText;
    public TextField p1PhoneText;
    public TextField p1EmailText;
    public TextField p2NameText;
    public TextField p2AddressText;
    public TextField p2ZipText;
    public TextField p2EmailText;
    public TextField p2PhoneText;
    @FXML
    private TextField idAdd;

    @FXML
    private TextField usawIText;

    @FXML
    private TextField p1NameText;

    @FXML
    private DatePicker dobDP;
    @FXML
    private ComboBox<String> ageGroupCB;

    @FXML
    private TextField notesText;

    @FXML
    private ComboBox<String> genderCB;

    @FXML
    private TextField p1AddressText;

    @FXML
    private TextField p1ZipText;
    Stage stage;
    Parent scene;
    PreparedStatement ps;
    Connection myConn = JDBC.getConnection();


    public void insertDB() throws SQLException {
        int zip = 0;
        int p2Zip = 0;
        int usawID = Integer.parseInt(usawIText.getText());
        String wrestlerName = wrestlerNameText.getText();
        String ageGroup = ageGroupCB.getValue();
        String parentName = p1NameText.getText();
        String email = p1EmailText.getText();
        String phone = p1PhoneText.getText();
        String address = p1AddressText.getText();
        if (!p1ZipText.getText().isEmpty()){
            zip = Integer.parseInt(p1ZipText.getText());
        }
        LocalDate dob = dobDP.getValue();
        String gender = genderCB.getValue();
        String notes = notesText.getText();
        String p2Name = p2NameText.getText();
        String p2Phone = p2PhoneText.getText();
        String p2Address = p2AddressText.getText();
        String p2Email= p2EmailText.getText();
        if (!p2ZipText.getText().isEmpty()){
             p2Zip =Integer.parseInt(p2ZipText.getText());
        }



        //                                       1         2              3          4         5             6       7          8     9     10      11      12          13      14          15        16
        String insert = "INSERT INTO roster (USAW_ID, Wrestler_Name, Age_Group, Parent1_Name, P1_Email, P1_Phone, P1_Address, P1_Zip, DOB, Gender,  Notes, P2_Name, P2_Phone, P2_Email, P2_Address, P2_Zip) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
        ps.setString(12, p2Name);
        ps.setString(13, p2Phone);
        ps.setString(14, p2Email);
        ps.setString(15, p2Address);
        ps.setInt(16, p2Zip);

        ps.executeUpdate();
    }

    public void updateDB() throws SQLException {
        int zip = 0;
        int p2Zip = 0;
        int usawID = Integer.parseInt(usawIText.getText());
        String wrestlerName = wrestlerNameText.getText();
        String ageGroup = ageGroupCB.getValue();
        String parentName = p1NameText.getText();
        String email = p1EmailText.getText();
        String phone = p1PhoneText.getText();
        String address = p1AddressText.getText();
        if (!p1ZipText.getText().isEmpty()){
            zip = Integer.parseInt(p1ZipText.getText());
        }
        LocalDate dob = dobDP.getValue();
        String gender = genderCB.getValue();
        String notes = notesText.getText();
        String p2Name = p2NameText.getText();
        String p2Phone = p2PhoneText.getText();
        String p2Address = p2AddressText.getText();
        String p2Email= p2EmailText.getText();
        if (!p2ZipText.getText().isEmpty()){
            p2Zip =Integer.parseInt(p2ZipText.getText());
        }


        //                                 //1             2                3          4            5              6         7               8      9      10        11           12       13          14          15          16            17
        String update = "UPDATE roster SET USAW_ID = ?,Wrestler_Name=?,Age_Group=?,Parent1_Name=?,P1_Email=?, P2_Phone=?, P2_Address=?, P1_Zip=?, DOB=?,Gender=?, Notes=?, P2_Name=?, P2_Phone=?, P2_Email=?, P2_Address=?, P2_Zip=? WHERE ID=?;";
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
        ps.setString(12, p2Name);
        ps.setString(13, p2Phone);
        ps.setString(14, p2Email);
        ps.setString(15, p2Address);
        ps.setInt(16, p2Zip);

        ps.setInt(17, Wrestler.tempWrestler.getId());


        ps.executeUpdate();
        Wrestler.tempWrestler = null;
        Parent2.tempParent1 = null;
        Parent2.tempParent2 = null;
    }

    public boolean checkForBlankFields() {
        if (usawIText.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Blank Text Field");
            a.setHeaderText("USAW ID no entry");
            a.setContentText("Please enter the wrestler's USAW ID");
            a.showAndWait();
            return false;
        }
        if (wrestlerNameText.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Blank Text Field");
            a.setHeaderText("Name text field blank");
            a.setContentText("Please enter the wrestler's name");
            a.showAndWait();
            return false;
        }
        if (dobDP.getValue()== null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Selection error ");
            a.setHeaderText("Date of birth not selected");
            a.setContentText("Please select the wrestler's date of birth");
            a.showAndWait();
            return false;
        }
        if (ageGroupCB.getValue().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Selection error");
            a.setHeaderText("Age group not selected");
            a.setContentText("Please select the wrestler's age group");
            a.showAndWait();
            return false;
        }


        return true;
    }

    @FXML
    void saveWrestlerBtn(ActionEvent event) throws SQLException, IOException {
        if (checkForBlankFields()){
            if (Wrestler.tempWrestler != null) {

                updateDB();
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("../View/MainMenuView.fxml"));
                stage.setTitle("Home");
                stage.setScene(new Scene(scene));
                stage.show();
            } else {

                insertDB();
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("../View/MainMenuView.fxml"));
                stage.setTitle("Home");
                stage.setScene(new Scene(scene));
                stage.show();
            }

        }



    }


    @FXML
    void cancelWrestlerBtn(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainMenuView.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Home");
        stage.show();
        Wrestler.tempWrestler = null;
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
            p1NameText.setText(Parent1.tempParent1.getP1name());
            ageGroupCB.setValue(wrestler.getAgeGroup());
            dobDP.setValue(wrestler.getDob());
            p1EmailText.setText(wrestler.getEmail());
            p1PhoneText.setText(wrestler.getPhone());
            p1AddressText.setText(Parent1.tempParent1.getP1address());
            p1ZipText.setText(String.valueOf(Parent1.tempParent1.getP1zip()));
            genderCB.setValue(wrestler.getGender());
            notesText.setText(wrestler.getNotes());
            idAdd.setText(String.valueOf(wrestler.getId()));
            p2NameText.setText(Parent2.tempParent2.getP1name());
            p2EmailText.setText(Parent2.tempParent2.getP1email());
            p2PhoneText.setText(Parent2.tempParent2.getP1phone());
            p2AddressText.setText(Parent2.tempParent2.getP1address());
            p2ZipText.setText(String.valueOf( Parent2.tempParent2.getP1zip()));



        }


    }

}
