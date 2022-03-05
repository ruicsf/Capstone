package Controller;

import Database.JDBC;
import Model.Wrestler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class AddParentController implements Initializable {
    public TextField wrestlerName;
    @FXML
    private TextField wrestlerId;
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField pName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField zip;
    @FXML
    private TextField notes;

    Stage stage;
    Parent scene;
    PreparedStatement ps;
    Connection myConn = JDBC.getConnection();

    Wrestler tempWrestler;

    @FXML
    void parentCancelBtn(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainMenuView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void parentSaveBtn(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        if (Wrestler.tempWrestler != null){
//            tempWrestler = Wrestler.tempWrestler;
//
//            wrestlerId.setText(String.valueOf(tempWrestler.getMemberId()) );
//            wrestlerName.setText(tempWrestler.getFirstName());
//        }
//
//
//    }

    }
}
