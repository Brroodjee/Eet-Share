package backend.classes;

import java.util.ArrayList;
import java.util.List;

public class User {
    protected String naam;
    protected String email;
    protected String wachtwoord;
    protected int userID;

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Bo", "bo_harmsen@outlook.com", "boopboop789", 000001));
        users.add(new User("Tom", "tom@outlook.com", "boopboop789", 000002));
        users.add(new User("Jessica", "jessica@outlook.com", "boopboop789", 000003));
        users.add(new User("Maus", "maus@outlook.com", "boopboop789", 000004));
        users.add(new User("Maurice", "maurice@outlook.com", "boopboop789", 000005));
    }


    public User(String naam, String email, String wachtwoord, int userID) {
        this.naam = naam;
        this.wachtwoord = wachtwoord;
        this.userID = userID;

        if(email.contains("@") && email.contains(".") && email.length() >=5){
            this.email = email;
        } else {
            throw new IllegalArgumentException("Het ingevoerde emailadres is niet geldig");
        }

        users.add(this);
    }

    public static List<User> getUsers() {
        return users;
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
}
