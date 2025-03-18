package ru.ferin.consolerpg.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SQLiteManager {
    private static final String DB_URL = "jdbc:sqlite:database.db";
    private static final String SQL_FILE = "save.sql";

    // Метод для сохранения lvl в БД и экспорта в save.sql
    public static void saveLevel(int lvl) {
        try {
            // Подключение к базе данных
            Connection conn = DriverManager.getConnection(DB_URL);

            // Создание таблицы, если она не существует
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS save (lvl INTEGER)");
            stmt.close();

            // Очистка таблицы и вставка новой записи
            PreparedStatement clearStmt = conn.prepareStatement("DELETE FROM save");
            clearStmt.executeUpdate();
            clearStmt.close();

            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO save (lvl) VALUES (?)");
            insertStmt.setInt(1, lvl);
            insertStmt.executeUpdate();
            insertStmt.close();

            // Экспорт в save.sql
            FileWriter writer = new FileWriter(SQL_FILE);
            writer.write("CREATE TABLE IF NOT EXISTS levels (lvl INTEGER);\n");
            writer.write("INSERT INTO levels (lvl) VALUES (" + lvl + ");\n");
            writer.close();

            conn.close();

        } catch (Exception e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    // Метод для чтения lvl из save.sql
    public static int loadLevel() {
        File sqlFile = new File(SQL_FILE);

        // Если файл не существует, возвращаем -1
        if (!sqlFile.exists()) {
            return -1;
        }

        try {
            // Подключение к базе данных
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            // Создание таблицы, если она не существует
            stmt.execute("CREATE TABLE IF NOT EXISTS save (lvl INTEGER)");

            // Чтение данных из таблицы
            ResultSet rs = stmt.executeQuery("SELECT lvl FROM save LIMIT 1");
            int lvl = -1;
            if (rs.next()) {
                lvl = rs.getInt("lvl");
            }

            rs.close();
            stmt.close();
            conn.close();

            return lvl;

        } catch (Exception e) {
            System.err.println("Ошибка при загрузке: " + e.getMessage());
            return -1;
        }
    }
}