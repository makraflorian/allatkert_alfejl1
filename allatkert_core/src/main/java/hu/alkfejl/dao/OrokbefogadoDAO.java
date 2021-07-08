package hu.alkfejl.dao;

import hu.alkfejl.model.Orokbefogado;
import java.util.List;

public interface OrokbefogadoDAO {


    List<Orokbefogado> listAll();

    List<Orokbefogado> getOrokbefogadoByEmail(String orokbefogado_id);

    Orokbefogado edit(Orokbefogado orokbefogado);

    Orokbefogado save(Orokbefogado orokbefogado);

    void delete(Orokbefogado orokbefogado);
}
