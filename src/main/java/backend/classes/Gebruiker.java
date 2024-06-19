package backend.classes;

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
        Huishouden nieuwHuishouden = new Huishouden(householdName, nieuweHoofd);
        Huishouden.getHuishoudens().add(nieuwHuishouden);
    }  // de gebruiker wordt nog niet omgezet naar een Hoofd bij de users zelf, maar dat komt later, hij wordt al wel als Hoofd toegevoegd in het huishouden.

    public void acceptInvitation() {
        // Implementatie voor het accepteren van een uitnodiging
    }

    public void declineInvitation() {
        // Implementatie voor het weigeren van een uitnodiging
    }
}
