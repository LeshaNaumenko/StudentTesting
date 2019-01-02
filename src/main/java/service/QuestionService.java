package service;

import connection.MysqlConnection;
import exeption.PersistException;
import exeption.ServiceExeption;
import model.dao.QuestionDao;
import model.dao.factory.DAOFactory;
import model.entity.Question;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Locale;

public class QuestionService {
    final static Logger logger = Logger.getLogger(QuestionService.class);

    private QuestionDao questionDao;

    public QuestionService() {
        System.out.println("QuestionService");
        Connection connection = MysqlConnection.createConnection();
        this.questionDao = DAOFactory.getInstance("mysql").getQuestionDao(connection);
    }

    public List<Question> getQuestions(String column, Object value) throws ServiceExeption {
        try {
            return questionDao.getListOfEntityBy(column, value);
        } catch (PersistException e) {
            logger.error("Exception getting question. \nError message: " + e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    public List<Question> getQuestions(Integer themeId, Locale locale) throws ServiceExeption {
        try {
            return questionDao.getListOfQuestionWithLocale(themeId, locale);
        } catch (PersistException e) {
            logger.error("Exception getting question. \nError message: " + e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    private boolean isEmpty(List<Question> questions) {
        return (questions == null || questions.size() == 0);
    }
}