package Database;


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
    private static final String location = "//localhost/";
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


//    /**
//     * Method used for login credentials validation
//     *
//     * @param username username used for validation
//     * @param pass     password used for validation
//     * @return returns true if username and password match, otherwise returns false.
//     * @throws SQLException
//     */
//    public boolean DBaccessValidation(String username, String pass) throws SQLException {
//
//        prepStmt = getConnection().prepareStatement("SELECT * FROM users WHERE User_Name=? ");
//        prepStmt.setString(1, username);
//        resultSet = prepStmt.executeQuery();
//        if (!resultSet.next()) {
//            return false;
//        }
//        if (pass.matches(resultSet.getString("Password"))) {
//            int loggedUserID = resultSet.getInt("User_ID");
//            String loggedUsername = resultSet.getString("User_Name");
//            return true;
//        }
//        return false;
//    }

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
                                resultSet.getString("Age_Group"),
                                resultSet.getString("Parent_Name"),
                                resultSet.getString("Email"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Address"),
                                resultSet.getInt("Zip"),
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

}
