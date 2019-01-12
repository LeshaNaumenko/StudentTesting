package dao.factory.impl;

import connection.ConnectionPool;
import dao.*;
import dao.factory.DAOFactory;
import dao.impl.*;
import model.entity.*;

import java.sql.Connection;

/**
 * Class {@code MysqlDAOFactory} is a concrete {@code DAOFactory} implementation for MYSQL.
 *
 * @author Alex Naumenko
 *
 * @see dao.factory.DAOFactory
 */
public class MysqlDAOFactory extends DAOFactory {

    /**
     * Database connection
     */
    private Connection connection;

    /**
     * Default constructor.
     */
    public MysqlDAOFactory() {
        this.connection = ConnectionPool.getConnection();
    }

    /**
     * Return {@link MysqlUserDao} instance.
     *
     * @return {@link MysqlUserDao} instance.
     */
    @Override
    public IUserDAO<User, Integer> getUserDAO() {
        return new MysqlUserDao(connection);
    }

     /**
     * Return {@link MysqlTestDao} instance.
     *
     * @return {@link MysqlTestDao} instance.
     */
    @Override
    public ITestDAO<Test,Integer> getTestDao() {
        return new MysqlTestDao(connection);
    }

     /**
     * Return {@link MysqlQuestionDao} instance.
     *
     * @return {@link MysqlQuestionDao} instance.
     */
    @Override
    public IQuestionDAO<Question, Integer>getQuestionDao() {
        return new MysqlQuestionDao(connection);
    }

     /**
     * Return {@link MysqlThemeDao} instance.
     *
     * @return {@link MysqlThemeDao} instance.
     */
    @Override
    public IThemeDAO<Theme, Integer> getThemeDAO() {
        return new MysqlThemeDao(connection);
    }

     /**
     * Return {@link MysqlAnswerDao} instance.
     *
     * @return {@link MysqlAnswerDao} instance.
     */
    @Override
    public IAnswerDAO<Answer, Integer> getAnswerDao() {
        return new MysqlAnswerDao(connection);
    }
}
