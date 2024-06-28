package backend.classes;

import java.util.ArrayList;
import java.util.List;

public class Gebruiker extends User {
    private int gebruikerID;
    private static int nextID = 1;
    private static List<Gebruiker> gebruikers = new ArrayList<>();

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

    public static List<Gebruiker> getGebruikers() {
        return gebruikers;
    }

    public static void setGebruikers(List<Gebruiker> gebruikers) {
        Gebruiker.gebruikers = gebruikers;
    }

    public void createHousehold(String householdName) {
        Hoofd nieuweHoofd = new Hoofd(this.getGebruikerID(), this.getUsername(), this.getPassword(), this.getUserID(), "hoofd");
        Hoofd.getHoofden().add(nieuweHoofd);

        Huishouden nieuwHuishouden = new Huishouden(householdName, nieuweHoofd);
        Huishouden.getHuishoudens().add(nieuwHuishouden);

        List<User> users = User.getUsers();
        users.remove(this);
        gebruikers.remove(this);

        User.getUsers().add(nieuweHoofd);
    }  // de gebruiker wordt omgezet naar Hoofd in zowel User als in Hoofd zelf, hij wordt ook verwijderd als Gebruiker

    public void acceptInvitation() {
        for (Invite invite : Invite.getInvites()) {
            if (invite.getGebruikerInvite().getUsername().equals(this.getUsername()) && invite.getStatusInvite().equals("pending")) {
                Hoofd hoofd = invite.getHoofdInvite();
                Huishouden huishouden = null;

                for (Huishouden h : Huishouden.getHuishoudens()) {
                    if (h.getHoofd().equals(hoofd)) {
                        huishouden = h;
                        break;
                    }
                }

                if (huishouden != null) {
                    Lid nieuwLid = new Lid(this.getGebruikerID(), this.getUsername(), this.getPassword(), this.getUserID(), "lid");
                    huishouden.addLid(nieuwLid);
                    Lid.getLeden().add(nieuwLid);

                    List<User> users = User.getUsers();
                    users.remove(this);
                    Gebruiker.getGebruikers().remove(this);

                    User.getUsers().add(nieuwLid);

                    invite.setStatusInvite("accepted");

                    break;
                }
            }
        }
    }

    public void declineInvitation() {
        for (Invite invite : Invite.getInvites()) {
            if (invite.getGebruikerInvite().getUsername().equals(this.getUsername()) && invite.getStatusInvite().equals("pending")) {
                Hoofd hoofd = invite.getHoofdInvite();
                Huishouden huishouden = null;

                for (Huishouden h : Huishouden.getHuishoudens()) {
                    if (h.getHoofd().equals(hoofd)) {
                        huishouden = h;
                        break;
                    }
                }

                if (huishouden != null) {

                    invite.setStatusInvite("declined");

                    break;
                }
            }
        }
    }

    public static Gebruiker getGebruikerByUsername(String username) {
        List<Gebruiker> gebruikers = Gebruiker.getGebruikers();
        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.getUsername().equals(username)) {
                return gebruiker;
            }
        }
        return null;
    }
}