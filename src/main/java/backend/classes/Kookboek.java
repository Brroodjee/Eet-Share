package backend.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Kookboek implements Serializable {
    private Huishouden huishouden;
    private ArrayList<Recept> recepten;
    private static List<Kookboek> kookboeken = new ArrayList<>();

    public Kookboek(Huishouden huishouden) {
        this.huishouden = huishouden;
        this.recepten = new ArrayList<>();
        Kookboek.getKookboeken().add(this);
    }


    public Huishouden getHuishouden() {
        return huishouden;
    }

    public ArrayList<Recept> getRecepten() {
        return recepten;
    }

    public void addRecept(Recept recept) {
        this.recepten.add(recept);
    }

    public static List<Kookboek> getKookboeken() {
        return kookboeken;
    }

    public static void setKookboeken(List<Kookboek> kookboeken) {
        Kookboek.kookboeken = kookboeken;
    }

    public static Kookboek findKookboekByHuishouden(Huishouden huishouden) {

        for (Kookboek kookboek : kookboeken) {
            if (kookboek.getHuishouden().equals(huishouden)) {
                return kookboek;
            }
        }
        return null;
    }
}
