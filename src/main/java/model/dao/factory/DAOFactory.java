package model.dao.factory;

import model.dao.*;

import java.sql.Connection;

public abstract class DAOFactory {
    private static volatile DAOFactory daoFactory;

    public abstract UserDao getUserDAO(Connection connection);

    public abstract TestDao getTestDao(Connection connection);

    public abstract QuestionDao getQuestionDao(Connection connection);

    public abstract ThemeDao getThemeDAO(Connection connection);

    public abstract AnswerDao getAnswerDao(Connection connection);

    public static DAOFactory getInstance(String nameDB) {
        switch (nameDB) {
            case "mysql":
                if (daoFactory == null) {
                    synchronized (DAOFactory.class) {
                        if (daoFactory== null) {
                            daoFactory = new MysqlDAOFactory();
                        }
                    }
                }
                break;
            case "oracle":
//                            return new OracleDAOFactory();
                break;
            default:
                System.out.println("3 here");
                daoFactory = null;
        }
        return daoFactory;
    }
}
