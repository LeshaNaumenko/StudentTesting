package model.dao.factory;

import model.dao.*;

import java.sql.Connection;

public class MysqlDAOFactory extends DAOFactory {
    @Override
    public UserDao getUserDAO(Connection connection) {
        return new UserDao(connection);
    }

    @Override
    public TestDao getTestDao(Connection connection) {
        return new TestDao(connection);
    }

    @Override
    public QuestionDao getQuestionDao(Connection connection) {
        return new QuestionDao(connection);
    }

    @Override
    public ThemeDao getThemeDAO(Connection connection) {
        return new ThemeDao(connection);
    }

    @Override
    public AnswerDao getAnswerDao(Connection connection) {
        return new AnswerDao(connection);
    }
}
