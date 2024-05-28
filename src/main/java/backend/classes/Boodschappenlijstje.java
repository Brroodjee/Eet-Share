package backend.classes;

import java.util.ArrayList;

public class Boodschappenlijstje {
    private Huishouden huishouden;
    private ArrayList<Product> producten;

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
}
