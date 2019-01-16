package dao.impl;

import dao.AbstractDao;
import dao.ITestDAO;
import exceptions.DAOException;
import model.entity.Test;
import model.entity.TestInfo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MysqlTestDao extends AbstractDao<Test, Integer> implements ITestDAO<Test,Integer> {
    final static Logger logger = Logger.getLogger(MysqlTestDao.class);

    static final String INSERT_TEST = "INSERT INTO student_testing.test (user_id, theme_id,status,grade,start_time,end_time, test_time,date ) VALUES (?,?,?,?,?,?,?,?)";

    public static String GET_STUDENT_RESULTS = "SELECT test.id, themes.course_name, themes.theme_name, test.date, test.start_time, test.end_time, themes.time, test.test_time, themes.passing_grade, test.grade, test.status FROM `student_testing`.`test` join `student_testing`.`themes` on\n" +
            "`test`.`theme_id` = `themes`.`id` where user_id = ? ORDER BY `test`.`date` desc LIMIT ?, ?";
    public static String GET_THE_NUMBER_OF_TESTS = "SELECT COUNT(test.id) FROM `student_testing`.`test` where user_id = ?";

    static final String GET_ALL_TEST = "SELECT * FROM student_testing.test";

    public MysqlTestDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Test> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Test> tests = new ArrayList<>();

        while (resultSet.next()) {
            tests.add(new Test.Builder()
                            .setId(resultSet.getInt(1))
                            .setUserId(resultSet.getInt(2))
                            .setThemeId(resultSet.getInt(3))
                            .setStatus(Test.Status.valueOf(resultSet.getString(4)))
                            .setGrade(resultSet.getInt(5))
                            .setStartTime(resultSet.getString(6))
                            .setEndTime(resultSet.getString(7))
                            .setUserTime(resultSet.getString(8))
                            .setDate(resultSet.getString(9))
                            .build()
        );
        }
        return tests;
    }

    @Override
    public String getSelectQuery() {
        return GET_ALL_TEST;
    }

    /**
     * used with join in questions with the name of the course
     *
     * @return sql request
     */
    @Override
    public String getSelectQueryForList() {
        return GET_ALL_TEST;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Test entity) throws SQLException {
        statement.setInt(1, entity.getUserId());
        statement.setInt(2, entity.getThemeId());
        statement.setString(3, entity.getStatus().name());
        statement.setInt(4, entity.getGrade());
        statement.setString(5, entity.getStartTime());
        statement.setString(6, entity.getEndTime());
        statement.setString(7, entity.getUserTime());
        statement.setString(8, entity.getDate());
    }

    @Override
    public String getCreateQuery() {
        return INSERT_TEST;
    }

    public List<TestInfo> getTestResults(Integer id, Integer start, Integer recordsPerPage) throws DAOException {
        List<TestInfo> listTestInfo = new ArrayList<>();
        String sql = GET_STUDENT_RESULTS;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, recordsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listTestInfo.add(new TestInfo.TestDTOBuilder()
                        .setTestId(resultSet.getInt(1))
                        .setCourseName(resultSet.getString(2))
                        .setThemeName(resultSet.getString(3))
                        .setDate(resultSet.getString(4))
                        .setStartTime(resultSet.getString(5))
                        .setEndTime(resultSet.getString(6))
                        .setThemeTime(resultSet.getInt(7))
                        .setUserTime(resultSet.getString(8))
                        .setPassingGrade(resultSet.getInt(9))
                        .setGrade(resultSet.getInt(10))
                        .setStatus(Test.Status.valueOf(resultSet.getString(11)))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Error getting results for user. \n" +
                    "Error message: " + e.getMessage());
            throw new DAOException(e);
        }
        return listTestInfo;
    }

    public Integer getNumberOfRows(Integer id) throws DAOException {
        int numOfRows = 0;
            String sql = GET_THE_NUMBER_OF_TESTS;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                numOfRows = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error getting number of rows of the test \n" +
                    "Error message: " + e.getMessage());
            throw new DAOException(e);
        }
        return numOfRows;
    }
}
