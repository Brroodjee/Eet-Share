package backend.classes;

import org.glassfish.hk2.api.DuplicateServiceException;

import java.util.List;

public class Gebruiker extends User {
    private int gebruikerID;
    private static int nextID = 1;

    public Gebruiker() {
        this.role = "gebruiker";
        this.gebruikerID = nextID++;
    }

    public Gebruiker(String username, String password, int userID, String role) {
        super(username, password, userID, role);
        this.gebruikerID = nextID++;
        this.role = "gebruiker";
    }

    public int getGebruikerID() {
        return gebruikerID;
    }

    public void createHousehold(String householdName) {
        Hoofd nieuweHoofd = new Hoofd(this.getGebruikerID(), this.getUsername(), this.getPassword(), this.getUserID(), "hoofd");
        Hoofd.getHoofden().add(nieuweHoofd);

        Huishouden nieuwHuishouden = new Huishouden(householdName, nieuweHoofd);
        Huishouden.getHuishoudens().add(nieuwHuishouden);

        List<User> users = User.getUsers();
        users.remove(this); // haalt de Gebruiker uit de User list, zet er vervolgens een Hoofd in

        User.getUsers().add(nieuweHoofd);
    }

    public void acceptInvitation() {
        // Implementatie voor het accepteren van een uitnodiging
    }

    public void declineInvitation() {
        // Implementatie voor het weigeren van een uitnodiging
    }
}