package backend.classes;

public class User {
    protected String naam;
    protected String email;
    protected String wachtwoord;
    protected int userID;

    public User(String naam, String email, String wachtwoord, int userID) {
        this.naam = naam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.userID = userID;
    }
}
