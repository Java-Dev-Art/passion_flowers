package com.example.passion_flowers.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    private Connection connection;

    /**
     * Начинает транзакцию с одним или несколькими DAO.
     * Устанавливает соединение и выключает автофиксирование.
     */
    public void begin(AbstractDao dao, AbstractDao... daos) throws SQLException {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection(); // Получение соединения из пула
        }
        try {
            connection.setAutoCommit(false); // Отключение автофиксации
        } catch (SQLException e) {
            throw new RuntimeException("Error setting auto-commit to false", e);
        }
        dao.setConnection(connection);
        for (AbstractDao daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    /**
     * Завершает транзакцию, включая восстановление автофиксирования и возврат соединения в пул.
     */
    public void end() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true); // Включение автофиксации
            } catch (SQLException e) {
                throw new RuntimeException("Error resetting auto-commit to true", e);
            } finally {
                try {
                    connection.close(); // Возврат соединения в пул
                } catch (SQLException e) {
                    throw new RuntimeException("Error closing connection", e);
                } finally {
                    connection = null; // Сброс ссылки
                }
            }
        }
    }

    /**
     * Подтверждает транзакцию.
     */
    public void commit() {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException("Error during commit", e);
            }
        }
    }

    /**
     * Откатывает транзакцию.
     */
    public void rollback() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException("Error during rollback", e);
            }
        }
    }
}
