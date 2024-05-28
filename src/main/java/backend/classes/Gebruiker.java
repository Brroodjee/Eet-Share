package backend.classes;

public class Gebruiker extends User {
    private int gebruikerID;

    public Gebruiker(int gebruikerID, String naam, String email, String wachtwoord, int userID) {
        super(naam, email, wachtwoord, userID);
        this.gebruikerID = gebruikerID;
    }
}
