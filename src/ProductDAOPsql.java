import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
    public List<Product> findByKaartNummer(int nummer) {
        List<Product> producten = new ArrayList<>();
        try {
            PreparedStatement pSt = conn.prepareStatement("SELECT * FROM ov_chipkaart_product WHERE kaart_nummer = ?");
            pSt.setInt(1, nummer);
            ResultSet myRS = pSt.executeQuery();

            while (myRS.next()) {
                producten.add(this.findByNummer(myRS.getInt("product_nummer")));
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
