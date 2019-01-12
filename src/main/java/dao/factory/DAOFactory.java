package dao.factory;

import dao.*;
import dao.factory.impl.MysqlDAOFactory;
import dao.impl.MysqlAnswerDao;
import dao.impl.MysqlQuestionDao;
import dao.impl.MysqlTestDao;
import dao.impl.MysqlThemeDao;
import model.entity.*;


/**
 * DAOFactory used for getting {@link DAOFactory} to process requests.
 *
 * @author Alex Naumenko
 *
 * @see dao.factory.impl.MysqlDAOFactory
 */
public abstract class DAOFactory {

    /**
     * Database names
     */
    public enum DBName {
        MYSQL_DB, ORACLE_DB
    }

    private static volatile DAOFactory daoFactory;

    /**
     * Return {@link IUserDAO} implementation.
     *
     * @return {@link IUserDAO} implementation.
     */
    public abstract IUserDAO<User, Integer> getUserDAO();

     /**
     * Return {@link ITestDAO} implementation.
     *
     * @return {@link ITestDAO} implementation.
     */
    public abstract ITestDAO<Test,Integer> getTestDao();

     /**
     * Return {@link IQuestionDAO} implementation.
     *
     * @return {@link IQuestionDAO} implementation.
     */
    public abstract IQuestionDAO<Question, Integer> getQuestionDao();

     /**
     * Return {@link IThemeDAO} implementation.
     *
     * @return {@link IThemeDAO} implementation.
     */
    public abstract IThemeDAO<Theme, Integer> getThemeDAO();

     /**
     * Return {@link IAnswerDAO} implementation.
     *
     * @return {@link IAnswerDAO} implementation.
     */
    public abstract IAnswerDAO<Answer, Integer> getAnswerDao();

    /**
     * Returns a new DAOFactory instance for the given database name.
     *
     * @param nameDB The database name to return a new DAOFactory instance for.
     * @return A new DAOFactory instance for the given database name.
     */
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
                            daoFactory = null;
                    }
                }
            }
        }
        return daoFactory;
    }
}
