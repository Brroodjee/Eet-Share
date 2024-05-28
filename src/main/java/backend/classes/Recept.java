package backend.classes;

import java.time.Duration;
import java.util.ArrayList;

public class Recept {
    private int receptID;
    private String receptNaam;
    private int servings;
    private Duration cookingTime;
    private Duration preppingTime;
    private String beschrijving;
    private ArrayList<Product> producten;

    public Recept(int receptID, String receptNaam, int servings, Duration cookingTime,Duration preppingTime, String beschrijving) {
        this.receptID = receptID;
        this.receptNaam = receptNaam;
        this.servings = servings;
        this.cookingTime = cookingTime;
        this.preppingTime = preppingTime;
        this.beschrijving = beschrijving;
    }

    public int getReceptID() {
        return receptID;
    }

    public String getReceptNaam() {
        return receptNaam;
    }

    public int getServings() {
        return servings;
    }

    public Duration getCookingTime() {
        return cookingTime;
    }

    public Duration getPreppingTime() {
        return preppingTime;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public ArrayList<Product> getProducten() {
        return producten;
    }

    public void setReceptID(int receptID) {
        this.receptID = receptID;
    }

    public void setReceptNaam(String receptNaam) {
        this.receptNaam = receptNaam;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCookingTime(Duration cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setPreppingTime(Duration preppingTime) {
        this.preppingTime = preppingTime;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setProducten(ArrayList<Product> producten) {
        this.producten = producten;
    }
}
