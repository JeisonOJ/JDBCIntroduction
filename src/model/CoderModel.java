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
        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, coder.getName());
            ps.setInt(2, coder.getAge());
            ps.setString(3, coder.getClan());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                coder.setId(rs.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Coder added successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The database doesn't connect " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return coder;
    }

    @Override
    public boolean update(Object object) {
        Connection connection = ConfigDB.openConnection();
        Coder coder = (Coder) object;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE coder SET name = ?, age = ?, clan = ? WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, coder.getName());
            ps.setInt(2, coder.getAge());
            ps.setString(3, coder.getClan());
            ps.setInt(4, coder.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Coder Updated successfully");

                isUpdate = true;
            } else {
                JOptionPane.showMessageDialog(null, "The coder doesn't exist");

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The database doesn't connect " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        System.out.println(object);
        Connection connection = ConfigDB.openConnection();
        Coder coder = (Coder) object;
        System.out.println(coder);
        boolean isDelete = false;
        System.out.println("Entramos a delete");
        try {
            System.out.println("Entramos a delete al try");

            String sql = "DELETE FROM coder WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            System.out.println("id a eliminar: " + coder.getId());
            ps.setInt(1, coder.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Coder deleted successfully");
                isDelete = true;
            } else {
                JOptionPane.showMessageDialog(null, "The coder doesn't exist");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DELETE: The database doesn't connect " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
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
                coder.setClan(rs.getString("clan"));
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
        String sql;
        Coder coder = null;
        try {
            sql = ("SELECT * FROM coder WHERE id=?;");
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coder = new Coder(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("clan"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FINDBYID: The database doesn't connect");
        }
        ConfigDB.closeConnection();
        return coder;
    }

    @Override
    public Object findByName(String name) {
        Connection connection = ConfigDB.openConnection();
        String sql;
        ArrayList<Coder> coders = new ArrayList<>();

        try {
            sql = "SELECT * from coder where name like ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"'%"+name+"%'");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coders.add(new Coder(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("clan")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FINDBYNAME: The database doesn't connect");
        }
        ConfigDB.closeConnection();
        return coders;
    }
}
