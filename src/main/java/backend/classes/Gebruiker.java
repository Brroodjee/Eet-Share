package backend.classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Gebruiker extends User {
    private int gebruikerID;
    private static List<Gebruiker> gebruikers = new ArrayList<>();

    public Gebruiker() {
        this.role = "Gebruiker";
        this.gebruikerID = getNewGebruikerID();
    }

    public Gebruiker(int gebruikerID, String username, String password) {
        super(username, password);
        this.gebruikerID = gebruikerID;
        this.role = "gebruiker";
    }

    public int getGebruikerID() {
        return gebruikerID;
    }

    public static int getNewGebruikerID() {
        if (gebruikers.isEmpty()) {
            return 1;
        } else {
            gebruikers.sort(Comparator.comparingInt(Gebruiker::getGebruikerID).reversed());
            int highestGebruikerID = gebruikers.get(0).getGebruikerID();
            return highestGebruikerID + 1;
        }
    }


    public void createHousehold(String householdName) {
        int newHoofdID = Hoofd.getNewHoofdID();
        Hoofd nieuweHoofd = new Hoofd(newHoofdID, this.getUsername(), this.getPassword());
        Huishouden nieuwHuishouden = new Huishouden(householdName, nieuweHoofd);

        this.role = "hoofd";
        nieuweHoofd.setHoofdID(newHoofdID);
        Hoofd.getHoofden().add(nieuweHoofd);
        Huishouden.getHuishoudens().add(nieuwHuishouden);
    }

    public void acceptInvitation() {
    }

    public void declineInvitation() {
    }
}
