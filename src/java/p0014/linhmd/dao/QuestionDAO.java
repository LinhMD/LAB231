package p0014.linhmd.dao;

import org.apache.log4j.Logger;
import p0014.linhmd.dto.Question;
import p0014.linhmd.ultilities.SQLQuery;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class QuestionDAO {

    private static final Logger LOGGER = Logger.getLogger(QuestionDAO.class);
    public static final int PAGE_LENGTH = 11;

    public List<Question> getQuestion(int page, String subject, String content, int status) throws SQLException {
        String sql = "select q.id, q.question_content, q.a, q.b, q.c, q.d, q.answer_correct, q.create_date, q.subjectID \n"
                + "from _question q\n"
                + "where q.status = ?\n";
        int tag = 0;

        if (subject != null && !subject.trim().isEmpty()) {
            sql += " and q.subjectID = ? \n";
            tag += 1;
        }

        if (content != null && !content.trim().isEmpty()) {
            sql += " and q.question_content like ? \n";
            System.out.println(content);
            tag += 3;
        }

        int offset = page * PAGE_LENGTH;
        sql += "ORDER by create_date\n"
                + "offset ? row fetch next " + PAGE_LENGTH + " row only \n";

        Vector<Vector<String>> result = null;
        switch (tag) {
            case 0:
                result = SQLQuery.executeQuery(sql, status, offset);
                break;
            case 1:
                result = SQLQuery.executeQuery(sql, status, subject, offset);
                break;
            case 3:
                result = SQLQuery.executeQuery(sql, status, "%" + content + "%", offset);
                break;
            case 4:
                result = SQLQuery.executeQuery(sql, status, subject, "%" + content + "%", offset);
                break;
        }
        return result
                .stream()
                .map(Question::new)
                .collect(Collectors.toList());

    }

    public Question getQuestionByID(int id) throws SQLException {
        String sql = "select q.id, q.question_content, q.a, q.b, q.c, q.d, q.answer_correct, q.create_date, q.subjectID \n"
                + "from _question q\n"
                + "where q.id = ?\n";
        return new Question(SQLQuery.executeQuery(sql, id).get(0));
    }

    public List<Question> getAllQuestionFromSubject(String subjectID) {
        String sql = "select q.id, q.question_content, q.a, q.b, q.c, q.d, q.answer_correct, q.create_date, q.subjectID \n"
                + "from _question q\n"
                + "where q.status = 1 and q.subjectID = ?\n";
        try {
            return SQLQuery.executeQuery(sql, subjectID)
                    .stream()
                    .map(Question::new)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public int getMaxQuestionID() throws SQLException {
        String sql = "select max(q.id) + 1 from _question  q";
        return Integer.parseInt(SQLQuery.executeQuery(sql).get(0).get(0));
    }

    public boolean addQuestion(Question question) {
        String sql = "insert into _question (id, question_content, a, b, c, d, answer_correct, create_date, subjectID)\n"
                + "values ((select max(q.id) + 1 from _question  q), ?, ?, ?, ?, ? , ?, ?, ?)";
        if (question == null) {
            return false;
        }

        try {
            return SQLQuery
                    .executeNonQuery(sql,
                            question.getContent(),
                            question.getAnsA(),
                            question.getAnsB(),
                            question.getAnsC(),
                            question.getAnsD(),
                            question.getCorrect() + "",
                            question.getCreateDate(),
                            question.getSubjectID());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public boolean setQuestionStatus(int questionID, int status) {
        String sql = "update _question\n"
                + "set status = ?\n"
                + "where id = ?";
        try {
            return SQLQuery.executeNonQuery(sql, status, questionID);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public boolean updateQuestion(Question question) {
        String sql = "update _question\n"
                + "set question_content = ?, a = ? , b = ?, c = ?, d = ?, answer_correct = ?, subjectID = ?\n"
                + "where id = ?";
        try {
            return SQLQuery
                    .executeNonQuery(sql,
                            question.getContent(),
                            question.getAnsA(),
                            question.getAnsB(),
                            question.getAnsC(),
                            question.getAnsD(),
                            question.getCorrect() + "",
                            question.getSubjectID(),
                            question.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

}
