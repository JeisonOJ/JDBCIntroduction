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
            ps.close();
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

        try {
            String sql = "UPDATE coder SET name = ?, age = ?, clan = ? WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, coder.getName());
            ps.setInt(2, coder.getAge());
            ps.setString(3, coder.getClan());
            ps.setInt(4, coder.getId());
            return ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The database doesn't connect " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Connection connection = ConfigDB.openConnection();
        Coder coder = (Coder) object;
        try {
            String sql = "DELETE FROM coder WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, coder.getId());
            ps.execute();
            System.out.println(ps.execute());
            ps.close();
            ConfigDB.closeConnection();
            return ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The database doesn't connect " + e.getMessage());
        }
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
                ps.close();
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
            sql=("SELECT * FROM coder WHERE id=?");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coder = new Coder(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("clan"));
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The database doesn't connect");
        }
        ConfigDB.closeConnection();
        return coder;
    }

    @Override
    public Object findByName(String name) {
        Connection connection = ConfigDB.openConnection();
        String sql;
        Coder coder = null;
        try {
            sql = ("SELECT * from coder where name=?;");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coder = new Coder(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("clan"));
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The database doesn't connect");
        }
        ConfigDB.closeConnection();
        return coder;
    }
}
