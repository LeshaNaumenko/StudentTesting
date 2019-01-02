package model.dao;

import exceptions.PersistException;

import java.util.List;

public interface InterfaceDao<T,K> {
    public abstract List<T> getAll() throws PersistException;
    public abstract T update(T entity);
    public abstract T getEntityBy(String column, Object value) throws PersistException;
    public abstract List<T> getListOfEntityBy(String column, Object value) throws PersistException;
    public abstract boolean delete(K key);
    public abstract T create(T entity) throws PersistException;
}
