import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private Connection conn;
    private ReizigerDAO rdao;

    public AdresDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO adres VALUES (?, ?, ?, ?, ?, ?)");
            pSt.setInt(1, adres.getAdres_id());
            pSt.setString(2, adres.getPostcode());
            pSt.setString(3, adres.getHuisnummer());
            pSt.setString(4, adres.getStraat());
            pSt.setString(5, adres.getWoonplaats());
            pSt.setInt(6, adres.getReiziger_id());
            pSt.executeQuery();

            pSt.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean update(Adres adres) {
        try {
            PreparedStatement pSt = conn.prepareStatement("UPDATE adres SET postcode = ?, huisnummer = ?, " +
                    "straat = ?, woonplaats = ?, reiziger_id = ? WHERE adres_id = ?");
            pSt.setString(1, adres.getPostcode());
            pSt.setString(2, adres.getHuisnummer());
            pSt.setString(3, adres.getStraat());
            pSt.setString(4, adres.getWoonplaats());
            pSt.setInt(5, adres.getReiziger_id());
            pSt.setInt(6, adres.getAdres_id());
            pSt.executeQuery();

            pSt.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            PreparedStatement pSt = conn.prepareStatement("DELETE FROM adres WHERE adres_id = ?");
            pSt.setInt(1, adres.getAdres_id());
            pSt.executeQuery();

            pSt.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM adres WHERE reiziger_id = ?");
            pSt.setInt(1, reiziger.getId());
            ResultSet myRS = pSt.executeQuery();

            if (myRS.next()) {
                return new Adres(myRS.getInt("adres_id"),
                        myRS.getString("postcode"),
                        myRS.getString("huisnummer"),
                        myRS.getString("straat"),
                        myRS.getString("woonplaats"),
                        myRS.getInt("reiziger_id"));
            }

            myRS.close();
            pSt.close();
            return null;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public Adres findById(int ID) {
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM adres WHERE adres_id = ?");
            pSt.setInt(1, ID);
            ResultSet myRS = pSt.executeQuery();

            if (myRS.next()) {
                return new Adres(myRS.getInt("adres_id"),
                        myRS.getString("postcode"),
                        myRS.getString("huisnummer"),
                        myRS.getString("straat"),
                        myRS.getString("woonplaats"),
                        myRS.getInt("reiziger_id"));
            }

            myRS.close();
            pSt.close();
            return null;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        List<Adres> adressen = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet myRS = stmt.executeQuery("SELECT * FROM adres");

            while (myRS.next()) {
                Adres adres = new Adres(myRS.getInt("adres_id"),
                        myRS.getString("postcode"),
                        myRS.getString("huisnummer"),
                        myRS.getString("straat"),
                        myRS.getString("woonplaats"),
                        myRS.getInt("reiziger_id"));

                adressen.add(adres);
            }

            myRS.close();
            stmt.close();
            return adressen;

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
