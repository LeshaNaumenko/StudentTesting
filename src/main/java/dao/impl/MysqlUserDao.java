package dao.impl;

import dao.AbstractDao;
import dao.IUserDAO;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDao extends AbstractDao<User, Integer> implements IUserDAO<User, Integer> {
    final static Logger logger = Logger.getLogger(MysqlUserDao.class);

    public static String GET_ALL_USERS = "SELECT * FROM user";
    public static String INSERT_USER = "INSERT INTO `student_testing`.`user` ( `first_name`,`last_name`,`email`,`password`,`salt`,`role`) VALUES (?,?,?,?,?,?);";
    public MysqlUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return INSERT_USER;
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();

            while (resultSet.next()){

                users.add(new User.Builder()
                        .setId(resultSet.getInt(1))
                        .setFirstName(resultSet.getString(2))
                        .setLastName(resultSet.getString(3))
                        .setEmail(resultSet.getString(4))
                        .setHash(resultSet.getString(5))
                        .setSalt(resultSet.getBytes(6))
                        .setRole(User.Role.valueOf(resultSet.getString(7)))
                        .build()
                        );
            }
            return users;
    }
    @Override
    public String getSelectQuery() {
        return GET_ALL_USERS;
    }

    //используется с join в questions с именем курса
    @Override
    public String getSelectQueryForList() {
        return getSelectQuery();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getPassword());
        statement.setBytes(5, entity.getSalt());
        statement.setString(6, entity.getRole().name());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) {
    }

    @Override
    public User update(User entity) {
        return null;
    }


    @Override
    public boolean delete(Integer id) {
        return false;
    }

}
