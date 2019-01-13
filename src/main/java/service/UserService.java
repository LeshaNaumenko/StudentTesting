package service;

import exceptions.DAOException;
import exceptions.ServiceException;
import dao.IUserDAO;
import dao.factory.DAOFactory;
import model.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {
    final static Logger logger = Logger.getLogger(UserService.class);

    private IUserDAO<User, Integer> userDAO;

    public UserService() {
        this.userDAO = DAOFactory.getInstance(DAOFactory.DBName.MYSQL_DB).getUserDAO();
    }

    public UserService(IUserDAO<User, Integer> userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            logger.error("No users");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public User getUserBy(String column, String value) throws ServiceException {

        try {
            return userDAO.getEntityBy(column, value);
        } catch (DAOException e) {
            logger.error("Exception getting users by "+column+" with a value of "+value+". \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public User registerUser(User user) throws ServiceException {
        try {
            return userDAO.create(user);
        } catch (DAOException e) {
            logger.error("Exception when creating an users. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
