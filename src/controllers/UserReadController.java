package controllers;

import models.UserReadModel;
import utils.AppStarter;
import utils.Users;
import views.UserReadView;

public class UserReadController {

    UserReadModel model;
    UserReadView view;

    public UserReadController(UserReadModel model, UserReadView view) {
        this.model = model;
        this.view = view;
    }

    public void readUsers() {
        // Передаем запрос и получаем ответ.
        String str = model.readUsers();
        // Проверяем возврат чтения данных.
        // Если БД отсутствует, выводим сообщение об этом
        // и закрываем приложение.
        // Иначе выводим сообщение и перезапускаем приложение.
        if (str.equals(Users.DB_ABSENT_MSG)) {
            // Выводим уведомление.
            view.getOutput(str);
            // Закрываем приложение.
            System.exit(0);
        } else {
            // Выводим уведомление или данные.
            view.getOutput("\n______ CONTACTS ___________\n" + str);
            // Перезапускаем приложение.
            AppStarter.startApp();
        }
    }
}
