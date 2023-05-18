package database;

import java.sql.*;
import java.util.Objects;

public class ConnectionToBase {
    /** Переменная для хранения соединения с базой данных */
    private static Connection connection;
    /** Метод для получения соединения с базой данных */
    private static synchronized void tryConnection() {
        try {
            connection = DriverManager.getConnection(BasePropertiesGetter.getUrl(), BasePropertiesGetter.getUser(), BasePropertiesGetter.getPassword());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
    /** Метод для выполнения статического SQL-запроса
     * @param query выполняемый запрос
     * @see ConnectionToBase#tryConnection() */
    public static void executeStatement(String query) {
        tryConnection();
        try {
            connection.createStatement().execute(query);
            connection.close();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
    /** Метод для выполнения статического SQL-запроса и получения данных из базы данных
     * @param sqlRequest выполняемый запрос
     * @param values вариативные элементы запроса
     * @see ConnectionToBase#tryConnection()
     * @return возвращает тип ResultSet для его дальнейшей обработки */
    public static ResultSet executePreparedStatement(String sqlRequest, String... values) {
        tryConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            for (int i = 0; i < values.length; ++i) {
                preparedStatement.setString(i + 1, values[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException sqlException) {
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                System.out.println(sqlException.getMessage());
            }
        }
    }
    /** Метод для создания необходимых для работы программы таблиц в базе данных в случае их несуществования при запуске программы
     * @see ConnectionToBase#executePreparedStatement(String, String...)
     * @see  ConnectionToBase#executeStatement(String) */
    public static void createTablesIfNotExist() {
        try {
            if (executePreparedStatement("SELECT * FROM VEHICLE") == null) {
                executeStatement("CREATE TABLE VEHICLE (id bigint PRIMARY KEY, creator text NOT NULL, creationDate bigint NOT NULL, name text NOT NULL, enginePower bigint, type text NOT NULL, capacity long precision, fuelConsumption long, x bigint NOT NULL, y double precision NOT NULL)");
            }
            if (executePreparedStatement("SELECT * FROM USERS") == null) {
                executeStatement("CREATE TABLE USERS (login text  PRIMARY KEY, hash text NOT NULL, salt text NOT NULL)");
            }
            if (executePreparedStatement("SELECT * FROM id") == null) {
                ResultSet resultSet = executePreparedStatement("SELECT id FROM VEHICLE");
                long maxId = 1;
                while (Objects.requireNonNull(resultSet).next()) {
                    maxId = Long.max(resultSet.getLong(1), maxId);
                }
                executeStatement("CREATE SEQUENCE id START " + maxId);
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
