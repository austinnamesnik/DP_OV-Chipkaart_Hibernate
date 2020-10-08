package dao;

import domein.OVChipkaart;
import domein.Reiziger;
import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaart ovChipkaart);
    boolean update(OVChipkaart ovChipkaart);
    boolean delete(OVChipkaart ovChipkaart);
    OVChipkaart findByKaartnummer(int kaartnummer);
    List<OVChipkaart> findAll();
    List<OVChipkaart> findByReiziger(Reiziger reiziger);
}
