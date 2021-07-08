package hu.alkfejl.dao;

import hu.alkfejl.model.Allat;
import java.util.List;

public interface AllatDAO {

    //állatokat hozzáadni, szerkeszteni, törölni, listázni

    List<Allat> listAll();

    public List<Allat> orokbefogadottAllat();

    public List<Allat> nemOrokbefogadottAllat();

    Allat save(Allat allat);

    public List<Allat> getAllatById(int allat_id);

    public List<Allat> noNameNemOrokbefogadottAllat();

    void delete(Allat allat);
}
