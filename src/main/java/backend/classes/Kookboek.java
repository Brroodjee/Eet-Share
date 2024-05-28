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

    public int getKookboekID() {
        return kookboekID;
    }

    public Huishouden getHuishouden() {
        return huishouden;
    }

    public ArrayList<Recept> getRecepten() {
        return recepten;
    }

    public void setRecepten(ArrayList<Recept> recepten) {
        this.recepten = recepten;
    }
}
