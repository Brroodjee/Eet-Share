package backend.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Favorietenlijstje implements Serializable {
    private int userID;
    private ArrayList<Product> producten;
    private static List<Favorietenlijstje> favorietenlijstjes = new ArrayList<>();

    public Favorietenlijstje(int userID) {
        this.userID = userID;
    }

    public ArrayList<Product> getProducten() {
        return producten;
    }

    public void setProducten(ArrayList<Product> producten) {
        this.producten = producten;
    }

    public int getUserID() {
        return userID;
    }

    public static List<Favorietenlijstje> getFavorietenlijstjes() {
        return favorietenlijstjes;
    }

    public static void setFavorietenlijstjes(List<Favorietenlijstje> favorietenlijstjes) {
        Favorietenlijstje.favorietenlijstjes = favorietenlijstjes;
    }
}
