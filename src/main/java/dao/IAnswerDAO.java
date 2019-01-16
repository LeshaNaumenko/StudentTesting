package dao;

import exceptions.DAOException;
import model.entity.Answer;

import java.util.List;


/**
 * {@code IAnswerDAO} interface defines methods for implementing different activities with answer in database.
 *
 * @author Alex Naumenko
 */
    public interface IAnswerDAO<T extends Answer, K> {

    /**
     * Return information about all answers.
     *
     * @return a list of answers if the query was successful.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see model.entity.Answer
     */
    List<T> getAll() throws DAOException;

    /**
     * This method create new user in system and return this user.
     *
     * @param entity - answer instance.
     * @return answer that was created
     * @throws DAOException if there was an error executing the query
     *                      in the database
     */
    T create(T entity) throws DAOException;
}
