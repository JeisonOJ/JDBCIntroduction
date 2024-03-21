import controller.CoderController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CoderController coderController = new CoderController();
        String option;
        String message = """
                ...::CODERS MENU::...
                1. List Coders.
                2. Insert Coder.
                3. Update Coder.
                4. Delete Coder.
                5. Get By name.
                6. Get By id.
                7. Exit
                """;
        do {
            option = JOptionPane.showInputDialog(null, message);
            if (option == null) {
                break;
            }

            switch (option) {
                case "1":
                    coderController.getAll();
                    break;
                case "2":
                    coderController.createCoder();
                    break;
                case "3":
                    coderController.updateCoder();
                    break;
                case "4":
                    coderController.deleteCoder();
                    break;
                case "5":
                    coderController.getCoderByName();
                    break;
                case "6":
                    coderController.getCoderById();
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null, "You are leaving the menu...");
                    break;
                default:
                    break;
            }

        } while (option.equals("7"));
    }
}