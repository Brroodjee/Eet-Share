package backend.classes;

public class Hoofd extends User {
    private int hoofdID;

    public Hoofd(int hoofdID, String naam, String email, String wachtwoord, int userID) {
        super(naam, email, wachtwoord, userID);
        this.hoofdID = hoofdID;
    }
}
