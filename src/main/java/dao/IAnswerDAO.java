package dao;

import exceptions.PersistException;
import model.entity.Answer;

import java.util.List;

public interface IAnswerDAO<T extends Answer, K extends Integer> {
    List<T> getAll() throws PersistException;
    T update(T entity);
    T getEntityBy(String column, Object value) throws PersistException;
    List<T> getListOfEntityBy(String column, Object value) throws PersistException;
    boolean delete(K key);
    T create(T entity) throws PersistException;
}
