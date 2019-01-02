package service;

import connection.MysqlConnection;
import exceptions.PersistException;
import exceptions.ServiceException;
import model.dao.TestDao;
import model.dao.ThemeDao;
import model.dao.factory.DAOFactory;
import model.entity.Test;
import model.entity.TestDTO;
import model.entity.Theme;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class TestService {
    final static Logger logger = Logger.getLogger(TestService.class);

    TestDao testDao;
    ThemeDao themeDAO;

    public TestService() {
        System.out.println("TestService");
        Connection connection = MysqlConnection.createConnection();
        this.testDao = DAOFactory.getInstance("mysql").getTestDao(connection);
        this.themeDAO = DAOFactory.getInstance("mysql").getThemeDAO(connection);
    }

    public List<Test> findAllTest() throws ServiceException {
        try {
            return testDao.getAll();
        } catch (PersistException e) {
            logger.error("Exception getting tests. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Test createTest(Integer userId, Integer themeId, Integer grade, String startTime, String endTime, String testTime, String date) throws ServiceException {
        try {
            Theme theme = themeDAO.getEntityBy("id", themeId);
            Test.Status status;
            if (grade > theme.getPassing_grade()) status = Test.Status.PASSED;
            else status = Test.Status.FAILED;
            Test test = new Test(userId, themeId, status, grade, startTime, endTime, testTime, date);
            return testDao.create(test);
        } catch (PersistException e) {
            logger.error("Exception when creating an tests. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Test getTestByID(Integer id) throws ServiceException {
        try {
            return testDao.getEntityBy("id", id);
        } catch (PersistException e) {
            logger.error("Exception getting tests by id. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<TestDTO> getResultsById(int id, int currentPage, int recordsPerPage) throws ServiceException {
        try {
            return testDao.getTestResults(id, currentPage, recordsPerPage);
        } catch (PersistException e) {
            logger.error("Exception getting results for user  by id. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Test> findTestsByUserID(int id) throws ServiceException {
        try {
            return testDao.getListOfEntityBy("user_id", id);
        } catch (PersistException e) {
            logger.error("Exception getting tests by user id. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static int calculateTheGrade(int correctAnswers, int listSize) {
        int grade = Math.round((correctAnswers * 100) / listSize);
        return grade;
    }


    public int getNumberOfRows(int id) throws ServiceException {
        try {
            return testDao.getNumberOfRows(id);
        } catch (PersistException e) {
            logger.error("Error getting number of rows of the test \n" +
                    "Error message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
