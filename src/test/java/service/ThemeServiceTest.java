package service;

import dao.IThemeDAO;
import exceptions.PersistException;
import exceptions.ServiceException;
import model.entity.Theme;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
    public void getCourses() throws ServiceException, PersistException {
        when(themeDAO.getCourseName()).thenReturn(Arrays.asList("Java", "C++", "C#"));
        List<String> courses = themeService.getCourses();
        assertNotNull(courses);
    }

    @Test
    public void getThemesByCourse() throws PersistException, ServiceException {
        when(themeDAO.getListOfEntityBy(anyString(), anyString())).thenReturn(Arrays.asList(new Theme(), new Theme(), new Theme()));
        List<Theme> anyCourse = themeService.getThemesByCourse("anyCourse");
        assertNotNull(anyCourse);
    }

    @Test
    public void getThemeByID() throws PersistException, ServiceException {
        when(themeDAO.getEntityBy(anyString(), anyInt())).thenReturn(new Theme());
        Theme themeByID = themeService.getThemeByID(1);
        assertNotNull(themeByID);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetThemeByID() throws PersistException, ServiceException {
        when(themeDAO.getEntityBy(anyString(), anyInt())).thenThrow(PersistException.class);
        themeService.getThemeByID(1);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetCourses() throws PersistException, ServiceException {
        when(themeDAO.getCourseName()).thenThrow(PersistException.class);
        themeService.getCourses();
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetThemeByCourse() throws PersistException, ServiceException {
        when(themeDAO.getListOfEntityBy(anyString(), anyString())).thenThrow(PersistException.class);
        themeService.getThemesByCourse("anyCourse");
    }


}