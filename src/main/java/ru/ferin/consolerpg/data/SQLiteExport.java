package ru.ferin.consolerpg.data;

import ru.ferin.consolerpg.world.World;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteExport {
    public static void save(World world) {
        String dbUrl = "jdbc:sqlite:database.db";
        String outputFile = "save.sql"; // Имя выходного файла

        try {
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement stmt = conn.createStatement();

            // Создание FileWriter для записи в файл
            FileWriter writer = new FileWriter(outputFile);

            writer.write("CREATE TABLE IF NOT EXISTS table_name (\n");
            writer.write("    id INTEGER PRIMARY KEY,\n");
            writer.write("    name TEXT NOT NULL,\n");
            writer.write("    lvl INTEGER\n");
            writer.write(");\n\n");

            ResultSet rs = null;
                    //stmt.executeQuery("SELECT * FROM table");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int lvl = rs.getInt("lvl");

                name = name.replace("'", "''");

                String insert = String.format(
                        "INSERT INTO table_name (id, name, lvl) VALUES (%d, '%s', %d);\n",
                        id, name, lvl
                );
                writer.write(insert);
            }

            writer.close();
            rs.close();
            stmt.close();
            conn.close();

            //System.out.println("Экспорт успешно завершен в файл " + outputFile);

        } catch (Exception e) {
            System.err.println("Ошибка при экспорте: " + e.getMessage());
            e.printStackTrace();
        }
    }
}