package dao;

import exceptions.DAOException;
import model.entity.Test;
import model.entity.TestDTO;
import java.util.List;

public interface ITestDAO<T extends Test, K> {

    T create(T entity) throws DAOException;

    List<TestDTO> getTestResults(K id, K start, K recordsPerPage) throws DAOException;

    K getNumberOfRows(K id) throws DAOException;
}
