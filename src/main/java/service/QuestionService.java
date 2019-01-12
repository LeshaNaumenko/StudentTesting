package service;

import dao.IQuestionDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import dao.factory.DAOFactory;
import model.entity.Question;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Locale;

public class QuestionService {
    final static Logger logger = Logger.getLogger(QuestionService.class);

    private IQuestionDAO<Question, Integer> questionDao;

    public QuestionService() {
        this.questionDao = DAOFactory.getInstance(DAOFactory.DBName.MYSQL_DB).getQuestionDao();
    }

    public QuestionService(IQuestionDAO<Question, Integer> questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getQuestions(Integer themeId, Locale locale) throws ServiceException {
        try {
            return questionDao.getListOfQuestionWithLocale(themeId, locale);
        } catch (DAOException e) {
            logger.error("Exception getting question. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private boolean isEmpty(List<Question> questions) {
        return (questions == null || questions.size() == 0);
    }
}
