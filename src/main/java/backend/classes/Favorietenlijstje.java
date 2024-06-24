package backend.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Favorietenlijstje implements Serializable {
    private User user;
    private ArrayList<Product> producten;
    private static List<Favorietenlijstje> favorietenlijstjes = new ArrayList<>();

    public Favorietenlijstje(User user) {
        this.user = user;
    }

    public ArrayList<Product> getProducten() {
        return producten;
    }

    public void setProducten(ArrayList<Product> producten) {
        this.producten = producten;
    }

    public User getUserFavorietenlijstje() {
        return user;
    }

    public void setUserFavorietenlijstje(User user) {
        this.user = user;
    }

    public static List<Favorietenlijstje> getFavorietenlijstjes() {
        return favorietenlijstjes;
    }

    public static void setFavorietenlijstjes(List<Favorietenlijstje> favorietenlijstjes) {
        Favorietenlijstje.favorietenlijstjes = favorietenlijstjes;
    }
}
