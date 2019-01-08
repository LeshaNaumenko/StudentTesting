package service;

import dao.IThemeDAO;
import exceptions.PersistException;
import exceptions.ServiceException;
import dao.factory.DAOFactory;
import model.entity.Theme;
import org.apache.log4j.Logger;

import java.util.List;

public class ThemeService {
    final static Logger logger = Logger.getLogger(ThemeService.class);

    private IThemeDAO<Theme, Integer> themeDAO;

    public ThemeService() {
        this.themeDAO = DAOFactory.getInstance(DAOFactory.DBName.MYSQL_DB).getThemeDAO();
    }


    public List<String> getCourses(){
        List<String> courseName = themeDAO.getCourseName();
        if (courseName.size()==0){
            return null;
        }
        return courseName;
    }

    public List<Theme> getThemesByCourse(Object value) throws ServiceException {
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
