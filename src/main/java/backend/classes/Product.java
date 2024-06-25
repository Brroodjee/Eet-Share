package backend.classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private int productID;
    private String beschrijving;
    private String productNaam;
    private Winkel winkel;
    private Boolean isVleeswaren;
    private Boolean isDrank;
    private Boolean isZuivel;
    private Boolean isGroente;
    private Boolean isFruit;
    private Boolean isKoek;
    private Boolean isSnoep;
    private Boolean isOverige;

    private static List<Product> producten = new ArrayList<>();

    static {
        Winkel ahWinkel = new Winkel("Albert Heijn", 1);
        producten.add(new Product(1, "Bananen", "AH Bananen tros", ahWinkel, false, false, false, false, true, false, false, false));
        producten.add(new Product(2, "Komkommer", "AH Komkommer", ahWinkel, false, false, false, true, false, false, false, false));
        producten.add(new Product(3, "Gepasteuriseerde halfvolle melk", "AH Halfvolle melk", ahWinkel, false, false, true, false, false, false, false, false));
        producten.add(new Product(4, "rundvlees", "AH Rundergehakt", ahWinkel, true, false, false, false, false, false, false, false));
        producten.add(new Product(5, "Gevulde salmiakdrop", "Venco Schoolkrijt", ahWinkel, false, false, false, false, false, false, true, false));
        producten.add(new Product(6, "Appelsap uit concentraat", "AH Appelsap", ahWinkel, false, true, false, false, false, false, false, false));
        producten.add(new Product(7, "Granenbiscuits met vruchtenvulling", "Liga Evergreen krenten", ahWinkel, false, false, false, false, false, true, false, false));
        producten.add(new Product(8, "Chocolademelk", "Chocomel Vol", ahWinkel, false, false, true, false, false, false, false, false));
        producten.add(new Product(9, "Kipfilet", "AH Scharrel kipfilet 2 stuks", ahWinkel, true, false, false, false, false, false, false, false));
        producten.add(new Product(10, "Frisdrank met groene thee-extracten en citrussmaak, met suikers en zoetstof.", "Fuze Tea Green ice tea", ahWinkel, false, true, false, false, false, false, false, false));
        producten.add(new Product(11, "Pasta", "Bertolli Pasta spaghetti no105", ahWinkel, false, false, false, false, false, false, false, true));
        producten.add(new Product(12, "Groene pesto", "AH Groene pesto", ahWinkel, false, false, false, false, false, false, false, true));
    }

    public Product(int productID, String beschrijving, String productNaam, Winkel winkel, Boolean isVleesware, Boolean isDrank, Boolean isZuivel, Boolean isGroente, Boolean isFruit, Boolean isKoek, Boolean isSnoep, Boolean isOverige) {
        this.productID = productID;
        this.beschrijving = beschrijving;
        this.productNaam = productNaam;
        this.winkel = winkel;
        this.isVleeswaren = isVleesware;
        this.isDrank = isDrank;
        this.isZuivel = isZuivel;
        this.isGroente = isGroente;
        this.isFruit = isFruit;
        this.isKoek = isKoek;
        this.isSnoep = isSnoep;
        this.isOverige = isOverige;
    }

    public int getProductID() {
        return productID;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getProductNaam() {
        return productNaam;
    }

    public Winkel getWinkel() {
        return winkel;
    }

    public Boolean getIsVleeswaren() {
        return isVleeswaren;
    }

    public Boolean getIsDrank() {
        return isDrank;
    }

    public Boolean getIsZuivel() {
        return isZuivel;
    }

    public Boolean getIsGroente() {
        return isGroente;
    }

    public Boolean getIsFruit() {
        return isFruit;
    }

    public Boolean getIsKoek() {
        return isKoek;
    }

    public Boolean getIsSnoep() {
        return isSnoep;
    }

    public Boolean getIsOverige() {
        return isOverige;
    }

    public static List<Product> getProducten() {
        return producten;
    }

    public static void setProducten(List<Product> producten) {
        Product.producten = producten;
    }

    public String getCategory() {
        if (this.isVleeswaren) {
            return "vleeswaren";
        } else if (this.isDrank) {
            return "dranken";
        } else if (this.isZuivel) {
            return "zuivel";
        } else if (this.isGroente || this.isFruit) {
            return "groente_en_fruit";
        } else if (this.isKoek || this.isSnoep) {
            return "koek_en_snoep";
        } else if (this.isOverige) {
            return "overige";
        } else {
            return "overige";
        }
    }

    public JsonObject toJson() {
        JsonObjectBuilder productBuilder = Json.createObjectBuilder();
        productBuilder.add("productID", this.productID);
        productBuilder.add("beschrijving", this.beschrijving);
        productBuilder.add("productNaam", this.productNaam);
        productBuilder.add("winkel", this.winkel.getWinkelNaam());
        productBuilder.add("isVleeswaren", this.isVleeswaren);
        productBuilder.add("isDrank", this.isDrank);
        productBuilder.add("isZuivel", this.isZuivel);
        productBuilder.add("isGroente", this.isGroente);
        productBuilder.add("isFruit", this.isFruit);
        productBuilder.add("isKoek", this.isKoek);
        productBuilder.add("isSnoep", this.isSnoep);
        productBuilder.add("isOverige", this.isOverige);

        return productBuilder.build();
    }

    public static Product fromJson(JsonObject jsonObject) {  // Dit is puur chatGPT
        int productId = jsonObject.getInt("productID");
        String beschrijving = jsonObject.getString("beschrijving");
        String productNaam = jsonObject.getString("productNaam");
        Winkel winkel = new Winkel(jsonObject.getString("winkel"), 1);
        boolean isVleeswaren = jsonObject.getBoolean("isVleeswaren");
        boolean isDrank = jsonObject.getBoolean("isDrank");
        boolean isZuivel = jsonObject.getBoolean("isZuivel");
        boolean isGroente = jsonObject.getBoolean("isGroente");
        boolean isFruit = jsonObject.getBoolean("isFruit");
        boolean isKoek = jsonObject.getBoolean("isKoek");
        boolean isSnoep = jsonObject.getBoolean("isSnoep");
        boolean isOverige = jsonObject.getBoolean("isOverige");

        return new Product(productId, beschrijving, productNaam, winkel, isVleeswaren, isDrank, isZuivel, isGroente, isFruit, isKoek, isSnoep, isOverige);
    }
}