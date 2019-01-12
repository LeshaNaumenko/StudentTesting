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
    T getEntityBy(String column, Object value) throws DAOException;
    List<T> getListOfEntityBy(String column, Object value) throws DAOException;
    List<String> getCourseName() throws DAOException;
}
