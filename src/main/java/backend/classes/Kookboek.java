package backend.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Kookboek implements Serializable {
    private int kookboekID;
    private Huishouden huishouden;
    private ArrayList<Recept> recepten;
    private static List<Kookboek> kookboeken = new ArrayList<>();

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

    public static List<Kookboek> getKookboeken() {
        return kookboeken;
    }

    public static void setKookboeken(List<Kookboek> kookboeken) {
        Kookboek.kookboeken = kookboeken;
    }
}
