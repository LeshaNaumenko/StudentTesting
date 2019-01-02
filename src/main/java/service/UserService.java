package service;

import connection.MysqlConnection;
import exeption.PersistException;
import exeption.ServiceExeption;
import model.dao.UserDao;
import model.dao.factory.DAOFactory;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class UserService {
    final static Logger logger = Logger.getLogger(UserService.class);

    UserDao userDAO;

    public UserService() {
        Connection connection = MysqlConnection.createConnection();
        this.userDAO = DAOFactory.getInstance("mysql").getUserDAO(connection);
    }

    public List<User> getAllUsers() throws ServiceExeption {
        try {
            return userDAO.getAll();
        } catch (PersistException e) {
            logger.error("Exception getting all users. \nError message: " + e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    public User getUserBy(String column, String value) throws ServiceExeption {

        try {
            return userDAO.getEntityBy(column, value);
        } catch (PersistException e) {
            logger.error("Exception getting users by "+column+" with a value of "+value+". \nError message: " + e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    public User registerUser(User user) throws ServiceExeption {
        try {
            return userDAO.create(user);
        } catch (PersistException e) {
            logger.error("Exception when creating an users. \nError message: " + e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
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
