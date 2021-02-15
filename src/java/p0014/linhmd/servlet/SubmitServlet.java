package p0014.linhmd.servlet;

import org.apache.log4j.Logger;
import p0014.linhmd.dao.QuizDAO;
import p0014.linhmd.dto.QuizResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SubmitServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(SubmitServlet.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response){
		try{
			HttpSession session = request.getSession(false);
			if(session != null){
				QuizResult result = (QuizResult) session.getAttribute("QUIZ_RESULT");
				if(result != null){
					session.removeAttribute("QUIZ");
					session.removeAttribute("END_TIME");
					QuizDAO quizDAO = new QuizDAO();
					quizDAO.insertQuizResult(result);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
}
