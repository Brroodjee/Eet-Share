package backend.classes;

import java.util.ArrayList;

public class Boodschappenlijstje {
    private Huishouden huishouden;
    private ArrayList<Product> producten;

    public Boodschappenlijstje(Huishouden huishouden) {
        this.huishouden = huishouden;
    }
}
