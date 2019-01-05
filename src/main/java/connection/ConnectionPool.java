package connection;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class ConnectionPool {
    private static volatile DataSource dataSource;
    private static volatile Connection connection = null;

    public static Connection createConnection() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("db");
            String url = resource.getString("url");
            String user = resource.getString("user");
            String pass = resource.getString("password");
            String driver = resource.getString("driver");
            Class.forName(driver);

            connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ResourceBundle resource = ResourceBundle.getBundle("db");
                    ds.setDriverClassName(resource.getString("driver"));
                    ds.setUrl(resource.getString("url"));
                    ds.setUsername(resource.getString("user"));
                    ds.setPassword(resource.getString("password"));
                    ds.setMinIdle(Integer.valueOf(resource.getString("min")));
                    ds.setMaxIdle(Integer.valueOf(resource.getString("max")));
                    ds.setMaxOpenPreparedStatements(Integer.valueOf(resource.getString("statements")));
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
