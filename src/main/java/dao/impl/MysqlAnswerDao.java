package dao.impl;

import dao.AbstractDao;
import dao.IAnswerDAO;
import model.entity.Answer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MysqlAnswerDao extends AbstractDao<Answer, Integer> implements IAnswerDAO<Answer, Integer> {
    final static Logger logger = Logger.getLogger(MysqlAnswerDao.class);

    public MysqlAnswerDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    protected List<Answer> parseResultSet(ResultSet resultSet) throws SQLException {return null;}

    @Override
    public String getSelectQuery() {
        return null;
    }

    @Override
    public String getSelectQueryForList() {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Answer object) throws SQLException {}

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Answer object) {}

    @Override
    public Answer update(Answer entity) {
        return null;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }
}
