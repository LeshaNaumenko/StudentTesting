package dao.factory;

import dao.*;
import dao.factory.impl.MysqlDAOFactory;
import dao.impl.MysqlAnswerDao;
import dao.impl.MysqlQuestionDao;
import dao.impl.MysqlTestDao;
import dao.impl.MysqlThemeDao;
import model.entity.*;

public abstract class DAOFactory {

    public enum DBName {
        MYSQL_DB, ORACLE_DB
    }

    private static volatile DAOFactory daoFactory;

    public abstract IUserDAO<User, Integer> getUserDAO();

    public abstract ITestDAO<Test,Integer> getTestDao();

    public abstract IQuestionDAO<Question, Integer> getQuestionDao();

    public abstract IThemeDAO<Theme, Integer> getThemeDAO();

    public abstract IAnswerDAO<Answer, Integer> getAnswerDao();

    public static DAOFactory getInstance(DBName nameDB) {


        if (daoFactory == null) {
            synchronized (DAOFactory.class) {
                if (daoFactory == null) {
                    switch (nameDB) {
                        case MYSQL_DB:
                            daoFactory = new MysqlDAOFactory();
                            break;
                        case ORACLE_DB:
//                            return new OracleDAOFactory();
                            break;
                        default:
                            System.out.println("3 here");
                            daoFactory = null;
                    }
                }
            }
        }
        return daoFactory;
    }
}
