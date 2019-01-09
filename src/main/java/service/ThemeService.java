package service;

import dao.IThemeDAO;
import exceptions.PersistException;
import exceptions.ServiceException;
import dao.factory.DAOFactory;
import model.entity.Theme;
import org.apache.log4j.Logger;

import java.util.List;

public class ThemeService {
    private final static Logger logger = Logger.getLogger(ThemeService.class);

    private IThemeDAO<Theme, Integer> themeDAO;

    public ThemeService(IThemeDAO<Theme, Integer> themeDAO) {
        this.themeDAO = themeDAO;
    }

    public List<String> getCourses() throws ServiceException {
        try {
            return themeDAO.getCourseName();
        } catch (PersistException e) {
            logger.error("Exception getting all course names\nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Theme> getThemesByCourse(String value) throws ServiceException {
        try {
            return themeDAO.getListOfEntityBy("course_name", value);
        } catch (PersistException e) {
            logger.error("Exception getting themes by course_name with a value of "+value+". \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Theme getThemeByID(Integer themeId) throws ServiceException {
        try {
            return themeDAO.getEntityBy("id", themeId);
        } catch (PersistException e) {
            logger.error("Exception getting themes by id. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private boolean isEmpty(List<Theme> themes) {
        return (themes == null || themes.size() == 0);
    }

}
