package Database;


import Model.Parent1;
import Model.Parent2;
import Model.Wrestler;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Class used for database access and interaction.
 */
public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//database-1.cntzuco7cpke.us-east-1.rds.amazonaws.com/";
    private static final String databaseName = "abc_wrestling";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "abcAdmin";
    private static final String password = "Passw0rd!";
    public static JDBC mysqlDatabase;
    public static Connection connection;

    Connection myConn = getConnection();
    PreparedStatement prepStmt;
    ResultSet resultSet;

    /**
     * Method for establishing a connection to the database.
     *
     * @return connection.
     */
    public static Connection Connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println(jdbcUrl + " " + userName + " " + password + " " + "\nConnection link created");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int getDatabaseRoster() {

        try {
            String select = "SELECT * FROM roster;";
            prepStmt = myConn.prepareStatement(select);
            resultSet = prepStmt.executeQuery();
            Wrestler.getDBwrestlerObservableList().clear();
            while (resultSet.next()) {


                Wrestler.getDBwrestlerObservableList().add(
                        new Wrestler(

                                resultSet.getInt("USAW_ID"),
                                resultSet.getString("Wrestler_Name"),
                                resultSet.getString("Parent1_Name"),
                                resultSet.getString("Age_Group"),
                                resultSet.getString("P1_Email"),
                                resultSet.getString("P1_Phone"),
                                resultSet.getString("P1_Address"),
                                resultSet.getInt("P1_Zip"),
                                resultSet.getTimestamp("DOB").toLocalDateTime().toLocalDate(),
                                resultSet.getString("Gender"),
                                resultSet.getInt("ID"),
                                resultSet.getString("Notes")

                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Wrestler.getDBwrestlerObservableList().size();
    }

   public void getDBParent1List() {
       try {
           String select = "SELECT * FROM roster;";
           prepStmt = myConn.prepareStatement(select);
           resultSet = prepStmt.executeQuery();
           Parent1.getDBparent1ObservableList().clear();

           while (resultSet.next()) {
               Parent1.getDBparent1ObservableList().add(
                       new Parent1(
                               resultSet.getString("Parent1_Name"),
                               resultSet.getString("P1_Email"),
                               resultSet.getString("P1_Phone"),
                               resultSet.getString("P1_Address"),
                               resultSet.getInt("P1_Zip"),
                               resultSet.getInt("ID")
                       )
               );
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }


    public void getDBParent2List(){
        try {
            String select = "SELECT * FROM roster;";
            prepStmt = myConn.prepareStatement(select);
            resultSet = prepStmt.executeQuery();
            Parent2.getDBparent2ObservableList().clear();

            while (resultSet.next()) {
                Parent2.getDBparent2ObservableList().add(
                        new Parent2(
                                resultSet.getString("p2_Name"),
                                resultSet.getString("P2_Email"),
                                resultSet.getString("P2_Phone"),
                                resultSet.getString("P2_Address"),
                                resultSet.getInt("P2_Zip"),
                                resultSet.getInt("ID")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to get connection for database.
     *
     * @return the connection.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Method for stopping connections to database.
     */
    public static void closeConnection() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
            connection.close();
            System.out.println("Connection closed at " + LocalDateTime.now() + " || " + dtf.format(ZonedDateTime.now()) + " || " +
                    dtf.format(LocalDateTime.now()) + " || " + LocalTime.now(Clock.systemUTC()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method used for login credentials validation
     *
     * @param username username used for validation
     * @param password     password used for validation
     * @return returns true if username and password match, otherwise returns false.
     * @throws SQLException
     */
    public boolean DBaccessValidation(String username, String password) throws SQLException {

        prepStmt = getConnection().prepareStatement("SELECT * FROM users WHERE User_Name=? ");
        prepStmt.setString(1, username);
        resultSet = prepStmt.executeQuery();
        if (!resultSet.next()) {
            return false;
        }
        if (password.matches(resultSet.getString("Password"))) {
            int loggedUserID = resultSet.getInt("User_ID");
            String loggedUsername = resultSet.getString("User_Name");
            return true;
        }
        return false;
    }

}
