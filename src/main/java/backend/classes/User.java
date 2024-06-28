package backend.classes;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User implements Principal, Serializable {
    protected String username;
    protected String password;
    protected int userID;
    protected String role;
    private static List<User> users = new ArrayList<>();

    public User() {
        this.userID = getNewUserID();
    }

    public User(String username, String password, int userID, String role) {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.role = role;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        User.users = users;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public static boolean exists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getName() {
        return this.username;
    }

    public int getUserID() {
        return userID;
    }

    public static int getNewUserID() {
        List<User> newUsers = new ArrayList<>();

        if (users.isEmpty()) {
            return 1;
        } else {
            users.sort(Comparator.comparingInt(User::getUserID).reversed());

            int highestUserID = users.get(0).getUserID();
            return highestUserID + 1;
        }
    }

    public static User getUserByUsername(String username) {
        List<User> users = User.getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isValidUser(User user) {
        List<User> users = User.getUsers();
        for (User storedUser : users) {
            if (storedUser.getUsername().equals(user.getUsername()) && storedUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

}