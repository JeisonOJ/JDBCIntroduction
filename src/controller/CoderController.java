package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.util.List;

public class CoderController {
    private CoderModel coderModel;

    public CoderController() {
        coderModel = new CoderModel();
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll(coderModel.findAll()));
    }

    public String getAll(List objectList) {
        StringBuilder message = new StringBuilder();
        message.append("Coders List\n");
        for (Object obj : objectList) {
            Coder coder = (Coder) obj;
            message.append(coder.toString()).append("\n");
        }
        return message.toString();
    }

    public void createCoder() {
        Coder coder = new Coder();
        String name = JOptionPane.showInputDialog(null, "Enter coder name");
        int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter coder age"));
        String clan = JOptionPane.showInputDialog(null, "Enter coder clan");

        coder.setName(name);
        coder.setAge(age);
        coder.setClan(clan);
        coder = (Coder) coderModel.insert(coder);
        JOptionPane.showMessageDialog(null, coder.toString());
    }

    public void updateCoder() {
        getAll();
        int toUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the coder id to update"));
        Coder coder = (Coder) coderModel.findById(toUpdate);
        if (coder != null){
            String name = JOptionPane.showInputDialog(null, "Enter new coder name",coder.getName());
//            int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new coder age",coder.getAge()));
            int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new coder age",String.valueOf(coder.getAge())));
            String clan = JOptionPane.showInputDialog(null, "Enter new coder clan",coder.getClan());

            coder.setName(name);
            coder.setAge(age);
            coder.setClan(clan);
            if (coderModel.update(coder)) {
                JOptionPane.showMessageDialog(null, coder.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Coder updated fail");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Coder not found");
        }
    }


    public void getCoderByName() {
        String search = JOptionPane.showInputDialog(null, "Enter the name to search");
        JOptionPane.showMessageDialog(null, coderModel.findByName(search));
    }

    public void getCoderById() {
        String toSearch = JOptionPane.showInputDialog(null, "Enter the id to search");
        int id = Integer.parseInt(toSearch);
        JOptionPane.showMessageDialog(null, coderModel.findById(id));
    }

    public void deleteCoder() {
        getAll();
        int toDelete = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the coder id to delete"));
        if (coderModel.delete(coderModel.findById(toDelete))) {
            JOptionPane.showMessageDialog(null, "Coder deleted successfully");
            return;
        }
        JOptionPane.showMessageDialog(null, "Coder deleted fail");
    }
}
