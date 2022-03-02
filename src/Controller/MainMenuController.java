package Controller;

import Database.JDBC;
import Model.Wrestler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    Stage stage;
    Parent scene;
    ResourceBundle rs;
    PreparedStatement ps;
    Connection myConn = JDBC.getConnection();

    public TableColumn rosterGenderCol;
    public TableColumn rosterIdCol;
    @FXML
    private Button logoutBtn;

    @FXML
    private Button reportsBtn;

    @FXML
    private TableView<Wrestler> rosterTable;

    @FXML
    private TableColumn<?, ?> rosterUsawIdCol;

    @FXML
    private TableColumn<?, ?> rosterFirstNameCol;

    @FXML
    private TableColumn<?, ?> rosterLastNameCol;

    @FXML
    private TableColumn<?, ?> rosterParentNameCol;

    @FXML
    private TableColumn<?, ?> rosterAageGroupCol;

    @FXML
    private TableColumn<?, ?> rosterDobCol;

    @FXML
    private TableColumn<?, ?> rosterEmailCol;

    @FXML
    private TableColumn<?, ?> rosterPhoneCol;

    @FXML
    private TableColumn<?, ?> rosterSchoolCol;

    @FXML
    private TableColumn<?, ?> rosterDatabaseIdCol;

    @FXML
    private ToggleGroup views;

    @FXML
    private TextField wrestlerSearch;

    @FXML
    private TableView<?> praentTable;

    @FXML
    private TableColumn<?, ?> parentFirstNameCol;

    @FXML
    private TableColumn<?, ?> parentLastName;

    @FXML
    private TableColumn<?, ?> parentWrestlerNameCol;

    @FXML
    private TableColumn<?, ?> parentEmailCol;

    @FXML
    private TableColumn<?, ?> parentPhoneCol;

    @FXML
    private TableColumn<?, ?> parentAddresCol;

    @FXML
    private TableColumn<?, ?> parentZipCol;

    @FXML
    private TableColumn<?, ?> databseIdCol;

    @FXML
    private TextField parentSearch;

    @FXML
    void logoutOnBtn(ActionEvent event) {

    }


    @FXML
    void parentDeleteBtn(ActionEvent event) {

    }

    @FXML
    void parentModifyBtn(ActionEvent event) {

    }

    @FXML
    void parentSearchOnAction(ActionEvent event) {

    }

    @FXML
    void reportsOnBtn(ActionEvent event) {

    }

    @FXML
    void rosterDeleteOnActionBtn(ActionEvent event) throws SQLException {
        Wrestler selectedWrestler = rosterTable.getSelectionModel().getSelectedItem();

        if (selectedWrestler == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Selection error");
            errorAlert.setContentText("Please make a selection");
            errorAlert.show();
        } else {
            Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmation.setTitle("Alert");
            alertConfirmation.setHeaderText("Deletion Warning");
            alertConfirmation.setContentText("Press OK to confirm deletion of " + selectedWrestler.getFirstName() +" "+selectedWrestler.getLastName());
            Optional<ButtonType> confirm = alertConfirmation.showAndWait();
            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                Wrestler.getWrestlerObservableList().remove(selectedWrestler);
                int wrestlerId = selectedWrestler.getMemberId();

                ps = myConn.prepareStatement("DELETE FROM roster WHERE Member_ID = ?");
                ps.setInt(1, wrestlerId);
                ps.executeUpdate();
                System.out.println("Wrestler Deleted");

            }
        }

    }

    @FXML
    void rosterModifyOnActionBtn(ActionEvent event) {
        try {
            Wrestler wrestler = rosterTable.getSelectionModel().getSelectedItem();
            Wrestler.toEdit(wrestler);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../View/AddWrestlerView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No appointment selected");
            alert.setContentText("Please select an appointment to modify");
            alert.showAndWait();
        }
    }


    @FXML
    void wrestlerSearchOnAction(ActionEvent event) {

    }


    @FXML
    void rosterAddOnActionBtn(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddWrestlerView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void parentAddBtn(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddParentView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JDBC.mysqlDatabase = new JDBC();
        JDBC.mysqlDatabase.getDatabaseRoster();

        rosterIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        rosterUsawIdCol.setCellValueFactory(new PropertyValueFactory<>("usawId"));
        rosterFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        rosterLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        rosterParentNameCol.setCellValueFactory(new PropertyValueFactory<>("parentName"));
        rosterDobCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        rosterAageGroupCol.setCellValueFactory(new PropertyValueFactory<>("ageGroup"));
        rosterSchoolCol.setCellValueFactory(new PropertyValueFactory<>("school"));
        rosterPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        rosterGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        rosterEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        rosterTable.setItems(Wrestler.getWrestlerObservableList());

    }


}