import java.util.List;

public interface ReizigerDAO {
    boolean save(Reiziger reiziger);

    boolean update(Reiziger reiziger);

    boolean delete(Reiziger reiziger);

    Reiziger findById(int ID);

    List<Reiziger> findByGbdatum(String datum);

    List<Reiziger> findAll();

    Adres findAdresByReizigerId(Reiziger reiziger);
}
