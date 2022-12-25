package models;

import database.DBCheck;
import database.DBConn;
import database.entities.User;
import exceptions.CreateException;
import exceptions.DBException;
import utils.Users;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserCreateModel {

    public String createUser(User user) {

        // Проверяем на наличие данных.
        // Если ДА, вносим данные и уведомляем об этом,
        // НЕТ - уведомление об отсутствии данных.
        if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
            try {
                throw new CreateException(Users.CHECK_INPUTS_MSG);
            } catch (CreateException e) {
                return e.getMessage();
            }
        }

        // Проверяем на наличие файла БД.
        // Если ДА, вносим данные и уведомляем об этом,
        // НЕТ - уведомление об отсутствии БД.
        if (!DBCheck.isDBExists()) {
            try {
                throw new DBException(Users.DB_ABSENT_MSG);
            } catch ( DBException e) {
                return e.getMessage();
            }
        }

        return addData(user);
    }

    private String addData(User user) {

        String sql = "INSERT INTO " + Users.TABLE_NAME + "(name, email) VALUES(?, ?)";
        // PreparedStatement - подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
            return Users.DATA_INSERT_MSG;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
