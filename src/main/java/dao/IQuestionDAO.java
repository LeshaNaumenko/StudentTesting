package dao;

import exceptions.DAOException;
import model.entity.Question;

import java.util.List;
import java.util.Locale;


/**
 * {@code IQuestionDAO} interface defines methods for implementing different activities with question in database.
 *
 * @author Alex Naumenko
 */
public interface IQuestionDAO<T extends Question, K> {

    /**
     * Return a list of question with locale of a certain course.
     *
     * @param themeId in database
     * @param locale  in database
     * @return a list of question with locale of a certain course.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     */
    List<T> getListOfQuestionWithLocale(K themeId, Locale locale) throws DAOException;
}