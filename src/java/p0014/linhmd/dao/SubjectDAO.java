package p0014.linhmd.dao;

import p0014.linhmd.dto.Subject;
import p0014.linhmd.ultilities.SQLQuery;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectDAO {
	public List<Subject> getAllSubject() throws SQLException {

		return SQLQuery.executeQuery("select s.code, s.name, s.time, s.question_limit from _subject s")
				.stream()
				.map(Subject::new)
				.collect(Collectors.toList());
	}
}
