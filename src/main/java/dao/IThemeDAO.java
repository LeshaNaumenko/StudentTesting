package dao;

import exceptions.PersistException;
import model.entity.Theme;

import java.util.List;

public interface IThemeDAO<T extends Theme, K extends Integer> {
    T getEntityBy(String column, Object value) throws PersistException;
    List<T> getListOfEntityBy(String column, Object value) throws PersistException;
    List<String> getCourseName();
}
