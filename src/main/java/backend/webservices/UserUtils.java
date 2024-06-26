package backend.webservices;

import backend.classes.*;

import java.util.List;

public class UserUtils {

    public static User getUserByUsername(String username) {
        List<User> users = User.getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isValidUser(User user) {
        List<User> users = User.getUsers();
        for (User storedUser : users) {
            if (storedUser.getUsername().equals(user.getUsername()) && storedUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public static Hoofd getHoofdByUsername(String username) {
        List<Hoofd> hoofden = Hoofd.getHoofden();
        for (Hoofd hoofd : hoofden) {
            if (hoofd.getUsername().equals(username)) {
                return hoofd;
            }
        }
        return null;
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
