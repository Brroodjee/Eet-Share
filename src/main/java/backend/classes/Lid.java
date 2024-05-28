package backend.classes;

public class Lid extends User {
    private int lidID;

    public Lid(int lidID, String naam, String email, String wachtwoord, int userID) {
        super(naam, email, wachtwoord, userID);
        this.lidID = lidID;
    }

    public int getLidID() {
        return lidID;
    }
}
