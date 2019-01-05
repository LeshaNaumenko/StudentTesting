package dao;

import exceptions.PersistException;
import model.entity.Question;
import java.util.List;
import java.util.Locale;

public interface IQuestionDAO<T extends Question, K extends Integer> {
    List<T> getListOfQuestionWithLocale(K themeId, Locale locale) throws PersistException;
}
