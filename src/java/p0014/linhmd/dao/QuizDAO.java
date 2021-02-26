package p0014.linhmd.dao;

import p0014.linhmd.dto.Question;
import p0014.linhmd.dto.QuizResult;
import p0014.linhmd.ultilities.SQLQuery;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class QuizDAO {

	public static final int PAGE_LENGTH = 5;

	public int getMaxID(){
		String sql = "select max(id) from _quiz_history";
		try{
			return Integer.parseInt(SQLQuery.executeQuery(sql).get(0).get(0)) + 1;
		}catch (Exception ignore){
			return 0;
		}

	}

	public QuizResult getQuizResult(int id) throws SQLException {
		String sql = "select id, quiz_taker, subject, time, point \n" +
				"from _quiz_history\n" +
				"where id = ?";
		QuizResult result = new QuizResult(SQLQuery.executeQuery(sql, id).get(0));
		sql = "select question_id, answer_id from _quiz_detail\n" +
				"where quiz_id = ?";
		result.addAllResult(SQLQuery.executeQuery(sql, id));
		return result;
	}

	public List<QuizResult> getQuizResultsOfUser(String email, int page) throws SQLException {
		String sql = "select id, quiz_taker, subject, time, point \n" +
				"from _quiz_history\n" +
				"where quiz_taker = ? \n"+
				"order by time ASC\n" +
				"offset ? row fetch next " + PAGE_LENGTH + " row only \n";
		Vector<Vector<String>> vectors = SQLQuery.executeQuery(sql, email, page * PAGE_LENGTH);
		List<QuizResult> results = vectors.stream().map(QuizResult::new).collect(Collectors.toList());
		sql = "select question_id, answer_id from _quiz_detail\n" +
				"where quiz_id = ?";
		for (QuizResult result : results) {
			result.addAllResult(SQLQuery.executeQuery(sql, result.getId()));
		}
		return results;
	}

	public void insertQuizResult(QuizResult result) throws SQLException {
		String sql = "insert into _quiz_history(id, quiz_taker, subject, time, point)\n" +
				"values (?, ?, ?, ?, ?)";
		int id= this.getMaxID();
		if(SQLQuery.executeNonQuery(sql, id , result.getQuizTaker(), result.getSubject(), result.getDate(), result.getPoint())){
			sql = "insert into _quiz_detail (quiz_id, question_id, answer_id)\n" +
					"values (?, ?, ?)";
			for (Question question : result.keySet()) {
				SQLQuery.executeNonQuery(sql,id,  question.getId(),
						result.get(question) == null? 0 : result.get(question) + "");
			}
		}
	}



}
