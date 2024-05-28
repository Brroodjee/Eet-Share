package backend.classes;

import java.util.ArrayList;

public class Huishouden {
    private String huishoudenNaam;
    private Hoofd hoofd;
    private ArrayList<Lid> leden;
    private ArrayList<Product> producten;

    public Huishouden(String huishoudenNaam, Hoofd hoofd) {
        this.huishoudenNaam = huishoudenNaam;
        this.hoofd = hoofd;
    }

    public String getHuishoudenNaam() {
        return huishoudenNaam;
    }

    public void setHuishoudenNaam(String huishouden) {
        this.huishoudenNaam = huishouden;
    }

    public Hoofd getHoofd() {
        return hoofd;
    }

    public void setHoofd(Hoofd hoofd) {
        this.hoofd = hoofd;
    }
}
