package dao.impl;

import dao.AbstractDao;
import dao.IThemeDAO;
import model.entity.Theme;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlThemeDao extends AbstractDao<Theme, Integer> implements IThemeDAO<Theme, Integer> {
    final static Logger logger = Logger.getLogger(MysqlThemeDao.class);

    public static String GET_ALL_COURSES_WITH_QUESTION = "SELECT Themes.theme_name, questions.question  " +
            "FROM student_testing.Themes join questions on Themes.id = questions.theme_id; ";
    public static String GET_ALL_THEMES = "select * from themes";
    public static String GET_ALL_COURSES_NAME = "SELECT DISTINCT themes.course_name FROM themes;";

    public MysqlThemeDao(Connection connection) {
        super(connection);
    }


    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public List<Theme> parseResultSet(ResultSet resultSet) {
        List<Theme> courses = new ArrayList<>();
        try {
            while (resultSet.next()) {
                courses.add(new Theme(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<String> getCourseName() {
        List<String> courses = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_COURSES_NAME)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                courses.add(resultSet.getString(1));
            }
            return (courses.size() != 0) ? courses : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSelectQuery() {
        return GET_ALL_THEMES;
    }

    //используется с join в questions с именем курса
    @Override
    public String getSelectQueryForList() {
        return getSelectQuery();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Theme object) {
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Theme object) {
    }

    @Override
    public Theme update(Theme entity) {
        return null;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }
}
