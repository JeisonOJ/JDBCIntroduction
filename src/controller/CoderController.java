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

    public void getCoderByName(String name){
        JOptionPane.showMessageDialog(null, coderModel.findByName(name).toString());
    }
    public void getCoderById(int id){
        JOptionPane.showMessageDialog(null, coderModel.findById(id).toString());
    }
}
