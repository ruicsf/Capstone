package sample;

import Database.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginView.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 350, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        JDBC.Connect();

        JDBC.mysqlDatabase = new JDBC();
        JDBC.mysqlDatabase.getDatabaseRoster();


        launch(args);

        JDBC.closeConnection();


    }
}
