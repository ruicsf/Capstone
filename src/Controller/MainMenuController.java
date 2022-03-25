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
    public TableColumn rosterAddressCol;
    public TableColumn rosterZipCol;
    public TableColumn notesCol;
    Stage stage;
    Parent scene;
    ResourceBundle rs;
    PreparedStatement ps;
    Connection myConn = JDBC.getConnection();

    @FXML
    private TableView rosterTable;

    @FXML
    private TableColumn<?, ?> rosterUsawIdCol;

    @FXML
    private TableColumn<?, ?> rosterFirstNameCol;

    @FXML
    private TableColumn<?, ?> rosterAageGroupCol;

    @FXML
    private TableColumn<?, ?> rosterParentNameCol;

    @FXML
    private TableColumn<?, ?> rosterEmailCol;

    @FXML
    private TableColumn<?, ?> rosterPhoneCol;


    @FXML
    private TableColumn<?, ?> rosterDobCol;

    @FXML
    private TableColumn<?, ?> rosterGenderCol;

    @FXML
    private TableColumn<?, ?> rosterIdCol;

    @FXML
    private TableColumn<?, ?> rosterNotes;

    @FXML
    private TextField wrestlerSearch;




    @FXML
    void logoutOnBtn(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/LoginView.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void reportsOnBtn(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/ReportsView.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void rosterDeleteOnActionBtn(ActionEvent event) throws SQLException {
        Wrestler selectedWrestler = (Wrestler) rosterTable.getSelectionModel().getSelectedItem();

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
            alertConfirmation.setContentText("Press OK to confirm deletion of " + selectedWrestler.getWrestlerName());
            Optional<ButtonType> confirm = alertConfirmation.showAndWait();
            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                Wrestler.getDBwrestlerObservableList().remove(selectedWrestler);
                int wrestlerId = selectedWrestler.getId();

                ps = myConn.prepareStatement("DELETE FROM roster WHERE ID = ?");
                ps.setInt(1, wrestlerId);
                ps.executeUpdate();
                System.out.println("Wrestler Deleted");

            }
        }

    }

    @FXML
    void rosterModifyOnActionBtn(ActionEvent event) {
        try {
            Wrestler wrestler = (Wrestler) rosterTable.getSelectionModel().getSelectedItem();
            Wrestler.toEdit(wrestler);

            Parent1.parent1ToEdit(wrestler.getId());
            Parent2.parent2ToEdit(wrestler.getId());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../View/AddWrestlerView.fxml"));
            stage.setTitle("Edit wrestler");
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Selection");
            alert.setContentText("Please make a selection");
            alert.showAndWait();
        }
    }


    @FXML
    void wrestlerSearchOnAction(ActionEvent event) {

        if (wrestlerSearch.getText().isEmpty()){
            rosterTable.setItems(Wrestler.getDBwrestlerObservableList());
        }else{
            rosterTable.setItems(Wrestler.lookupWrestler(wrestlerSearch.getText()));
        }
    }


    @FXML
    void rosterAddOnActionBtn(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddWrestlerView.fxml"));
        stage.setTitle("Add Wrestler");
        stage.setScene(new Scene(scene));
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JDBC.mysqlDatabase = new JDBC();
        JDBC.mysqlDatabase.getDatabaseRoster();
        JDBC.mysqlDatabase.getDBParent1List();
        JDBC.mysqlDatabase.getDBParent2List();


        rosterUsawIdCol.setCellValueFactory(new PropertyValueFactory<>("usawID"));
        rosterFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("wrestlerName"));
        rosterAageGroupCol.setCellValueFactory(new PropertyValueFactory<>("ageGroup"));
        rosterParentNameCol.setCellValueFactory(new PropertyValueFactory<>("parentName"));
        rosterEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        rosterPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        rosterAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        rosterZipCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
        rosterDobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        rosterGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        rosterIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        notesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));


        rosterTable.setItems(Wrestler.getDBwrestlerObservableList());



    }


}