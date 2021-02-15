package p0014.linhmd.dao;

import p0014.linhmd.dto.Question;
import p0014.linhmd.dto.QuizResult;
import p0014.linhmd.ultilities.SQLQuery;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class QuizDAO {

	public int getMaxID(){
		String sql = "select max(id) from _quiz_history";
		try{
			return Integer.parseInt(SQLQuery.executeQuery(sql).get(0).get(0)) + 1;
		}catch (Exception ignore){
			return 0;
		}

	}

	public List<QuizResult> getQuizResultOfUser(String email) throws SQLException {
		String sql = "select id, quiz_taker, subject, time, point \n" +
				"from _quiz_history\n" +
				"where quiz_taker = ?";
		Vector<Vector<String>> vectors = SQLQuery.executeQuery(sql, email);
		List<QuizResult> results = vectors.stream().map(QuizResult::new).collect(Collectors.toList());
		sql = "select question_id, answer from _quiz_detail\n" +
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
			sql = "insert into _quiz_detail (quiz_id, question_id, answer)\n" +
					"values (?, ?, ?)";
			for (Question question : result.keySet()) {
				SQLQuery.executeNonQuery(sql,id,  question.getId(), result.get(question) + "");
			}
		}
	}


}
