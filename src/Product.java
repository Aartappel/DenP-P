import java.util.List;

public class Product {
    private int productNummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    private List<OVChipkaart> ovChipkaarten;

    public Product(int productNummer, String naam, String beschrijving, double prijs, List<OVChipkaart> ovChipkaarten) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovChipkaarten = ovChipkaarten;
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

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setOVChipkaart(List<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    public void addOVChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaarten.add(ovChipkaart);
    }

    public void removeOVChipkaart(OVChipkaart ovChipkaart) {
        ovChipkaarten.remove(ovChipkaart);
    }

    @Override
    public String toString() {
        return "Product: " +
                "productNummer: " + productNummer +
                ", naam: '" + naam + '\'' +
                ", beschrijving: '" + beschrijving + '\'' +
                ", prijs: " + prijs +
                ", ovChipkaart: " + ovChipkaarten.toString();
    }
}
