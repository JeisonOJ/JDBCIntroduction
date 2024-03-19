package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;

public class CoderController {
    private CoderModel coderModel;

    public CoderController() {
        coderModel = new CoderModel();
    }

    public void getAll() {
        StringBuilder message = new StringBuilder();
        message.append("Coders List\n");
        for (Object obj : coderModel.findAll()) {
            Coder coder = (Coder) obj;
            message.append(coder.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, message);
    }

    public void createCoder(){
        Coder coder = new Coder();
        String name = JOptionPane.showInputDialog(null,"Enter coder name");
        int age = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter coder age"));
        String clan = JOptionPane.showInputDialog(null,"Enter coder clan");

        coder.setName(name);
        coder.setAge(age);
        coder.setClan(clan);
        coder = (Coder) coderModel.insert(coder);
        JOptionPane.showMessageDialog(null, coder.toString());
    }

    public void getCoderByName(){
        String search = JOptionPane.showInputDialog(null,"Enter the name to search");
        JOptionPane.showMessageDialog(null, coderModel.findByName(search).toString());
    }
    public void getCoderById(){
        String toSearch = JOptionPane.showInputDialog(null,"Enter the id to search");
        int id = Integer.parseInt(toSearch);
        JOptionPane.showMessageDialog(null, coderModel.findById(id).toString());
    }

    public void deleteCoder(){
        String toDelete = JOptionPane.showInputDialog(null,"Enter the coder name to delete");
        if (coderModel.delete(coderModel.findByName(toDelete))){
            JOptionPane.showMessageDialog(null, "Coder deleted successfully");
            return;
        }
        JOptionPane.showMessageDialog(null, "Coder deleted fail");

    }
}
