package backend.classes;

import java.util.ArrayList;
import java.util.List;

public class Huishouden {
    private String huishoudenNaam;
    private Hoofd hoofd;
    private ArrayList<Lid> leden;
    private ArrayList<Product> producten;

    private static List<Huishouden> huishoudens = new ArrayList<>();

    static {
        Hoofd hoofd1 = new Hoofd(1, "Bo", "bo_harmsen@outlook.com", "boopboop789", 000001);
        Lid lid1 = new Lid(1, "Maus", "maus@outlook.com", "boopboop789", 000004);
        Lid lid2 = new Lid(2, "Jessica", "jessica@outlook.com", "boopboop789", 000003);

        Huishouden huishouden = new Huishouden("Harmsen", hoofd1);
        huishouden.addLid(lid1);
        huishouden.addLid(lid2);

        huishoudens.add(huishouden);
    }

    public Huishouden(String huishoudenNaam, Hoofd hoofd) {
        this.huishoudenNaam = huishoudenNaam;
        this.hoofd = hoofd;
        this.leden = new ArrayList<>();
        this.producten = new ArrayList<>();
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

    public List<Lid> getLeden() {
        return leden;
    }

    public void addLid(Lid lid) {
        this.leden.add(lid);
    }

    public void removeLid(Lid lid) {
        this.leden.remove(lid);
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void addProduct(Product product) {
        this.producten.add(product);
    }

    public void removeProduct(Product product) {
        this.producten.remove(product);
    }

    public static List<Huishouden> getHuishoudens() {
        return huishoudens;
    }
}
