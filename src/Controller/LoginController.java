package Controller;

import Database.JDBC;
import Model.UserLog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Label titleLbl;
    @FXML
    private Button loginButton;
    @FXML
    private Label invalidLoginLbl;
    @FXML
    private Label usernameLbl;
    @FXML
    private TextField usernameTxt;
    @FXML
    private Label passwordLbl;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Label ZoneIDLbl;
    @FXML


    Stage stage;
    Parent scene;

    @FXML
    void OnActionLogin(ActionEvent event) {
        // Get user login credentials
        invalidLoginLbl.setText("");

        String username = usernameTxt.getText();
        String password = passwordTxt.getText();

        boolean verifyLogin = false;
        try {
            verifyLogin = JDBC.mysqlDatabase.DBaccessValidation(username, password);
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }


        if (verifyLogin) {
            try {

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/MainMenuView.fxml")));
                stage.setScene(new Scene(scene));
                stage.show();

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {

            System.out.println("Login Failed");
            invalidLoginLbl.setText("Invalid Login");
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invalidLoginLbl.setText("");
    }
}
