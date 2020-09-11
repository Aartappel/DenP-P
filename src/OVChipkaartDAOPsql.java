import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ProductDAOPsql pdao;

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO ov_chipkaart VALUES (?, ?, ?, ?, ?)");
            pSt.setInt(1, ovChipkaart.getKaartNummer());
            pSt.setDate(2, ovChipkaart.getGeldigTotDatum());
            pSt.setInt(3, ovChipkaart.getKlasse());
            pSt.setDouble(4, ovChipkaart.getSaldo());
            pSt.setInt(5, ovChipkaart.getReiziger().getReizigerID());
            pSt.executeQuery();

            pSt.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            PreparedStatement pSt = conn.prepareStatement("UPDATE ov_chipkaart SET geldig_tot = ?, klasse = ?, " +
                    "saldo = ?, reiziger_id = ? WHERE kaart_nummer = ?");
            pSt.setDate(1, ovChipkaart.getGeldigTotDatum());
            pSt.setInt(2, ovChipkaart.getKlasse());
            pSt.setDouble(3, ovChipkaart.getSaldo());
            pSt.setInt(4, ovChipkaart.getReiziger().getReizigerID());
            pSt.setInt(5, ovChipkaart.getKaartNummer());
            pSt.executeQuery();

            pSt.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            PreparedStatement pSt = conn.prepareStatement("DELETE FROM ov_chipkaart WHERE kaart_nummer = ?");
            pSt.setInt(1, ovChipkaart.getKaartNummer());
            pSt.executeQuery();

            pSt.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        List<OVChipkaart> ovChipkaarten = new ArrayList<>();
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM ov_chipkaart WHERE reiziger_id = ?");
            pSt.setInt(1, reiziger.getReizigerID());
            ResultSet myRS = pSt.executeQuery();

            while (myRS.next()) {
                List<Product> producten = new ArrayList<>();

                OVChipkaart ovChipkaart = new OVChipkaart(myRS.getInt("kaart_nummer"),
                        myRS.getDate("geldig_tot"),
                        myRS.getInt("klasse"),
                        myRS.getDouble("saldo"),
                        new ReizigerDAOPsql(conn).findById(myRS.getInt("reiziger_id")),
                        pdao.findByKaartNummer(myRS.getInt("kaart_nummer")));

                ovChipkaarten.add(ovChipkaart);
            }

            myRS.close();
            pSt.close();
            return ovChipkaarten;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public OVChipkaart findByNummer(int nummer) {
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?");
            pSt.setInt(1, nummer);
            ResultSet myRS = pSt.executeQuery();

            if (myRS.next()) {
                return new OVChipkaart(myRS.getInt("kaart_nummer"),
                        myRS.getDate("geldig_tot"),
                        myRS.getInt("klasse"),
                        myRS.getDouble("saldo"),
                        new ReizigerDAOPsql(conn).findById(myRS.getInt("reiziger_id")),
                        pdao.findByKaartNummer(myRS.getInt("kaart_nummer")));
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
    public List<OVChipkaart> findAll() {
        List<OVChipkaart> ovChipkaarten = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet myRS = stmt.executeQuery("SELECT * FROM ov_chipkaart");

            while (myRS.next()) {
                List<Product> producten = new ArrayList<>();

                OVChipkaart ovChipkaart = new OVChipkaart(myRS.getInt("kaart_nummer"),
                        myRS.getDate("geldig_tot"),
                        myRS.getInt("klasse"),
                        myRS.getDouble("saldo"),
                        new ReizigerDAOPsql(conn).findById(myRS.getInt("reiziger_id")),
                        pdao.findByKaartNummer(myRS.getInt("kaart_nummer")));

                ovChipkaarten.add(ovChipkaart);
            }

            myRS.close();
            stmt.close();
            return ovChipkaarten;

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
