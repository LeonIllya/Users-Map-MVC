package models;

import database.DBCheck;
import database.DBConn;
import database.entities.User;
import exceptions.DBException;
import utils.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserReadModel {

    public String readUsers() {
        // Проверяем на наличие файла БД.
        // ДА - работаем с данными.
        // НЕТ - уведомление об отсутствии БД.
        if (!DBCheck.isDBExists()) {
            try {
                throw new DBException(Users.DB_ABSENT_MSG);
            } catch (DBException e) {
                return e.getMessage();
            }
        }

        // Получаем данные в коллекцию.
        Map<Integer, String> users = getUsers();

        // Если коллекция не null, формируем вывод.
        // Иначе уведомление об отсутствии данных.
        if (users != null) {
            // Если коллекция не пуста, формируем вывод.
            // Иначе уведомление об отсутствии данных.
            if (!users.isEmpty()) {
                AtomicInteger count = new AtomicInteger(0);
                StringBuilder stringBuilder = new StringBuilder();
                users.forEach((key, value) ->
                        stringBuilder.append(count.incrementAndGet())
                                .append(") ")
                                .append(key)
                                .append(" ")
                                .append(value)
                                .append("\n")
                );
                return stringBuilder.toString();
            } else return Users.DATA_ABSENT_MSG;
        } else return Users.DATA_ABSENT_MSG;
    }


    public Map<Integer, String> getUsers() {

        try (Statement stmt = DBConn.connect().createStatement()) {

            Map<Integer, String> map = new HashMap<>();

            String sql = "SELECT id, name, email FROM " + Users.TABLE_NAME;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                int id = rs.getInt("id");
                user.setId(id);
                String name = rs.getString("name");
                user.setName(name);
                String email = rs.getString("email");
                user.setEmail(email);

                String userData = user.getName() + " " + user.getEmail();
                map.put(user.getId(), userData);
            }
            // Возвращаем коллекцию данных
            return map;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Если ошибка - возвращаем пустую map
            return Collections.emptyMap();
        }
    }
}
