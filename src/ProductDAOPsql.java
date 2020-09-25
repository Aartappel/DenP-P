import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    private Connection conn;

    public ProductDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Product product) {
        try {
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?)");
            pSt.setInt(1, product.getProductNummer());
            pSt.setString(2, product.getNaam());
            pSt.setString(3, product.getBeschrijving());
            pSt.setDouble(4, product.getPrijs());
            pSt.executeQuery();

            pSt.close();

            PreparedStatement pStOV = conn.prepareStatement("INSERT INTO ov_chipkaart_product VALUES (?, ?, ?, ?)");
            pStOV.setDouble(1, product.getOvChipkaart().getKaartNummer());
            pStOV.setInt(2, product.getProductNummer());
            pStOV.setString(3, null);
            pStOV.setDate(4, new Date(System.currentTimeMillis()));
            pStOV.executeQuery();

            pStOV.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try {
            PreparedStatement pSt = conn.prepareStatement("UPDATE product SET naam = ?, beschrijving = ?, " +
                    "prijs = ? WHERE product_nummer = ?");
            pSt.setString(1, product.getNaam());
            pSt.setString(2, product.getBeschrijving());
            pSt.setDouble(3, product.getPrijs());
            pSt.setInt(4, product.getProductNummer());
            pSt.executeQuery();

            pSt.close();

            PreparedStatement pStOV = conn.prepareStatement("UPDATE ov_chipkaart_product SET kaart_nummer = ?, " +
                    "product_nummer = ?, status = ?, last_update = ?");
            pStOV.setDouble(1, product.getOvChipkaart().getKaartNummer());
            pStOV.setInt(2, product.getProductNummer());
            pStOV.setString(3, null);
            pStOV.setDate(4, new Date(System.currentTimeMillis()));
            pStOV.executeQuery();

            pStOV.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        try {
            PreparedStatement pSt = conn.prepareStatement("DELETE FROM product WHERE product_nummer = ?");
            pSt.setInt(1, product.getProductNummer());
            pSt.executeQuery();

            pSt.close();

            PreparedStatement pStOV = conn.prepareStatement("DELETE FROM ov_chipkaart_product " +
                    "WHERE product_nummer = ?");
            pStOV.setInt(1, product.getProductNummer());
            pStOV.executeQuery();

            pStOV.close();
            return true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public Product findByNummer(int nummer) {
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM product WHERE product_nummer = ?");
            pSt.setInt(1, nummer);
            ResultSet myRS = pSt.executeQuery();

            if (myRS.next()) {
                return new Product(myRS.getInt("product_nummer"),
                        myRS.getString("naam"),
                        myRS.getString("beschrijving"),
                        myRS.getDouble("prijs"));
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
    public List<Product> findByOVChipkaart(double nummer) {
        List<Product> producten = new ArrayList<>();
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM product " +
                    "INNER JOIN ov_chipkaart_product AS ocp ON product.product_nummer = ocp.product_nummer " +
                    "WHERE ocp.kaart_nummer = ?");
            pSt.setDouble(1, nummer);
            ResultSet myRS = pSt.executeQuery();

            while (myRS.next()) {
                producten.add(this.findByNummer(myRS.getInt("product_nummer")));
                Product product = new Product(myRS.getInt("product.product_nummer"),
                        myRS.getString("product.naam"),
                        myRS.getString("product.beschrijving"),
                        myRS.getDouble("product.prijs"));

                producten.add(product);
            }

            myRS.close();
            pSt.close();
            return producten;

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> producten = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet myRS = stmt.executeQuery("SELECT * FROM product");

            while (myRS.next()) {
                Product product = new Product(myRS.getInt("product_nummer"),
                        myRS.getString("naam"),
                        myRS.getString("beschrijving"),
                        myRS.getDouble("prijs"));

                producten.add(product);
            }

            myRS.close();
            stmt.close();
            return producten;

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
