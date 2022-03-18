package Model;

public class User {

    private static String username;
    private String userName;
    private int userID;
    private String password;

    public User(String username) {
        this.username = username;
    }

    public User(String userName, int userID, String password) {
        this.userName = userName;
        this.userID = userID;
        this.password = password;
    }
}
