package backend.classes;

import java.util.ArrayList;

public class Favorietenlijstje {
    private int userID;
    private ArrayList<Product> producten;

    public Favorietenlijstje(int userID) {
        this.userID = userID;
    }

    public ArrayList<Product> getProducten() {
        return producten;
    }

    public void setProducten(ArrayList<Product> producten) {
        this.producten = producten;
    }

    public int getUserID() {
        return userID;
    }
}
