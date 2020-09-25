public class Product {
    private int productNummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    private OVChipkaart ovChipkaart;

    public Product(int productNummer, String naam, String beschrijving, double prijs) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public OVChipkaart getOvChipkaart() {
        return ovChipkaart;
    }

    public void setOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaart = ovChipkaart;
    }

    @Override
    public String toString() {
        return "Product: " +
                "productNummer: " + productNummer +
                ", naam: '" + naam + '\'' +
                ", beschrijving: '" + beschrijving + '\'' +
                ", prijs: " + prijs +
                ", ovChipkaart: " + ovChipkaart.toString();
    }
}
