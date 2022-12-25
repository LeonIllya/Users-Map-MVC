package views;

import database.entities.User;

import java.util.Scanner;

public class UserDeleteView {
    public User doInput() {

        Scanner scanner = new Scanner(System.in);
        User user = new User();

        // Ввод и валидация данных
        String title = "Enter user's ID: ";
        System.out.print(title);
        // Санитизация через trim()
        // Преобразование String в int
        // Передача в setter
        user.setId(Integer.parseInt(scanner.nextLine().trim()));
//        user.setId(scanner.nextInt());

        return user;
    }

    // Вывод уведомления
    public void getOutput(String output) {
        System.out.println(output);
    }
}
