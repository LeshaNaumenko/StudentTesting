package service;

import dao.ITestDAO;
import dao.IThemeDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import model.entity.TestDTO;
import model.entity.Theme;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServiceTest {

    private model.entity.Test TEST = new model.entity.Test.Builder().build();
    private ITestDAO<model.entity.Test, Integer> testDao;
    private IThemeDAO<Theme, Integer> themeDAO;
    private TestService testService;

    @Before
    public void setUp() {
        testDao = (ITestDAO<model.entity.Test, Integer>) mock(ITestDAO.class);
        themeDAO = (IThemeDAO<Theme, Integer>) mock(IThemeDAO.class);
        testService = new TestService(testDao, themeDAO);
    }

    @Test
    public void calculateStatus() throws DAOException, ServiceException, ServiceException {
        when(themeDAO.getEntityBy(anyString(), anyInt())).thenReturn(new Theme(1, null, null, null, 70));
        model.entity.Test.Status status = testService.calculateStatus(70, 1);
        assertNotNull(status);
        assertEquals(status, model.entity.Test.Status.PASSED);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetThemeByID() throws DAOException, ServiceException {
        when(themeDAO.getEntityBy(anyString(), anyInt())).thenThrow(DAOException.class);
        testService.calculateStatus(70, 1);
    }

    @Test
    public void createTest() throws DAOException, ServiceException {
        when(testDao.create(any(model.entity.Test.class))).thenReturn(TEST);
        model.entity.Test test = testService.createTest(TEST);
        assertNotNull(test);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForCreateTest() throws DAOException, ServiceException {
        when(testDao.create(any(model.entity.Test.class))).thenThrow(DAOException.class);
        testService.createTest(TEST);
    }

    @Test
    public void getResultsById() throws DAOException, ServiceException {
        when(testDao.getTestResults(anyInt(), anyInt(), anyInt())).thenReturn(Arrays.asList(new TestDTO(), new TestDTO(), new TestDTO()));
        List<TestDTO> resultsById = testService.getResultsById(1, 1, 1);
        assertNotNull(resultsById);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetResultsById() throws DAOException, ServiceException {
        when(testDao.getTestResults(anyInt(), anyInt(), anyInt())).thenThrow(DAOException.class);
        testService.getResultsById(1, 1, 1);
    }

    @Test
    public void calculateTheGrade() throws ServiceException {
        int grade = testService.calculateTheGrade(10, 15);
        assertEquals(67, grade);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetResultsByIdCalculateTheGrade() throws ServiceException {
        testService.calculateTheGrade(10, 0);
    }

    @Test
    public void getNumberOfRows() throws DAOException, ServiceException {
        when(testDao.getNumberOfRows(anyInt())).thenReturn(1000);
        int resultsById = testService.getNumberOfRows(1);
        assertEquals(1000, resultsById);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetNumberOfRows() throws DAOException, ServiceException {
        when(testDao.getNumberOfRows(anyInt())).thenThrow(DAOException.class);
        testService.getNumberOfRows(1);
    }

    @Test
    public void getDuration() throws ServiceException {
        long duration = testService.getDuration(10, 5);
        assertEquals(duration, 5);

    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetDuration() throws ServiceException {
        testService.getDuration(5, 6);
    }

    @Test
    public void getTheDifferenceSeconds() {
        long theDifferenceSeconds = testService.getTheDifferenceSeconds(15470);
        assertEquals(15, theDifferenceSeconds);
    }


    @Test
    public void getTheDifferenceMinutes() {
        long theDifferenceMinutes = testService.getTheDifferenceMinutes(15470332);
        assertEquals(17, theDifferenceMinutes);
    }
}