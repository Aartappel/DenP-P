import java.sql.Date;
import java.util.List;

public class OVChipkaart {
    private double kaartNummer;
    private Date geldigTotDatum;
    private int klasse;
    private double saldo;
    private Reiziger reiziger;
    private List<Product> producten;

    public OVChipkaart(double kaartNummer, Date geldigTotDatum, int klasse, double saldo, Reiziger reiziger,
                       List<Product> producten) {
        this.kaartNummer = kaartNummer;
        this.geldigTotDatum = geldigTotDatum;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
        this.producten = producten;
    }

    public double getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(double kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTotDatum() {
        return geldigTotDatum;
    }

    public void setGeldigTotDatum(Date geldigTotDatum) {
        this.geldigTotDatum = geldigTotDatum;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    public boolean addProduct(Product product) {
        return this.producten.add(product);
    }

    public boolean removeProduct(Product product) {
        return producten.remove(product);
    }

    @Override
    public String toString() {
        return "OVChipkaart: " +
                "kaartNummer: " + kaartNummer +
                ", geldigTotDatum: " + geldigTotDatum +
                ", klasse: " + klasse +
                ", saldo: " + saldo +
                ", reiziger: " + reiziger +
                ", producten: " + producten.toString();
    }
}
