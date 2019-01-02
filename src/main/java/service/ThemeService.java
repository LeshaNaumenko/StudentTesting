package service;

import connection.MysqlConnection;
import exeption.PersistException;
import exeption.ServiceExeption;
import model.dao.ThemeDao;
import model.dao.factory.DAOFactory;
import model.entity.Theme;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class ThemeService {
    final static Logger logger = Logger.getLogger(ThemeService.class);

    private ThemeDao themeDAO;

    public ThemeService() {
        Connection connection = MysqlConnection.createConnection();
        this.themeDAO = DAOFactory.getInstance("mysql").getThemeDAO(connection);
    }

    public List<Theme> findAllThemes() throws ServiceExeption {
        try {
            return themeDAO.getAll();
        } catch (PersistException e) {
            logger.error("Exception getting themes. \nError message: " + e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    public List<String> getCourses(){
        List<String> courseName = themeDAO.getCourseName();
        if (courseName.size()==0){
            return null;
        }
        return courseName;
    }

    public List<Theme> getListOfEntityBy(String column, Object value) throws ServiceExeption {
        try {
            return themeDAO.getListOfEntityBy(column, value);
        } catch (PersistException e) {
            logger.error("Exception getting themes by "+column+" with a value of "+value+". \nError message: " + e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    public Theme getThemeByID(Integer themeId) throws ServiceExeption {
        try {
            return themeDAO.getEntityBy("id", themeId);
        } catch (PersistException e) {
            logger.error("Exception getting themes by id. \nError message: " + e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    private boolean isEmpty(List<Theme> themes) {
        return (themes == null || themes.size() == 0);
    }

}
