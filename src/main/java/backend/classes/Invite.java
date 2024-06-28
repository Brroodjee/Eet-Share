package backend.classes;

import java.util.ArrayList;
import java.util.List;

public class Invite {
    private Hoofd hoofd;
    private Gebruiker gebruiker;
    private String status;
    private static List<Invite> invites = new ArrayList<>();

    public Invite(Hoofd hoofd, Gebruiker gebruiker, String status) {
        this.hoofd = hoofd;
        this.gebruiker = gebruiker;
        this.status = "pending";
    }

    public Hoofd getHoofdInvite() {
        return hoofd;
    }

    public void setHoofdInvite(Hoofd hoofd) {
        this.hoofd = hoofd;
    }

    public Gebruiker getGebruikerInvite() {
        return gebruiker;
    }

    public void setGebruikerInvite(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public String getStatusInvite() {
        return status;
    }

    public void setStatusInvite(String status) {
        this.status = status;
    }

    public static List<Invite> getInvites() {
        return invites;
    }

    public static void setInvites(List<Invite> invites) {
        Invite.invites = invites;
    }
}
