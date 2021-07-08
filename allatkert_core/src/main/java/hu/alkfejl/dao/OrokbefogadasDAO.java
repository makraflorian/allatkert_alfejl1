package hu.alkfejl.dao;


import hu.alkfejl.model.Orokbefogadas;
import java.util.List;

public interface OrokbefogadasDAO {

    //az örökbefogadásokat listázni, szűrni

    List<Orokbefogadas> listAll();

    Orokbefogadas save(Orokbefogadas orokbefogadas);

}
