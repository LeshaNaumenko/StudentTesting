package dao;

import exceptions.PersistException;
import model.entity.Test;
import model.entity.TestDTO;
import java.util.List;

public interface ITestDAO<T extends Test, K extends Integer> {

    T create(T entity) throws PersistException;

    List<TestDTO> getTestResults(K id, K start, K recordsPerPage) throws PersistException;

    K getNumberOfRows(K id) throws PersistException;
}
