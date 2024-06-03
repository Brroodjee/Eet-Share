package backend.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User implements Serializable {
    protected String naam;
    protected String email;
    protected String wachtwoord;
    protected int userID;

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new Gebruiker(000001, "Bo", "bo_harmsen@outlook.com", "boopboop789", 000001));
        users.add(new Gebruiker(000002, "Tom", "tom@outlook.com", "boopboop789", 000002));
        users.add(new Gebruiker(000003, "Jessica", "jessica@outlook.com", "boopboop789", 000003));
        users.add(new Gebruiker(000004, "Maus", "maus@outlook.com", "boopboop789", 000004));
        users.add(new Gebruiker(000005, "Maurice", "maurice@outlook.com", "boopboop789", 000005));
    }

    public User(String naam, String email, String wachtwoord, int userID) {
        this.naam = naam;
        this.wachtwoord = wachtwoord;
        this.userID = userID;

        if (email.contains("@") && email.contains(".") && email.length() >= 5) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Het ingevoerde emailadres is niet geldig");
        }

        users.add(this);
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        User.users = users;
    }

    public String getNaam() {
        return naam;
    }

    public String getEmail() {
        return email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public int getUserID() {
        return userID;
    }

    public boolean validatePassword(String password) {
        return this.wachtwoord.equals(password);
    }

    public static int getNewUserID() {
        if (users.isEmpty()) {
            return 1;
        } else {
            users.sort(Comparator.comparingInt(User::getUserID).reversed());
            int highestUserID = users.get(0).getUserID();
            return highestUserID + 1;
        }
    }

    public static boolean exists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
