import controller.CoderController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CoderController coderController = new CoderController();
        String option = "";
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
            option = JOptionPane.showInputDialog(null,message);
            if (option == null){
                break;
            }

            switch (option){
                case "1":
                    coderController.getAll();
                    break;
                case "2":
                    coderController.createCoder();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                default:
                    break;
            }

        }while (option.equals("7"));
    }
}