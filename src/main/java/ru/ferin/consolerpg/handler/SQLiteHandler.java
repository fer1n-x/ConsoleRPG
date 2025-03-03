package ru.ferin.consolerpg.handler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler {
    private final Connection connection;

    public SQLiteHandler(String dbName) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
    }

    /**
     * @param sql текст запроса
     * @return результат
     * @throws SQLException
     */
    public List<Object[]> executeQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<Object[]> result = new ArrayList<>();
        while (resultSet.next()) {
            int columnCount = resultSet.getMetaData().getColumnCount();
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = resultSet.getObject(i + 1);
            }
            result.add(row);
        }

        resultSet.close();
        statement.close();
        return result;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
