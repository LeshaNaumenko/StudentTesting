package dao.impl;

import dao.AbstractDao;
import dao.IQuestionDAO;
import exceptions.DAOException;
import model.entity.Question;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MysqlQuestionDao extends AbstractDao<Question, Integer> implements IQuestionDAO<Question, Integer> {
    final static Logger logger = Logger.getLogger(MysqlQuestionDao.class);

    final String GET_ALL_QUESTIONS = "select * from questions";
    final String GET_ALL_QUESTIONS_OF_A_CERTAIN_COURSE =
"select questions.id, questions.theme_id, questions.question, questions.option_1, questions.option_2, questions.option_3, questions.option_4," +
        " questions.correct_option from themes inner join questions on themes.id = questions.theme_id";
    final String GET_ALL_QUESTIONS_OF_A_CERTAIN_COURSES = "select questions.id, questions.theme_id, question_translate.question, question_translate.option_1, question_translate.option_2, \n" +
            "question_translate.option_3, question_translate.option_4, question_translate.correct_option from themes \n" +
            "inner join questions on themes.id = questions.theme_id \n" +
            "inner join question_translate on question_translate.questionId = questions.id\n" +
            "inner join language on question_translate.lang_id = language.id\n" +
            "where language.language =?  and language.country = ? and questions.theme_id = ?;";

    public MysqlQuestionDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return null;
    }
    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Question object) {}

    @Override
    protected List<Question> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Question> questions = new ArrayList<>();
        while (resultSet.next()){
            questions.add(new Question(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
                    ));
        }
        return questions;
    }

    public List<Question> getListOfQuestionWithLocale(Integer themeId, Locale locale) throws DAOException {
        List<Question> questionList;
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUESTIONS_OF_A_CERTAIN_COURSES);
            preparedStatement.setString(1, locale.getLanguage());
            preparedStatement.setString(2, locale.getCountry());
            preparedStatement.setInt(3, themeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            questionList=  parseResultSet(resultSet);

            return  questionList;
        } catch (SQLException e) {
            logger.error("Error getting list of question with locale. \nError message: " + e.getMessage());
            throw new DAOException(e);
        }
    }

    @Override
    public String getSelectQuery() {
        return GET_ALL_QUESTIONS;
    }
    //используется с join в questions с именем курса
    @Override
    public String getSelectQueryForList() {
        return GET_ALL_QUESTIONS_OF_A_CERTAIN_COURSE;
    }
}
