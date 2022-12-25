package controllers;

import models.UserDeleteModel;
import utils.AppStarter;
import utils.Users;
import views.UserDeleteView;

public class UserDeleteController {

    UserDeleteModel model;
    UserDeleteView view;

    public UserDeleteController(UserDeleteModel model, UserDeleteView view) {
        this.model = model;
        this.view = view;
    }

    public void deleteUser() {

        // Вносим данные и получаем сообщение.
        String str = model.deleteUser(view.doInput());
        // Проверяем сообщение на внесение данных.
        // Если БД отсутствует, выводим сообщение об этом
        // и закрываем приложение.
        // Иначе выводим сообщение и перезапускаем приложение.
        if (str.equals(Users.DB_ABSENT_MSG)) {
            // Выводим уведомление.
            view.getOutput(str);
            // Закрываем приложение.
            System.exit(0);
        } else {
            // Выводим уведомление.
            view.getOutput(str);
            // Перезапускаем приложение.
            AppStarter.startApp();
        }
    }
}
