package ru.ferin.consolerpg.data;

import ru.ferin.consolerpg.core.ConsoleRPG;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//Неинтересный факт: меня бесят танцы с бубном Connection, Statement, PreparedStatement, ResultSet, DriverManager и т.д. ,_,
//Я ленивая задница которая привыкла работать с БД через hibernate.
//НО В НЕКОТОРЫХ СЛУЧАЯХ столь низкоуровневые обращения к базе данных могут быть лучше, чем очередная транзакция в хайбэнейте
public class SQLiteManager {
    private static final String DB_URL = "jdbc:sqlite:data.sqlite";

    public static void saveProgress(SaveState data) {
        try {
            //Создаем соединение с БД.
            Connection conn = DriverManager.getConnection(DB_URL);

            //Создаем состояние для выполнения статичных запросов.
            Statement stmt = conn.createStatement();
            //Пересоздаем таблицу если она есть, создаем новую если нет
            stmt.execute("DROP TABLE IF EXISTS save");
            stmt.execute("CREATE TABLE IF NOT EXISTS save (lvl INTEGER, health DOUBLE, attack DOUBLE, defence DOUBLE, lasttime LONG)");

            //Закрываем состояние
            stmt.close();

            ///////////////ИМХО тупенькая идея создавать таблицу ради одной записи. Вот если бы это была многопользовательская игра... Тогда еще другое дело.. Тогда бы например каждая запись могла принадлежать отдельному юзеру.
            //Создаем состояние для выполнения динамичных запросов
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO save (lvl, health, attack, defence, lasttime) VALUES (?, ?, ?, ?, ?)");
            insertStmt.setInt(1, data.lvl());
            insertStmt.setDouble(2, data.health());
            insertStmt.setDouble(3, data.attack());
            insertStmt.setDouble(4, data.defence());
            insertStmt.setLong(5, data.lasttime());
            insertStmt.executeUpdate();
            //Закрываем состояние
            insertStmt.close();

            //Закрываем соединение с БД
            conn.close();

        } catch (Exception e) {
            System.err.println("[PROGRESS] Saving error: " + e.getMessage());
        }
    }

    public static SaveState loadLevel() {
        int lvl = -1;
        double health = -1;
        double attack = -1;
        double defence = -1;
        long lasttime = -1;
        try {
            // Подключение к базе данных
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            // Создание таблицы, если она не существует
            stmt.execute("CREATE TABLE IF NOT EXISTS save (lvl INTEGER, health DOUBLE, attack DOUBLE, defence DOUBLE, lasttime LONG)");

            // Чтение данных из таблицы
            ResultSet rs = stmt.executeQuery("SELECT lvl, health, attack, defence, lasttime FROM save LIMIT 1");

            if (rs.next()) {
                lvl = rs.getInt("lvl");
                health = rs.getDouble("health");
                attack = rs.getDouble("attack");
                defence = rs.getDouble("defence");
                lasttime = rs.getLong("lasttime");
            }

            rs.close();
            stmt.close();
            conn.close();

            return new SaveState(lvl, health, attack, defence, lasttime);

        } catch (Exception e) {
            System.err.println("Ошибка при загрузке: " + e.getMessage());
            return new SaveState(lvl, health, attack, defence, lasttime);
        }
    }

    /**
     * @return Если уровень сохранен, то возвращает дату последнего сохранения. В ином случае возвращает -1
     */
    public static String checkLevel() {
        SaveState sv = ConsoleRPG.getInstance().getTempSaveState();
        if (sv.isValid()) {
            Date date = new java.util.Date(sv.lasttime());
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(java.util.TimeZone.getDefault());
            return sdf.format(date);
        }
        return "";
    }

    public static void saveLogs(List<LogState> logs) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS logs (logtype VARCHAR(20),  value DOUBLE, time LONG)");
            stmt.close();
            logs.forEach(log -> {
                try {
                    PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO logs (logtype, value, time) VALUES (?, ?, ?)");
                    insertStmt.setString(1, log.type().toString());
                    insertStmt.setDouble(2, log.value());
                    insertStmt.setLong(3, log.time());
                    insertStmt.executeUpdate();
                    insertStmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            conn.close();

        } catch (Exception e) {
            System.err.println("[LOG] Saving error: " + e.getMessage());
        }
    }
}