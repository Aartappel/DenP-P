import java.util.List;

public interface ProductDAO {
    boolean save(Product product);

    boolean update(Product product);

    boolean delete(Product product);

    Product findByNummer(int nummer);

    List<Product> findByOVChipkaart(double nummer);

    List<Product> findAll();
}
