package dao.factory.impl;

import connection.ConnectionPool;
import dao.*;
import dao.factory.DAOFactory;
import dao.impl.*;
import model.entity.*;

import java.sql.Connection;

public class MysqlDAOFactory extends DAOFactory {
    private Connection connection;

    public MysqlDAOFactory() {
        this.connection = ConnectionPool.getConnection();
    }

    @Override
    public IUserDAO<User, Integer> getUserDAO() {
        return new MysqlUserDao(connection);
    }

    @Override
    public ITestDAO<Test,Integer> getTestDao() {
        return new MysqlTestDao(connection);
    }

    @Override
    public IQuestionDAO<Question, Integer>getQuestionDao() {
        return new MysqlQuestionDao(connection);
    }

    @Override
    public IThemeDAO<Theme, Integer> getThemeDAO() {
        return new MysqlThemeDao(connection);
    }

    @Override
    public IAnswerDAO<Answer, Integer> getAnswerDao() {
        return new MysqlAnswerDao(connection);
    }
}
