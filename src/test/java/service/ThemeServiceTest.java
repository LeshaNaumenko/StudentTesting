package service;

import dao.IThemeDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
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

public class ThemeServiceTest {
    private IThemeDAO<Theme, Integer> themeDAO;
    private ThemeService themeService;

    @Before
    public void setUp() {
        themeDAO = (IThemeDAO<Theme, Integer>) mock(IThemeDAO.class);
        themeService = new ThemeService(themeDAO);
    }

    @Test
    public void getCourses() throws ServiceException, DAOException {
        when(themeDAO.getCourseName()).thenReturn(Arrays.asList("Java", "C++", "C#"));
        List<String> courses = themeService.getCourses();
        assertNotNull(courses);
    }

    @Test
    public void getThemesByCourse() throws DAOException, ServiceException {
        when(themeDAO.getListOfEntityBy(anyString(), anyString())).thenReturn(Arrays.asList(new Theme(), new Theme(), new Theme()));
        List<Theme> anyCourse = themeService.getThemesByCourse("anyCourse");
        assertNotNull(anyCourse);
    }

    @Test
    public void getThemeByID() throws DAOException, ServiceException {
        when(themeDAO.getEntityBy(anyString(), anyInt())).thenReturn(new Theme());
        Theme themeByID = themeService.getThemeByID(1);
        assertNotNull(themeByID);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetThemeByID() throws DAOException, ServiceException {
        when(themeDAO.getEntityBy(anyString(), anyInt())).thenThrow(DAOException.class);
        themeService.getThemeByID(1);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetCourses() throws DAOException, ServiceException {
        when(themeDAO.getCourseName()).thenThrow(DAOException.class);
        themeService.getCourses();
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetThemeByCourse() throws DAOException, ServiceException {
        when(themeDAO.getListOfEntityBy(anyString(), anyString())).thenThrow(DAOException.class);
        themeService.getThemesByCourse("anyCourse");
    }


}