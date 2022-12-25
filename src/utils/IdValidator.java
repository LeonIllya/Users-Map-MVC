package utils;

import database.DBConn;
import database.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdValidator {
    // проверка наличия ID в БД
    public static boolean isIdExists(User users) {

        try {
            String query = "SELECT COUNT(id) FROM " + Users.TABLE_NAME + " WHERE id = ?";
            PreparedStatement pst = DBConn.connect().prepareStatement(query);
            pst.setInt(1, users.getId());

            try (ResultSet rs = pst.executeQuery()) {
                // Ожидаем только один результат
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
