package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection connection = ConfigDB.openConnection();
        Coder coder = (Coder) object;
        String sql = "INSERT INTO coder(name,age,clan) values(?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,coder.getName());
            ps.setInt(2,coder.getAge());
            ps.setString(3,coder.getClan());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                coder.setId(rs.getInt(1));
            }
            ps.close();
            JOptionPane.showMessageDialog(null, "Coder added successfully");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "The database doesn't connect "+e.getMessage());
        }
        ConfigDB.closeConnection();
        return coder;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public List<Object> findAll() {
        Connection connection = ConfigDB.openConnection();
        List<Object> coders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM coder ORDER BY coder.id ASC;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Coder coder = new Coder();
                coder.setId(rs.getInt("id"));
                coder.setName(rs.getString("name"));
                coder.setAge(rs.getInt("age"));
                coder.setName(rs.getString("clan"));
                coders.add(coder);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The database doesn't connect");
        }
        ConfigDB.closeConnection();
        return coders;
    }

    @Override
    public Object findById(int id) {
        Connection connection = ConfigDB.openConnection();
        StringBuilder sql = new StringBuilder();
        Coder coder = null;
        try{
            sql.append("SELECT * from coder where id=").append(id).append(";");
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                coder = new Coder(rs.getInt("id"),rs.getString("name"),rs.getInt("age"), rs.getString("clan"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "The database doesn't connect");
        }
        ConfigDB.closeConnection();
        return coder;
    }

    @Override
    public Object findByName(String name) {
        Connection connection = ConfigDB.openConnection();
        StringBuilder sql = new StringBuilder();
        Coder coder = null;
        try{
            sql.append("SELECT * from coder where name=").append(name).append(";");
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                coder = new Coder(rs.getInt("id"),rs.getString("name"),rs.getInt("age"), rs.getString("clan"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "The database doesn't connect");
        }
        ConfigDB.closeConnection();
        return coder;
    }
}
