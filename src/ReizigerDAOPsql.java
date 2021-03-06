import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;
    private AdresDAO adao;
    private OVChipkaartDAO ovdao;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO reiziger VALUES (?, ?, ?, ?, ?)");
            pSt.setInt(1, reiziger.getReizigerID());
            pSt.setString(2, reiziger.getVoorletters());
            pSt.setString(3, reiziger.getTussenvoegsel());
            pSt.setString(4, reiziger.getAchternaam());
            pSt.setDate(5, reiziger.getGeboortedatum());
            pSt.executeQuery();

            pSt.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            PreparedStatement pSt = conn.prepareStatement("UPDATE reiziger SET voorletters = ?, " +
                    "tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?");
            pSt.setString(1, reiziger.getVoorletters());
            pSt.setString(2, reiziger.getTussenvoegsel());
            pSt.setString(3, reiziger.getAchternaam());
            pSt.setDate(4, reiziger.getGeboortedatum());
            pSt.setInt(5, reiziger.getReizigerID());
            pSt.executeQuery();

            pSt.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            PreparedStatement pSt = conn.prepareStatement("DELETE FROM reiziger WHERE reiziger_id = ?");
            pSt.setInt(1, reiziger.getReizigerID());
            pSt.executeQuery();

            pSt.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public Reiziger findById(int ID) {
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM reiziger WHERE reiziger_id = ?");
            pSt.setInt(1, ID);
            ResultSet myRS = pSt.executeQuery();

            if (myRS.next()) {
                List<OVChipkaart> ovChipkaarten = new ArrayList<>(ovdao.findByReiziger(this.findById(ID)));

                return new Reiziger(myRS.getInt("reiziger_id"),
                        myRS.getString("voorletters"),
                        myRS.getString("tussenvoegsel"),
                        myRS.getString("achternaam"),
                        myRS.getDate("geboortedatum"),
                        ovChipkaarten);
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
    public List<Reiziger> findByGbdatum(String datum) {
        List<Reiziger> reizigers = new ArrayList<>();
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM reiziger WHERE geboortedatum = ?");
            pSt.setDate(1, Date.valueOf(datum));
            ResultSet myRS = pSt.executeQuery();

            while (myRS.next()) {
                List<OVChipkaart> ovChipkaarten = new ArrayList<>(ovdao.findByReiziger(this.findById(myRS.getInt(
                        "reiziger_id"))));

                Reiziger reiziger = new Reiziger(myRS.getInt("reiziger_id"),
                        myRS.getString("voorletters"),
                        myRS.getString("tussenvoegsel"),
                        myRS.getString("achternaam"),
                        myRS.getDate("geboortedatum"),
                        ovChipkaarten);

                reizigers.add(reiziger);
            }

            myRS.close();
            pSt.close();
            return reizigers;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        List<Reiziger> reizigers = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet myRS = stmt.executeQuery("SELECT * FROM reiziger");

            while (myRS.next()) {
                List<OVChipkaart> ovChipkaarten = new ArrayList<>(ovdao.findByReiziger(this.findById(myRS.getInt(
                        "reiziger_id"))));

                Reiziger reiziger = new Reiziger(myRS.getInt("reiziger_id"),
                        myRS.getString("voorletters"),
                        myRS.getString("tussenvoegsel"),
                        myRS.getString("achternaam"),
                        myRS.getDate("geboortedatum"),
                        ovChipkaarten);

                reizigers.add(reiziger);
            }

            myRS.close();
            stmt.close();
            return reizigers;

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public Adres findAdresByReizigerId(Reiziger reiziger) {
        try {
            return adao.findByReiziger(reiziger);
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
