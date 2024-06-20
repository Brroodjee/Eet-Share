package backend.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Boodschappenlijstje implements Serializable {
    private Huishouden huishouden;
    private ArrayList<Product> producten;
    private static List<Boodschappenlijstje> boodschappenlijstjes = new ArrayList<>();

    public Boodschappenlijstje(Huishouden huishouden) {
        this.huishouden = huishouden;
    }

    public Huishouden getHuishouden() {
        return huishouden;
    }

    public ArrayList<Product> getProducten() {
        return producten;
    }

    public void setProducten(ArrayList<Product> producten) {
        this.producten = producten;
    }

    public static List<Boodschappenlijstje> getBoodschappenlijstjes() {
        return boodschappenlijstjes;
    }

    public static void setBoodschappenlijstjes(List<Boodschappenlijstje> boodschappenlijstjes) {
        Boodschappenlijstje.boodschappenlijstjes = boodschappenlijstjes;
    }
}
