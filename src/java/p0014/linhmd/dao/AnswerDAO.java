package p0014.linhmd.dao;

import org.apache.log4j.Logger;
import p0014.linhmd.dto.Answer;
import p0014.linhmd.dto.Question;
import p0014.linhmd.ultilities.SQLQuery;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerDAO {
	private static final Logger LOGGER = Logger.getLogger(AnswerDAO.class);

	public List<Answer> getAnswersOfQuestion(Question question) throws SQLException {
		String sql = "select id, content, question_id, is_true from _answer\n" +
				"where question_id = ?";
		return SQLQuery.executeQuery(sql, question.getId())
				.stream()
				.map(Answer::new)
				.map(answer -> answer.setQuestion(question))
				.collect(Collectors.toList());
	}

	public Answer getAnswer(int id) throws SQLException {
		String sql = "select id, content, question_id, is_true from _answer\n" +
				"where id = ?";
		return new Answer(SQLQuery.executeQuery(sql, id).get(1));
	}

	public void updateAnswer(Answer answer) throws SQLException {
		String sql = "update _answer\n" +
				"set content = ?, is_true = ?\n" +
				"where id = ?";
		SQLQuery.executeNonQuery(sql, answer.getContent(), answer.isCorrect()? 1: 0, answer.getId());
	}

	public void insertAnswer(Answer answer) throws SQLException {
		String sql = "insert into _answer(id, content, question_id, is_true)\n" +
				"values ((select max(id) from _answer) + 1, ?, ?, ?)";
		SQLQuery.executeNonQuery(sql, answer.getContent(), answer.getQuestion().getId(), answer.isCorrect());
	}
}
