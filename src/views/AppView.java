package views;

import models.AppModel;
import utils.OptionValidator;

import java.util.Scanner;

public class AppView {
    AppModel model;
    Scanner scanner;
    int choice;

    public AppView(AppModel model) {
        this.model = model;
    }

    public int doChoice() {

        int option;
        scanner = new Scanner(System.in);

        System.out.print("""
                
                ______ MENU ___________
                1 - Create a user.
                2 - View users.
                3 - Update the users email.
                4 - Delete user.
                0 - Close the App.
                """);

        choice = OptionValidator.validateOption(scanner);
        model.setChoice(choice);
        option = model.getChoice();

        return option;
    }

    public void getOutput(String output, int choice) {
        if (choice == 0) System.out.println(output);
        scanner.close();
        System.exit(0);
    }
}
