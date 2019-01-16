package connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class {@code ConnectionPool} is an abstraction layer between our program
 * and different JDBC drivers that provides the database connection pool.
 *
 * @author Alex Naumenko
 */
public final class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());
    private static volatile DataSource dataSource;

    /**
     * Gets {@code BasicDataSource) implementation of {@code DataSource} by configuration file properties of db.
     *
     * @return dataSource
     */
    private static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ResourceBundle resource = ResourceBundle.getBundle("db");
                    ds.setDriverClassName(resource.getString("MYSQL_DB_DRIVER_CLASS"));
                    ds.setUrl(resource.getString("MYSQL_DB_URL"));
                    ds.setUsername(resource.getString("MYSQL_DB_USERNAME"));
                    ds.setPassword(resource.getString("MYSQL_DB_PASSWORD"));
                    ds.setMinIdle(Integer.valueOf(resource.getString("MYSQL_DB_MIN")));
                    ds.setMaxIdle(Integer.valueOf(resource.getString("MYSQL_DB_MAX")));
                    ds.setMaxOpenPreparedStatements(Integer.valueOf(resource.getString("MYSQL_DB_STATEMENTS")));
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

    /**
     * Gets connection.
     *
     * @return connection.
     */
    public static Connection getConnection()  {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
            System.out.println(e.getMessage());
            throw new RuntimeException("Hasn't found connection with database");
        }
    }
}
