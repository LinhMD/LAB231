package p0014.linhmd.dao;

import org.apache.log4j.Logger;
import p0014.linhmd.dto.Answer;
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
        String sql = "select q.id, q.question_content, q.create_date, q.subjectID \n"
                + "from _question q\n"
                + "where q.status = ?\n";
        int tag = 0;

        if (subject != null && !subject.trim().isEmpty()) {
            sql += " and q.subjectID = ? \n";
            tag += 1;
        }

        if (content != null && !content.trim().isEmpty()) {
            sql += " and q.question_content like ? \n";
            tag += 3;
        }

        int offset = page * PAGE_LENGTH + status == 0 ? 1: 0;

        sql += "ORDER by create_date ASC \n"
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

        return result.stream()
                    .map(Question::new)
                    .collect(Collectors.toList());

    }

    public Question getQuestionByID(int id) throws SQLException {
        String sql = "select q.id, q.question_content,  q.create_date, q.subjectID \n"
                + "from _question q\n"
                + "where q.id = ?\n";
        return new Question(SQLQuery.executeQuery(sql, id).get(0));
    }

    public List<Question> getAllQuestionFromSubject(String subjectID) {
        String sql = "select q.id, q.question_content,  q.create_date, q.subjectID \n"
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
        String sql = "insert into _question (id, question_content,  create_date, subjectID)\n"
                + "values ((select max(q.id) + 1 from _question  q), ?, ?, ?)";
        if (question == null) {
            return false;
        }
        try {
            question.setId(this.getMaxQuestionID());
            SQLQuery.executeNonQuery(sql,
                            question.getContent(),
                            question.getCreateDate(),
                            question.getSubjectID());
            AnswerDAO answerDAO = new AnswerDAO();
            for (Answer answer : question.getAnswers()) {
                answerDAO.insertAnswer(answer);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
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
                + "set question_content = ?,  subjectID = ?\n"
                + "where id = ?";
        try {
            SQLQuery.executeNonQuery(sql,
                            question.getContent(),
                            question.getSubjectID(),
                            question.getId());
            AnswerDAO answerDAO = new AnswerDAO();
            for (Answer answer : question.getAnswers()) {
                answerDAO.updateAnswer(answer);
            }
            return true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.getAllQuestionFromSubject("IOT101").forEach(System.out::println);
    }

}
