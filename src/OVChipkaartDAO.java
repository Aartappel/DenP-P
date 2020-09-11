import java.util.List;

public interface OVChipkaartDAO {

    boolean save(OVChipkaart ovChipkaart);

    boolean update(OVChipkaart ovChipkaart);

    boolean delete(OVChipkaart ovChipkaart);

    List<OVChipkaart> findByReiziger(Reiziger reiziger);

    OVChipkaart findByNummer(int ID);

    List<OVChipkaart> findAll();
}
