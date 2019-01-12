package dao;

import exceptions.DAOException;
import model.entity.User;

import java.util.List;

/**
 * {@code IUserDAO} interface defines methods for implementing different activities with user in database.
 *
 * @author Alex Naumenko
 */
public interface IUserDAO<T extends User, K> {

    /**
     * Return information about all users.
     *
     * @return list of users if the query was successful.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see model.entity.User
     */
    List<T> getAll() throws DAOException;

    /**
     * Return active user with specified value.
     *
     * @param column in database
     * @param value  in database
     * @return active user.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     */
    T getEntityBy(String column, Object value) throws DAOException;

    /**
     * This method create new user in system and return active user.
     *
     * @param entity - User instance.
     * @return active user.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     */
    T create(T entity) throws DAOException;
}
