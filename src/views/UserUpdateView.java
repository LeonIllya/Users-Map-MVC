package views;

import database.entities.User;

import java.util.Scanner;

public class UserUpdateView {
    User user;
    String title;
    Scanner scanner;

    public User doInputs() {

        scanner = new Scanner(System.in);
        user = new User();

        title = "Enter user's ID: ";
        System.out.print(title);
        user.setId(Integer.parseInt(scanner.nextLine().trim()));
//        user.setId(scanner.nextInt());

        title = "Enter new email: ";
        System.out.print(title);
        user.setEmail(scanner.nextLine().trim());

        return user;
    }

    public void getOutput(String output) {
        System.out.println(output);
    }
}
