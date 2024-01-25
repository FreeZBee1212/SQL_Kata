package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {


        UserServiceImpl userService = new UserServiceImpl();

        userService.dropUsersTable();           // 1) удаление таблицы
        userService.createUsersTable();         // 2) создание таблицы
        userService.saveUser("Ivan", "Ivanov", (byte) 22);
        userService.saveUser("Элвис", "Гресли", (byte) 25);
        userService.saveUser("Petya", "Gipopotam", (byte) 22);
        userService.saveUser("Johny", "Tree", (byte) 25);
        System.out.println(userService.getAllUsers());             // 3) вывод в консоль всех юзеров
//        userService.cleanUsersTable();         // 4) удаление данных в таблице
//        userService.dropUsersTable();          // 5) удаление таблицы


    }
}





