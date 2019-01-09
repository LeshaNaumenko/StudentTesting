package service;

import dao.IUserDAO;
import exceptions.PersistException;
import exceptions.ServiceException;
import model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private IUserDAO<User, Integer> userDAO;
    private UserService userService;
    private User user = new User();

    @Before
    public void setUp() {
        userDAO = (IUserDAO<User, Integer>) mock(IUserDAO.class);
        userService = new UserService(userDAO);
    }

    @Test
    public void getAllUsers() throws PersistException, ServiceException {
        when(userDAO.getAll()).thenReturn(Arrays.asList(new User(), new User()));
        List<User> allUsers = userService.getAllUsers();
        assertNotNull(allUsers);
    }

    @Test
    public void getUserBy() throws PersistException, ServiceException {
        when(userDAO.getEntityBy(anyString(), anyString())).thenReturn(new User());
        User userBy = userService.getUserBy("someValue", "someValue");
        assertNotNull(userBy);
    }

    @Test
    public void registerUser() throws PersistException, ServiceException {
        when(userDAO.create(any(User.class))).thenReturn(user);
        User user = userService.registerUser(new User());
        assertNotNull(user);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetAllUsers() throws PersistException, ServiceException {
        when(userDAO.getAll()).thenThrow(PersistException.class);
        userService.getAllUsers();
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForGetUserBy() throws PersistException, ServiceException {
        when(userDAO.getEntityBy(anyString(), anyString())).thenThrow(PersistException.class);
        userService.getUserBy("someValue", "someValue");
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsForRegisterUser() throws PersistException, ServiceException {
        when(userDAO.create(any(User.class))).thenThrow(PersistException.class);
        userService.registerUser(new User());
    }
}