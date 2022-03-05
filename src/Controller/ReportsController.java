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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    @FXML
    private TableView<Wrestler> rosterTable;

    @FXML
    private TableColumn<?, ?> rosterUsawIdCol;

    @FXML
    private TableColumn<?, ?> wrestlerNameCol;

    @FXML
    private TableColumn<?, ?> rosterAageGroupCol;

    @FXML
    private TableColumn<?, ?> rosterParentNameCol;

    @FXML
    private TableColumn<?, ?> rosterEmailCol;

    @FXML
    private TableColumn<?, ?> rosterPhoneCol;

    @FXML
    private TableColumn<?, ?> rosterAddressCol;

    @FXML
    private TableColumn<?, ?> rosterZipCol;

    @FXML
    private TableColumn<?, ?> rosterDobCol;

    @FXML
    private TableColumn<?, ?> rosterGenderCol;

    @FXML
    private TableColumn<?, ?> notesCol;

    @FXML
    private TableColumn<?, ?> rosterIdCol;

    @FXML
    private ComboBox<String> selectAgeGroupCB;

    @FXML
    private Label rosterSizeLbl;


    Stage stage;
    Parent scene;

    @FXML
    void exitOnBtn(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainMenuView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    RosterCounterInterface rosterCounter = () ->rosterSizeLbl.setText(String.valueOf(JDBC.mysqlDatabase.getDatabaseRoster()));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> ageGroupList =  FXCollections.observableArrayList("8U", "10U", "12U", "14U", "16U", "18U");

        selectAgeGroupCB.setItems(ageGroupList);

        rosterUsawIdCol.setCellValueFactory(new PropertyValueFactory<>("usawID"));
        wrestlerNameCol.setCellValueFactory(new PropertyValueFactory<>("wrestlerName"));
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

        selectAgeGroupCB.getSelectionModel().selectedItemProperty().addListener((v,o,newValue) ->rosterTable.setItems(Wrestler.getListByAgeGroup(newValue)));

        rosterSizeLbl.setText(String.valueOf(JDBC.mysqlDatabase.getDatabaseRoster()) );


    }
}
