import java.sql.Date;
import java.util.List;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTotDatum;
    private int klasse;
    private float saldo;
    private int reizigerID;
    private List<Product> producten;

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
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

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getReizigerID() {
        return reizigerID;
    }

    public void setReizigerID(int reizigerID) {
        this.reizigerID = reizigerID;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }
}
