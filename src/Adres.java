public class Adres {
    private int adresID;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private Reiziger reiziger;

    public Adres(int adresID, String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger) {
        this.adresID = adresID;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger = reiziger;
    }

    public int getAdresID() {
        return adresID;
    }

    public void setAdresID(int adresID) {
        this.adresID = adresID;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "Adres: " +
                "adres_id=" + adresID +
                ", postcode='" + postcode + '\'' +
                ", huisnummer=" + huisnummer +
                ", straat='" + straat + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                ", reiziger_id=" + reiziger;
    }
}
