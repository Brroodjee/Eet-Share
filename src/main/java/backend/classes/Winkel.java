package backend.classes;

import java.util.ArrayList;

public class Winkel {
    private String winkelNaam;
    private int winkelID;
    private ArrayList<Product> producten;

    public Winkel(String winkelNaam, int winkelID) {
        this.winkelNaam = winkelNaam;
        this.winkelID = winkelID;
    }

    public String getWinkelNaam() {
        return winkelNaam;
    }

    public int getWinkelID() {
        return winkelID;
    }
}
