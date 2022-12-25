package controllers;

import database.entities.User;
import models.UserUpdateModel;
import utils.AppStarter;
import utils.Users;
import views.UserUpdateView;

public class UserUpdateController {

    UserUpdateModel model;
    UserUpdateView view;
    User user;

    public UserUpdateController(UserUpdateModel model, UserUpdateView view) {
        this.model = model;
        this.view = view;
    }

    public void updateUser() {
        // Получаем входные данные.
        user = view.doInputs();
        // Передаем данные на обработку и получаем ответ.
        String str = model.updateUser(user);
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
