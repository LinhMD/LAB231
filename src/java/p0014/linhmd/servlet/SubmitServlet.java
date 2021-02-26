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
import java.util.Properties;

public class SubmitServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(SubmitServlet.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Properties action = (Properties) this.getServletContext().getAttribute("ACTION");
		String url = action.getProperty("LoginPage");
		try{
			HttpSession session = request.getSession(false);
			if(session != null){
				QuizResult result = (QuizResult) session.getAttribute("QUIZ_RESULT");
				url = action.getProperty("ChooseSubject");
				if(result != null){
					session.removeAttribute("QUIZ");
					session.removeAttribute("END_TIME");
					session.removeAttribute("QUIZ_RESULT");
					session.removeAttribute("QUIZ_SUBJECT");
					QuizDAO quizDAO = new QuizDAO();
					quizDAO.insertQuizResult(result);
					request.setAttribute("QUIZ_RESULT", result);
					url = action.getProperty("QuizResultView");
				}
			}
		}catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}finally {
			request.getRequestDispatcher(url).forward(request, response);
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
