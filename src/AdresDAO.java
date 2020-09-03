import java.util.List;

public interface AdresDAO {
    boolean save(Adres adres);

    boolean update(Adres adres);

    boolean delete(Adres adres);

    Adres findByReiziger(Reiziger reiziger);

    Adres findById(int ID);

    List<Adres> findAll();
}
