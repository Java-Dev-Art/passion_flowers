package com.example.passion_flowers.dao;

import com.example.passion_flowers.model.User;
import com.example.passion_flowers.service.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.passion_flowers.dao.ConnectionPool.getConnection;

public class UserDao extends AbstractDao<User>{

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLQueries.SQL_SELECT_ALL_USERS)) {

            while (resultSet.next()) {
                users.add(mapToUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving all users", e);
        }
        return users;
    }

    @Override
    public User findByEntityById(long id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_SELECT_USERS_BY_USER_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving user by ID: " + id, e);
        }
        return null;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return delete(user.getId());
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_DELETE_BY_USER_ID)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error deleting user by ID: " + id, e);
        }
    }

    @Override
    public boolean create(User user) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_CREATE_NEW_OBJECT, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatementForUser(preparedStatement, user);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getLong(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Error creating user", e);
        }
        return false;
    }

    @Override
    public boolean update(User user) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_UPDATE_USER)) {
            fillPreparedStatementForUser(preparedStatement, user);
            preparedStatement.setLong(6, user.getId()); // ID пользователя
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error updating user with ID: " + user.getId(), e);
        }
    }

    @Override
    public User findUserByUsername(String username) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FIND_BY_USERNAME)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getLong("user_id"),
                            resultSet.getString("username"),
                            null, // email не используется в этой операции
                            resultSet.getString("password_hash"),
                            null, null, null, null, null, null // Остальные поля можно пропустить
                    );
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding user by username", e);
        }
        return null;
    }

    // Вспомогательные методы

    private User mapToUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                null, // password_hash не возвращаем для безопасности
                resultSet.getString("phone"),
                resultSet.getString("address"),
                resultSet.getString("telegram_handle"),
                resultSet.getString("instagram_handle"),
                resultSet.getString("role"),
                resultSet.getString("account_status")
        );
    }

    private void fillPreparedStatementForUser(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPasswordHash());
        preparedStatement.setString(4, user.getPhone());
        preparedStatement.setString(5, user.getAddress());
        preparedStatement.setString(6, user.getTelegramHandle());
        preparedStatement.setString(7, user.getInstagramHandle());
        preparedStatement.setString(8, user.getRole());
        preparedStatement.setString(9, user.getAccountStatus());
    }


    @Override
    public boolean createUser(User user) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(this);
            boolean isCreated = this.create(user);
            transaction.commit();
            return isCreated;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException("Error creating user", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean authentication(String login, String password) throws ServiceException {
        try {
            // Находим пользователя по логину
            User user = findUserByUsername(login);

            if (user != null) {
                // Сравниваем пароль
                return PasswordUtils.verifyPassword(password, user.getPasswordHash());
            }

            // Пользователь не найден
            return false;
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
