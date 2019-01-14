package dao;

import exceptions.DAOException;
import model.entity.Theme;

import java.util.List;

/**
 * {@code IThemeDAO} interface defines methods for implementing different activities with theme in database.
 *
 * @author Alex Naumenko
 */
public interface IThemeDAO<T extends Theme, K> {

    /**
     * Return theme with specified value.
     *
     * @param column in database
     * @param value  in database
     * @return theme by specified value.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     */
    T getEntityBy(String column, Object value) throws DAOException;

    /**
     * Return a list of themes by specified value.
     *
     * @param column in database
     * @param value in database
     * @return a list of themes by specified value if the query was successful.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     */
    List<T> getListOfEntityBy(String column, Object value) throws DAOException;

    /**
     * Return a list of course name.
     *
     * @return a list of course name.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     */
    List<String> getCourseName() throws DAOException;
}
