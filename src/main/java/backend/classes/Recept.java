package backend.classes;

import java.util.ArrayList;

public class Recept {
    private int receptID;
    private String beschrijving;
    private ArrayList<Product> producten;

    public Recept(int receptID, String beschrijving) {
        this.receptID = receptID;
        this.beschrijving = beschrijving;
    }
}
