package models;

import database.DBCheck;
import database.DBConn;
import database.entities.User;
import exceptions.CreateException;
import exceptions.UpdateException;
import utils.IdValidator;
import utils.Users;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserUpdateModel {

    public String updateUser(User user) {

        // Проверяем на наличие данных.
        // Если ДА, вносим данные и уведомляем об этом,
        // НЕТ - уведомление об отсутствии данных.
        if (user.getId() == 0 || user.getEmail().isEmpty()) {
            try {
                throw new UpdateException(Users.CHECK_INPUTS_MSG);
            } catch (CreateException e) {
                return e.getMessage();
            }
        }

        // Проверяем на наличие файла БД.
        // Если ДА, обновляем данные и уведомляем об этом,
        // НЕТ - уведомление об отсутствии БД.
        if (DBCheck.isDBExists()) {
            return updateData(user);
        } else {
            return Users.DB_ABSENT_MSG;
        }
    }

    // Обновление по id, при условии его наличия в БД.
    // Если ДА - обновляем и выводим сообщение об удалении,
    // НЕТ - выводим сообщение об отсутствии id.
    private String updateData(User user) {

        if (IdValidator.isIdExists(user)) {

            String sql = "UPDATE " + Users.TABLE_NAME + " SET email = ? WHERE id = ?";
            // PreparedStatement - подготовленное выражение, чтобы избежать SQL-инъекций
            try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {
                pstmt.setString(1, user.getEmail());
                pstmt.setInt(2, user.getId());
                pstmt.executeUpdate();
                return Users.DATA_UPDATE_MSG;
            } catch (SQLException e) {
                return e.getMessage();
            }
        } else {
            return Users.ID_NO_EXISTS_MSG;
        }
    }
}
