package Model;

public class UserLog {

    private static String username;
    private String userName;
    private int userID;
    private String password;

    public UserLog(String username) {
        this.username = username;
    }

    public UserLog(String userName, int userID, String password) {
        this.userName = userName;
        this.userID = userID;
        this.password = password;
    }
}
