package com.company.DataBase;

import com.company.Classes.Hall;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    public static Connection connection;
    public static Statement statement;

    public static void createDataBase() {
        try {
            Class.forName("org.sqlite.JDBC"); //Проверяем наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection("jdbc:sqlite:GymDataBase");//соединениесБД
            System.out.println("Соединение с СУБД выполнено.");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }
    public static void createTable(){

        var sql = "CREATE TABLE IF NOT EXISTS Hall (\n" +
                "hallId integer PRIMARY KEY AUTOINCREMENT,\n" +
                "title text\n" +
                ");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Метод для добавления зала
    public static void addHall(String title){
        //Команда для добавления
        String sql = "INSERT INTO Hall (title) VALUES ('"+title+"');";
        try {
            //Стейтмент отправляет команду
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Получить список залов
    public static Object[][] getHall(){
        //Специальный лист, который записывается в таблицу
        ArrayList<Hall> halls =new ArrayList<>();
        //Запрос для выдачи
        String sql = "SELECT * FROM Hall;";
        try {
            //ResultSet хранит данные их таблицы БД
            ResultSet resultSet = statement.executeQuery(sql);
            //Пока есть что записывать
            while (resultSet.next()){
                //Создать новый холл
                var h = new Hall();
                //Присовить холу Название = выдать текст из записи из колоники     //Столбец с id залов
                h.setHallName(resultSet.getString("title"));
                h.setHallId(resultSet.getInt("hallId"));
                //Полжить зал в список
                halls.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object[][] array = new String[halls.size()][2];
        for(int i = 0; i < halls.size(); i++){
            array[i][0] = String.valueOf(halls.get(i).getHallId());
            array[i][1] = halls.get(i).getHallName();
        }
        return array;
    }
    //Найти зал по id
    public static Hall foundHall(int id){
        var hall = new Hall();
        String sql = "SELECT * FROM Hall where hallId  = "+id+";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                hall.setHallName(resultSet.getString("title"));
                hall.setHallId(resultSet.getInt("hallId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hall;
    }
    //изменить зал
    public static void editHall(Hall hall){
        String sql = "UPDATE Hall SET title = '"+hall.getHallName()+"' where hallId = " + hall.getHallId()+";";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Удалить зал по id
    public static void deleteHall(int id){
        try {
            statement.executeUpdate("delete from Hall where hallId = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Получить список все id залов
    public static Integer[] getHallId(){
        ArrayList<Integer> ids = new ArrayList<>();
        var sql = "select hallId from Hall;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("hallId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Integer[] array = new Integer[ids.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ids.get(i);
        }
        return array;
    }
}
