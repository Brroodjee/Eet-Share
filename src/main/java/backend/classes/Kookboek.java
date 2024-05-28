package backend.classes;

import java.util.ArrayList;

public class Kookboek {
    private int kookboekID;
    private Huishouden huishouden;
    private ArrayList<Recept> recepten;

    public Kookboek(int kookboekID, Huishouden huishouden) {
        this.kookboekID = kookboekID;
        this.huishouden = huishouden;
    }
}
