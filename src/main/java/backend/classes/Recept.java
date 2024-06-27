package backend.classes;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Recept implements Serializable {
    private String receptNaam;
    private int servings;
    private int cookingTime;
    private int preppingTime;
    private String beschrijving;
    private ArrayList<Product> producten;
    private static List<Recept> recepten = new ArrayList<>();

    public Recept(String receptNaam, int servings, int cookingTime, int preppingTime, String beschrijving) {
        this.receptNaam = receptNaam;
        this.servings = servings;
        this.cookingTime = cookingTime;
        this.preppingTime = preppingTime;
        this.beschrijving = beschrijving;
    }

    public String getReceptNaam() {
        return receptNaam;
    }

    public int getServings() {
        return servings;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public int getPreppingTime() {
        return preppingTime;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public ArrayList<Product> getProducten() {
        return producten;
    }

    public void setReceptNaam(String receptNaam) {
        this.receptNaam = receptNaam;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setPreppingTime(int preppingTime) {
        this.preppingTime = preppingTime;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setProducten(ArrayList<Product> producten) {
        this.producten = producten;
    }

    public static List<Recept> getRecepten() {
        return recepten;
    }

    public static void setRecepten(List<Recept> recepten) {
        Recept.recepten = recepten;
    }
}
