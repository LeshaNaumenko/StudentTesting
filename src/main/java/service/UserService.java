package service;

import exceptions.PersistException;
import exceptions.ServiceException;
import dao.IUserDAO;
import dao.factory.DAOFactory;
import model.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {
    final static Logger logger = Logger.getLogger(UserService.class);

    private IUserDAO<User, Integer> userDAO;

    public UserService(IUserDAO<User, Integer> userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (PersistException e) {
            logger.error("Exception getting all users. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public User getUserBy(String column, String value) throws ServiceException {

        try {
            return userDAO.getEntityBy(column, value);
        } catch (PersistException e) {
            logger.error("Exception getting users by "+column+" with a value of "+value+". \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public User registerUser(User user) throws ServiceException {
        try {
            return userDAO.create(user);
        } catch (PersistException e) {
            logger.error("Exception when creating an users. \nError message: " + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
