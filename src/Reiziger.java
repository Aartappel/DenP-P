import java.sql.Date;

public class Reiziger {
    private int reizigerID;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reizigerID = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getId() {
        return reizigerID;
    }

    public void setId(int reiziger_id) {
        this.reizigerID = reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getNaam() {
        StringBuilder naam = new StringBuilder();
        naam.append(voorletters).append(" ");
        if (this.tussenvoegsel != null) {
            naam.append(tussenvoegsel).append(" ");
        }
        naam.append(achternaam);
        return naam.toString();
    }

    @Override
    public String toString() {
        return "reiziger: " +
                "id: " + reizigerID +
                ", voorletters: '" + voorletters + '\'' +
                ", tussenvoegsel: '" + tussenvoegsel + '\'' +
                ", achternaam: '" + achternaam + '\'' +
                ", geboortedatum: " + geboortedatum;
    }
}
