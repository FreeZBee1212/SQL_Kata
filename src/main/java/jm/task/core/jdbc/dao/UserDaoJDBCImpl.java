package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    Statement state;

    {
        try {
            state = Util.connect().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String create_table = "CREATE TABLE IF NOT EXISTS USER (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(64), lastname VARCHAR(64), age INT(3))";
        try {
            state.execute(create_table);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String drop_table = "DROP TABLE IF EXISTS user";
        try {
            state.execute(drop_table);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        String save = "INSERT INTO USER (name, lastName, age) " +
//                "VALUES (" + name + ", " + lastName +", " + age + ");";
//        try {
//            state.execute(save);
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

    public void removeUserById(long id) {
        String remove_user_ID = "DELETE FROM USER WHERE id = " + id;
        try {
            state.execute(remove_user_ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() throws SQLException {
        String sqlQuery = "SELECT * FROM USER";
        ResultSet resultSet = state.executeQuery(sqlQuery);
        List<User> list = new ArrayList<>();


        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            byte age = resultSet.getByte("age");
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("lastName");

            User user = new User(name, lastName, age);
            list.add(user);
        }


        return list;
    }

    public void cleanUsersTable() {
        String cleaner = "DELETE FROM USER";
        try {
            state.execute(cleaner);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
