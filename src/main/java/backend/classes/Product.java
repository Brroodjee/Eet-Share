package backend.classes;

public class Product {
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

    public Product(int productID, String beschrijving, String productNaam, Winkel winkel, Boolean isVleesware, Boolean isDrank, Boolean isZuivel, Boolean isGroente, Boolean isFruit, Boolean isKoek, Boolean isSnoep, Boolean isOverige) {
        this.productID = productID;
        this.beschrijving = beschrijving;
        this.productNaam = productNaam;
        this.winkel = winkel;
        this.isVleeswaren = isVleeswaren;
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
}
