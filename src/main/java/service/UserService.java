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

    public UserService() {
        DAOFactory instance = DAOFactory.getInstance(DAOFactory.DBName.MYSQL_DB);
        IUserDAO<User, Integer> userDAO = instance.getUserDAO();
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

    private boolean isEmpty(List<User> users) {
        return (users == null || users.size() == 0);
    }


  /*  public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException {
        String pass = "18101996Lesha";
        byte[] salt = getSalt();
        String securePassword = getSecurePassword(pass, salt);

        System.out.println(new String(salt, StandardCharsets.UTF_8));
        System.out.println(securePassword);

    }*/
}
