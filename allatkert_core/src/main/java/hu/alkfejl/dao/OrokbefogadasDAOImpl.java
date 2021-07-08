package hu.alkfejl.dao;

import hu.alkfejl.config.ConnectConfig;
import hu.alkfejl.model.Orokbefogadas;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OrokbefogadasDAOImpl implements OrokbefogadasDAO{

    private static final String SELECT_ALL_OROKBEFOGADAS = "SELECT OROKBEFOGAD.orokbefogado_id, OROKBEFOGAD.allat_id, OROKBEFOGADO.name AS ember_name, ALLAT.name AS allat_name, OROKBEFOGAD.mikor, OROKBEFOGAD.n_name, OROKBEFOGAD.tipus, OROKBEFOGAD.mennyiseg, OROKBEFOGAD.gyakorisag FROM OROKBEFOGADO, ALLAT, OROKBEFOGAD WHERE OROKBEFOGAD.orokbefogado_id=OROKBEFOGADO.id AND ALLAT.id=OROKBEFOGAD.allat_id";
    private static final String INSERT_OROKBEFOGADAS = "INSERT INTO OROKBEFOGAD (orokbefogado_id, allat_id, mikor, n_name, tipus, mennyiseg, gyakorisag) VALUES (?,?,?,?,?,?,?)";

    private static String connection_URL;

    public OrokbefogadasDAOImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection_URL = ConnectConfig.getValue("db.url");
    }

    @Override
    public Orokbefogadas save(Orokbefogadas orokbefogadas) {

     try(Connection c = DriverManager.getConnection(connection_URL);
        ){
            PreparedStatement stmt;

            stmt = c.prepareStatement(INSERT_OROKBEFOGADAS, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, orokbefogadas.getOrokbefogado_id());
            stmt.setInt(2, orokbefogadas.getAllat_id());
            stmt.setObject(3, orokbefogadas.getMikor());
            stmt.setString(4, orokbefogadas.getN_name());
            stmt.setString(5, orokbefogadas.getTipus());
            stmt.setInt(6, orokbefogadas.getMennyiseg());
            stmt.setString(7, orokbefogadas.getGyakorisag());

            int affected_rows = stmt.executeUpdate();
            if (affected_rows == 0){
                return null;
            }

            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orokbefogadas;
    }

    @Override
    public List<Orokbefogadas> listAll() {

         List<Orokbefogadas> result = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(connection_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_OROKBEFOGADAS);
            ){
            while(rs.next()){
                Orokbefogadas orokbefogadas = new Orokbefogadas();

                orokbefogadas.setOrokbefogado_id(rs.getInt("orokbefogado_id"));
                orokbefogadas.setAllat_id(rs.getInt("allat_id"));
                Date date = Date.valueOf(rs.getString("mikor"));
                orokbefogadas.setMikor(date == null ? LocalDate.now() : date.toLocalDate());
                orokbefogadas.setN_name(rs.getString("n_name"));
                orokbefogadas.setTipus(rs.getString("tipus"));
                orokbefogadas.setMennyiseg(rs.getInt("mennyiseg"));
                orokbefogadas.setGyakorisag(rs.getString("gyakorisag"));

                orokbefogadas.setEmber_name(rs.getString("ember_name"));
                orokbefogadas.setAllat_name(rs.getString("allat_name"));

                result.add(orokbefogadas);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
