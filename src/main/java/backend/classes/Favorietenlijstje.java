package backend.classes;

import java.util.ArrayList;

public class Favorietenlijstje {
    private int userID;
    private ArrayList<Product> producten;

    public Favorietenlijstje(int userID) {
        this.userID = userID;
    }
}
