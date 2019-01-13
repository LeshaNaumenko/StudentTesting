package dao;


import exceptions.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public abstract class AbstractDao<T, K> {
    private final static Logger LOGGER = Logger.getLogger(AbstractDao.class);
    public Connection connection;

    public AbstractDao(Connection connection)  {
            this.connection = connection;
    }

    public abstract String getCreateQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws SQLException;

    public abstract String getSelectQuery();

    public abstract String getSelectQueryForList();

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;


    public T create(T object) throws DAOException {
        T persistInstance = null;
        int id;
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DAOException("On persist modify more then 1 record: " + count);
            }
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            id = keys.getInt(1);

            sql = getSelectQuery() + " WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new DAOException("Exception on create new entity.");
            }
            persistInstance = list.iterator().next();
            return persistInstance;
        } catch (Exception e) {
            LOGGER.error("Exception on create new entity. \n" +
                    "Error message: " + e.getMessage());
            throw new DAOException(e);
        }
    }


    public T getEntityBy(String column, Object value) throws DAOException {
        List<T> list;
        String sql = getSelectQueryForList() + " where " + column + "=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error getting entity. \n" +
                    "Error message: " + e.getMessage());
            throw new DAOException(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            LOGGER.error("Received more than one record.");
            throw new DAOException("Received more than one record.");
        }
        return list.iterator().next();
    }


    public List<T> getAll() throws DAOException {
        List<T> list;
        String selectQuery = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error getting all entity. \n" +
                    "Error message: " + e.getMessage());
            throw new DAOException(e);
        }
        return list;
    }


    public List<T> getListOfEntityBy(String column, Object value) throws DAOException {
        List<T> list;
        String sql = getSelectQueryForList() + " where " + column + "=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error getting list of entity. \n" +
                    "Error message: " + e.getMessage());
            throw new DAOException(e);
        }
        return list;
    }

    /*
    public T update(T entity) {
        return null;
    }

    public boolean delete(K key) {
        return false;
    }
    */

}
