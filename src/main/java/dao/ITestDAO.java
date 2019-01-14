package dao;

import exceptions.DAOException;
import model.entity.Test;
import model.entity.TestInfo;
import java.util.List;


/**
 * {@code ITestDAO} interface defines methods for implementing different activities with test in database.
 *
 * @author Alex Naumenko
 */
public interface ITestDAO<T extends Test, K> {

    /**
     * This method create new test in system and return this test.
     *
     * @param entity - Test instance.
     * @return test that was created
     * @throws DAOException if there was an error executing the query
     *                      in the database
     */
    T create(T entity) throws DAOException;

    List<TestInfo> getTestResults(K id, K start, K recordsPerPage) throws DAOException;

    K getNumberOfRows(K id) throws DAOException;
}
