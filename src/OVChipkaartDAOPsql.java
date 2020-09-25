import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ProductDAOPsql pdao;

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO ov_chipkaart VALUES (?, ?, ?, ?, ?)");
            pSt.setDouble(1, ovChipkaart.getKaartNummer());
            pSt.setDate(2, ovChipkaart.getGeldigTotDatum());
            pSt.setInt(3, ovChipkaart.getKlasse());
            pSt.setDouble(4, ovChipkaart.getSaldo());
            pSt.setInt(5, ovChipkaart.getReiziger().getReizigerID());
            pSt.executeQuery();

            pSt.close();

            for (Product product : ovChipkaart.getProducten()) {
                PreparedStatement pStP = conn.prepareStatement("INSERT INTO ov_chipkaart_product VALUES (?, ?, ?, ?)");
                pStP.setDouble(1, product.getOvChipkaart().getKaartNummer());
                pStP.setInt(2, product.getProductNummer());
                pStP.setString(3, null);
                pStP.setDate(4, new Date(System.currentTimeMillis()));
                pStP.executeQuery();

                pStP.close();
            }
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
            pSt.setDouble(5, ovChipkaart.getKaartNummer());
            pSt.executeQuery();

            pSt.close();

            for (Product product : ovChipkaart.getProducten()) {
                PreparedStatement pStP = conn.prepareStatement("UPDATE ov_chipkaart_product SET kaart_nummer = ?, " +
                        "product_nummer = ?, status = ?, last_update = ?");
                pStP.setDouble(1, product.getOvChipkaart().getKaartNummer());
                pStP.setInt(2, product.getProductNummer());
                pStP.setString(3, null);
                pStP.setDate(4, new Date(System.currentTimeMillis()));
                pStP.executeQuery();

                pStP.close();
            }
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
            pSt.setDouble(1, ovChipkaart.getKaartNummer());
            pSt.executeQuery();

            pSt.close();

            for (Product product : ovChipkaart.getProducten()) {
                PreparedStatement pStP = conn.prepareStatement("DELETE FROM ov_chipkaart_product " +
                        "WHERE product_nummer = ?");
                pStP.setInt(1, product.getProductNummer());
                pStP.executeQuery();

                pStP.close();
            }
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

                OVChipkaart ovChipkaart = new OVChipkaart(myRS.getDouble("kaart_nummer"),
                        myRS.getDate("geldig_tot"),
                        myRS.getInt("klasse"),
                        myRS.getDouble("saldo"),
                        new ReizigerDAOPsql(conn).findById(myRS.getInt("reiziger_id")),
                        pdao.findByOVChipkaart(myRS.getDouble("kaart_nummer")));

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
    public OVChipkaart findByNummer(double nummer) {
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?");
            pSt.setDouble(1, nummer);
            ResultSet myRS = pSt.executeQuery();

            if (myRS.next()) {
                return new OVChipkaart(myRS.getDouble("kaart_nummer"),
                        myRS.getDate("geldig_tot"),
                        myRS.getInt("klasse"),
                        myRS.getDouble("saldo"),
                        new ReizigerDAOPsql(conn).findById(myRS.getInt("reiziger_id")),
                        pdao.findByOVChipkaart(myRS.getDouble("kaart_nummer")));
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

                OVChipkaart ovChipkaart = new OVChipkaart(myRS.getDouble("kaart_nummer"),
                        myRS.getDate("geldig_tot"),
                        myRS.getInt("klasse"),
                        myRS.getDouble("saldo"),
                        new ReizigerDAOPsql(conn).findById(myRS.getInt("reiziger_id")),
                        pdao.findByOVChipkaart(myRS.getDouble("kaart_nummer")));

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
