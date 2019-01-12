package dao;

import exceptions.DAOException;
import model.entity.Question;
import java.util.List;
import java.util.Locale;

public interface IQuestionDAO<T extends Question, K> {
    List<T> getListOfQuestionWithLocale(K themeId, Locale locale) throws DAOException;
}
