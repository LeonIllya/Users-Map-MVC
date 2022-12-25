package views;

import database.entities.User;

import java.util.Scanner;

public class UserCreateView {
    User user;
    String title;
    Scanner scanner;

    public User doInputs() {

        scanner = new Scanner(System.in);
        user = new User();

        // Ввод данных

        title = "Enter name: ";
        System.out.print(title);
        user.setName(scanner.nextLine().trim());

        title = "Enter email: ";
        System.out.print(title);
        user.setEmail(scanner.nextLine().trim());

        return user;
    }

    // Вывод ответа
    public void getOutput(String output) {
        System.out.println(output);
    }
}
