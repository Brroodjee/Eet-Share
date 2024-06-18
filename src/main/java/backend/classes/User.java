package backend.classes;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User implements Principal, Serializable {
    protected String username;
    protected String password;
    protected String role;
    private static List<User> users = new ArrayList<>();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "gebruiker";
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
}
