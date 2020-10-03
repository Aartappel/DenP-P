import java.util.List;

public class Product {
    private int productNummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    private List<Double> ovChipkaartNummers;

    public Product(int productNummer, String naam, String beschrijving, double prijs, List<Double> ovChipkaartNummers) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovChipkaartNummers = ovChipkaartNummers;
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

    public List<Double> getOvChipkaartNummers() {
        return ovChipkaartNummers;
    }

    public void setOVChipkaarten(List<OVChipkaart> ovChipkaarten) {
        for (OVChipkaart ovChipkaart : ovChipkaarten) {
            this.ovChipkaartNummers.add(ovChipkaart.getKaartNummer());
        }
    }

    public boolean addOVChipkaart(double ovChipkaartNummer) {
        return this.ovChipkaartNummers.add(ovChipkaartNummer);
    }

    public boolean removeOVChipkaart(OVChipkaart ovChipkaart) {
        return ovChipkaartNummers.remove(ovChipkaart.getKaartNummer());
    }

    @Override
    public String toString() {
        return "Product: " +
                "productNummer: " + productNummer +
                ", naam: '" + naam + '\'' +
                ", beschrijving: '" + beschrijving + '\'' +
                ", prijs: " + prijs +
                ", ovChipkaart: " + ovChipkaartNummers.toString();
    }
}
