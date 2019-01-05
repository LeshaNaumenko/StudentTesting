package dao;

import exceptions.PersistException;
import model.entity.User;

import java.util.List;

public interface IUserDAO<T extends User, K extends Integer> {
    List<T> getAll() throws PersistException;
    T update(T entity);
    T getEntityBy(String column, Object value) throws PersistException;
    T create(T entity) throws PersistException;
}
