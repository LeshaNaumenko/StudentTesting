package service;

import dao.ITestDAO;
import dao.IThemeDAO;
import exceptions.PersistException;
import exceptions.ServiceException;
import dao.impl.MysqlTestDao;
import dao.impl.MysqlThemeDao;
import dao.factory.DAOFactory;
import model.entity.Test;
import model.entity.TestDTO;
import model.entity.Theme;
import org.apache.log4j.Logger;

import java.util.List;

public class TestService {
    final static Logger logger = Logger.getLogger(TestService.class);

    ITestDAO<Test,Integer> testDao;
    IThemeDAO<Theme, Integer> themeDAO;

    public TestService() {
        System.out.println("TestService");
        this.testDao = DAOFactory.getInstance(DAOFactory.DBName.MYSQL_DB).getTestDao();
        this.themeDAO = DAOFactory.getInstance(DAOFactory.DBName.MYSQL_DB).getThemeDAO();
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


    public List<TestDTO> getResultsById(int id, int currentPage, int recordsPerPage) throws ServiceException {
        try {
            return testDao.getTestResults(id, currentPage, recordsPerPage);
        } catch (PersistException e) {
            logger.error("Exception getting results for user  by id. \nError message: " + e.getMessage());
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
