package models;

import database.DBCheck;
import database.DBConn;
import database.entities.User;
import utils.IdValidator;
import utils.Users;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDeleteModel {

    public String deleteUser(User user) {
        // Проверяем на наличие файла БД.
        // Если ДА, удаляем данные и уведомляем об этом,
        // НЕТ - уведомление об отсутствии БД.
        if (DBCheck.isDBExists()) {
            return deleteData(user);
        } else {
            return Users.DB_ABSENT_MSG;
        }
    }

    // Удаление по id, при условии его наличия в БД.
    // Если ДА - удаляем и выводим сообщение об удалении,
    // НЕТ - выводим сообщение об отсутствии id.
    private String deleteData(User user) {

        if (IdValidator.isIdExists(user)) {

            String sql = "DELETE FROM " + Users.TABLE_NAME + " WHERE id = ?";

            try (PreparedStatement stmt = DBConn.connect().prepareStatement(sql)) {
                // установка соответствующих параметров
                stmt.setInt(1, user.getId());
                // выполнение запроса в БД
                stmt.executeUpdate();
                return Users.DATA_DELETE_MSG;
            } catch (SQLException e) {
                return e.getMessage();
            }
        } else {
            return Users.ID_NO_EXISTS_MSG;
        }
    }
}
