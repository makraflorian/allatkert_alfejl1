package hu.alkfejl.dao;

import hu.alkfejl.config.ConnectConfig;
import hu.alkfejl.model.Orokbefogado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrokbefogadoDAOImpl implements OrokbefogadoDAO{

    private static final String SELECT_ALL_OROKBEFOGADO = "SELECT * FROM OROKBEFOGADO";
    private static final String EDIT_OROKBEFOGADO = "UPDATE OROKBEFOGADO SET name=?, email=? WHERE id=?";
    private static final String DELETE_OROKBEFOGADO = "DELETE FROM OROKBEFOGADO WHERE id=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM OROKBEFOGADO WHERE email=?";
    private static final String INSERT_OROKBEFOGADO = "INSERT INTO OROKBEFOGADO (name, email) VALUES (?,?)";
    private static final String CASCADEXD = "PRAGMA foreign_keys = ON;";
    private static String connection_URL;

    public OrokbefogadoDAOImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection_URL = ConnectConfig.getValue("db.url");
    }

    @Override
    public List<Orokbefogado> listAll() {

        List<Orokbefogado> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connection_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_OROKBEFOGADO);
        ){
            while(rs.next()){
                Orokbefogado orokbefogado = new Orokbefogado();
                orokbefogado.setId(rs.getInt("id"));
                orokbefogado.setName(rs.getString("name"));
                orokbefogado.setEmail(rs.getString("email"));

                result.add(orokbefogado);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Orokbefogado edit(Orokbefogado orokbefogado) {

        try(Connection c = DriverManager.getConnection(connection_URL);
            PreparedStatement stmt = c.prepareStatement(EDIT_OROKBEFOGADO);
            ){

            stmt.setString(1, orokbefogado.getName());
            stmt.setString(2, orokbefogado.getEmail());
            stmt.setInt(3, orokbefogado.getId());

            int affected_rows = stmt.executeUpdate();
            if (affected_rows == 0){
                return null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return orokbefogado;
    }

    @Override
    public List<Orokbefogado> getOrokbefogadoByEmail(String orokbefogado_email) {

        List<Orokbefogado> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connection_URL);
            PreparedStatement stmt = c.prepareStatement(FIND_BY_EMAIL);
        ){

            stmt.setString(1, orokbefogado_email);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Orokbefogado orokbefogado = new Orokbefogado();
                orokbefogado.setId(rs.getInt("id"));
                orokbefogado.setName(rs.getString("name"));
                orokbefogado.setEmail(rs.getString("email"));

                result.add(orokbefogado);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Orokbefogado save(Orokbefogado orokbefogado) {

        try(Connection c = DriverManager.getConnection(connection_URL);
            PreparedStatement stmt = c.prepareStatement(INSERT_OROKBEFOGADO);
        ){
            stmt.setString(1, orokbefogado.getName());
            stmt.setString(2, orokbefogado.getEmail());

            int affected_rows = stmt.executeUpdate();
            if (affected_rows == 0){
                return null;
            }

            if(orokbefogado.getId() <= 0){
                ResultSet genkeys = stmt.getGeneratedKeys();
                if(genkeys.next()){
                    orokbefogado.setId(genkeys.getInt(1));
                }
            }

            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return orokbefogado;
    }

    @Override
    public void delete(Orokbefogado orokbefogado) {

        try(Connection c = DriverManager.getConnection(connection_URL);
            PreparedStatement cascade = c.prepareStatement(CASCADEXD);
            PreparedStatement stmt = c.prepareStatement(DELETE_OROKBEFOGADO);
        ){
            cascade.executeUpdate();
            stmt.setInt(1, orokbefogado.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
