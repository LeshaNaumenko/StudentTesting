package service;

import dao.ITestDAO;
import dao.IThemeDAO;
import exceptions.PersistException;
import exceptions.ServiceException;
import model.entity.Test;
import model.entity.TestDTO;
import model.entity.Theme;
import org.apache.log4j.Logger;

import java.util.List;

public class TestService {
    final static Logger logger = Logger.getLogger(TestService.class);

    private ITestDAO<Test, Integer> testDao;
    private IThemeDAO<Theme, Integer> themeDAO;

    public TestService(ITestDAO<Test, Integer> testDao, IThemeDAO<Theme, Integer> themeDAO) {
        this.testDao = testDao;
        this.themeDAO = themeDAO;
    }


    public Test.Status calculateStatus(int grade, Integer themeId) throws ServiceException {
        try {
            Theme theme = themeDAO.getEntityBy("id", themeId);
            if (grade >= theme.getPassingGrade()) return Test.Status.PASSED;
            return Test.Status.FAILED;
        } catch (PersistException e) {
            logger.error("Exception when calculate status. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Test createTest(Test test) throws ServiceException {
        try {
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

    public int calculateTheGrade(int correctAnswers, int listSize) throws ServiceException {
        if (listSize==0)
            throw new ServiceException("Argument 'listSize' is 0");
        return (int) Math.round((double) (correctAnswers * 100) / listSize);
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

    public long getDuration(long end, long start) throws ServiceException {
        if (start>end)
            throw new ServiceException("Argument 'start' > 'end' ");
        return end - start;
    }

    public long getTheDifferenceSeconds(long duration) {
        return duration / 1000 % 60;
    }

    public long getTheDifferenceMinutes(long duration) {
        return duration / (60 * 1000) % 60;
    }
}