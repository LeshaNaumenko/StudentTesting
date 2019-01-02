package model.dao;

import exceptions.PersistException;
import model.entity.Test;
import model.entity.TestDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TestDao extends AbstractDao<Test, Integer> {
    static final String INSERT_TEST = "INSERT INTO student_testing.test (user_id, theme_id,status,grade,start_time,end_time, test_time,date ) VALUES (?,?,?,?,?,?,?,?)";

    public static String GET_STUDENT_RESULTS = "SELECT test.id, themes.course_name, themes.theme_name, test.date, test.start_time, test.end_time, themes.time, test.test_time, themes.passing_grade, test.grade, test.status FROM `student_testing`.`test` join `student_testing`.`themes` on\n" +
            "`test`.`theme_id` = `themes`.`id` where user_id = ? ORDER BY `test`.`date` desc;";

    static final String GET_ALL_TEST = "SELECT * FROM student_testing.test";

    public TestDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Test> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Test> tests = new ArrayList<>();

        while (resultSet.next()) {
            tests.add(new Test(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    Test.Status.valueOf(resultSet.getString(4)),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        return tests;
    }

    @Override
    public String getSelectQuery() {
        return GET_ALL_TEST;
    }

    //используется с join в questions с именем курса
    @Override
    public String getSelectQueryForList() {
        return getSelectQuery();
    }

    @Override
    public Test update(Test entity) {
        return null;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Test entity) {
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Test entity) throws SQLException {
        statement.setInt(1, entity.getUser_id());
        statement.setInt(2, entity.getTheme_id());
        statement.setString(3, entity.getStatus().name());
        statement.setInt(4, entity.getGrade());
        statement.setString(5, entity.getStart_time());
        statement.setString(6, entity.getEnd_time());
        statement.setString(7, entity.getTest_time());
        statement.setString(8, entity.getDate());
    }

    @Override
    public String getCreateQuery() {
        return INSERT_TEST;
    }

    public List<TestDTO> getResultsForStudent(Integer id) throws PersistException {
        List<TestDTO> listTestDTO = new ArrayList<>();
        String sql = GET_STUDENT_RESULTS;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listTestDTO.add(new TestDTO.TestBuilder()
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
            throw new PersistException(e);
        }
        return listTestDTO;
    }
}
