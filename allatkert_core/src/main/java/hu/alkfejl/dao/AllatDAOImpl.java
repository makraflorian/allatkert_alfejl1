package hu.alkfejl.dao;

import hu.alkfejl.config.ConnectConfig;
import hu.alkfejl.model.Allat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AllatDAOImpl implements AllatDAO{

    private static final String DELETE_ALLAT = "DELETE FROM ALLAT WHERE id=?";
    private static final String SELECT_ALL_ALLAT = "SELECT * FROM ALLAT";
    private static final String INSERT_ALLAT = "INSERT INTO ALLAT (name, faj, pic, bemutat, dateOfBirth) VALUES (?,?,?,?,?)";
    private static final String UPDATE_ALLAT = "UPDATE ALLAT SET name=?, faj=?, pic=?, bemutat=?, dateOfBirth=? WHERE id=?";
    private static final String IS_OROKBEFOGADOTT = "SELECT ALLAT.id, ALLAT.name, ALLAT.faj, ALLAT.pic, ALLAT.bemutat, ALLAT.dateOfBirth FROM ALLAT, OROKBEFOGAD WHERE ALLAT.id=OROKBEFOGAD.allat_id GROUP BY ALLAT.id";
    private static final String NOT_OROKBEFOGADOTT = "SELECT ALLAT.id, ALLAT.name, ALLAT.faj, ALLAT.pic, ALLAT.bemutat, ALLAT.dateOfBirth FROM ALLAT, OROKBEFOGAD WHERE ALLAT.id NOT IN (SELECT allat_id FROM OROKBEFOGAD) GROUP BY ALLAT.id";
    private static final String FIND_BY_ID = "SELECT  * FROM ALLAT WHERE id=?";
    private static final String CASCADEXD = "PRAGMA foreign_keys = ON;";
    private static final String NO_NAME_NOT_OROKBEFOGADOTT = "SELECT ALLAT.id, ALLAT.name, ALLAT.faj, ALLAT.pic, ALLAT.bemutat, ALLAT.dateOfBirth FROM ALLAT, OROKBEFOGAD WHERE ALLAT.id NOT IN (SELECT allat_id FROM OROKBEFOGAD) AND ALLAT.name is null GROUP BY ALLAT.id";
    private static String connection_URL;

    public AllatDAOImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection_URL = ConnectConfig.getValue("db.url");
    }

    @Override
    public List<Allat> listAll() {
        List<Allat> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connection_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_ALLAT);

        ){
            while(rs.next()){
                Allat allat = new Allat();
                allat.setId(rs.getInt("id"));
                allat.setName(rs.getString("name"));
                allat.setFaj(rs.getString("faj"));
                allat.setPic(rs.getString("pic"));
                allat.setBemutat(rs.getString("bemutat"));
                allat.setDateOfBirth(rs.getString("dateOfBirth"));

                result.add(allat);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public List<Allat> orokbefogadottAllat(){

        List<Allat> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connection_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(IS_OROKBEFOGADOTT);
        ){
            while(rs.next()){
                Allat allat = new Allat();
                allat.setId(rs.getInt("id"));
                allat.setName(rs.getString("name"));
                allat.setFaj(rs.getString("faj"));
                allat.setPic(rs.getString("pic"));
                allat.setBemutat(rs.getString("bemutat"));
                allat.setDateOfBirth(rs.getString("dateOfBirth"));

                result.add(allat);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public List<Allat> nemOrokbefogadottAllat(){

        List<Allat> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connection_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(NOT_OROKBEFOGADOTT);
        ){
            while(rs.next()){
                Allat allat = new Allat();
                allat.setId(rs.getInt("id"));
                allat.setName(rs.getString("name"));
                allat.setFaj(rs.getString("faj"));
                allat.setPic(rs.getString("pic"));
                allat.setBemutat(rs.getString("bemutat"));
                allat.setDateOfBirth(rs.getString("dateOfBirth"));

                result.add(allat);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public List<Allat> noNameNemOrokbefogadottAllat(){

        List<Allat> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connection_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(NO_NAME_NOT_OROKBEFOGADOTT);
        ){
            while(rs.next()){
                Allat allat = new Allat();
                allat.setId(rs.getInt("id"));
                allat.setName(rs.getString("name"));
                allat.setFaj(rs.getString("faj"));
                allat.setPic(rs.getString("pic"));
                allat.setBemutat(rs.getString("bemutat"));
                allat.setDateOfBirth(rs.getString("dateOfBirth"));

                result.add(allat);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Allat save(Allat allat) {
        try(Connection c = DriverManager.getConnection(connection_URL);
        ){
            PreparedStatement stmt;

            if (allat.getId() <= 0){
                stmt = c.prepareStatement(INSERT_ALLAT, Statement.RETURN_GENERATED_KEYS);
            }
            else{
                stmt = c.prepareStatement(UPDATE_ALLAT);
                stmt.setInt(6, allat.getId());
            }

            stmt.setString(1, allat.getName());
            stmt.setString(2, allat.getFaj());
                stmt.setString(3, allat.getPic());
            stmt.setString(4, allat.getBemutat());
            stmt.setString(5, allat.getDateOfBirth());

            int affected_rows = stmt.executeUpdate();
            if (affected_rows == 0){
                return null;
            }

            if(allat.getId() <= 0){//insert
                ResultSet genkeys = stmt.getGeneratedKeys();
                if(genkeys.next()){
                    allat.setId(genkeys.getInt(1));
                }
            }

            stmt.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allat;
    }

    @Override
    public List<Allat> getAllatById(int allat_id) {
        List<Allat> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connection_URL);
            PreparedStatement stmt = c.prepareStatement(FIND_BY_ID);
        ){
            stmt.setInt(1, allat_id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Allat allat = new Allat();
                allat.setId(rs.getInt("id"));
                allat.setName(rs.getString("name"));
                allat.setFaj(rs.getString("faj"));
                allat.setPic(rs.getString("pic"));
                allat.setBemutat(rs.getString("bemutat"));
                allat.setDateOfBirth(rs.getString("dateOfBirth"));

                result.add(allat);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public void delete(Allat allat) {

        try(Connection c = DriverManager.getConnection(connection_URL);
            PreparedStatement cascade = c.prepareStatement(CASCADEXD);
            PreparedStatement stmt = c.prepareStatement(DELETE_ALLAT);
        ){
            cascade.executeUpdate();
            stmt.setInt(1, allat.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
