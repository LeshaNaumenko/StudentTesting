package dao;

import exceptions.DAOException;
import model.entity.Answer;

import java.util.List;

/**
 * Class {@code MysqlDAOFactory} is a concrete {@code DAOFactory} implementation for MYSQL.
 *
 * @author Alex Naumenko
 *
 * @see dao.factory.DAOFactory
 */
public interface IAnswerDAO<T extends Answer, K> {
    List<T> getAll() throws DAOException;
    T create(T entity) throws DAOException;
}
