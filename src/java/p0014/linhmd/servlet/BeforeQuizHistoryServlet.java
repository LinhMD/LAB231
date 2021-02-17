package p0014.linhmd.servlet;

import org.apache.log4j.Logger;
import p0014.linhmd.dao.QuizDAO;
import p0014.linhmd.dto.QuizResult;
import p0014.linhmd.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class BeforeQuizHistoryServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(BeforeQuizHistoryServlet.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Properties action = (Properties) this.getServletContext().getAttribute("ACTION");
		String url = action.getProperty("LoginPage");
		try{
			HttpSession session = request.getSession(false);
			String page = request.getParameter("pageNum");
			int pageNum = page == null ? 0 : Integer.parseInt(page);
			String type = request.getParameter("btnAction");
			if (type != null) {
				switch (type) {
					case "Previous":
						if(pageNum > 0)
							pageNum--;
						break;
					case "Next":
						pageNum++;
						break;
				}
			}
			if(session != null){
				User user = (User) session.getAttribute("USER");
				if(user != null){

					QuizDAO quizDAO = new QuizDAO();
					List<QuizResult> quizResults = quizDAO.getQuizResultsOfUser(user.getEmail(), pageNum);
					if(quizResults.size() > QuizDAO.PAGE_LENGTH - 1){
						request.setAttribute("hasNext", true);
						quizResults.remove(quizResults.size() - 1);
					}
					session.setAttribute("QUIZ_RESULTS", quizResults);

					url = action.getProperty("QuizHistoryView");
				}
			}
			request.setAttribute("page", pageNum);
		}catch (NumberFormatException | NullPointerException ignore){

		}catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
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
