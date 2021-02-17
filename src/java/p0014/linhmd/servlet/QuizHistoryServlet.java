package p0014.linhmd.servlet;

import org.apache.log4j.Logger;
import p0014.linhmd.dto.QuizResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class QuizHistoryServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(QuizHistoryServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties action = (Properties) this.getServletContext().getAttribute("ACTION");
		String url = action.getProperty("LoginPage");
		try{
			int index = Integer.parseInt(request.getParameter("index"));
			HttpSession session = request.getSession(false);
			List<QuizResult> quizResults = (List<QuizResult>) session.getAttribute("QUIZ_RESULTS");
			QuizResult result = quizResults.get(index);
			request.setAttribute("QUIZ_RESULT", result);
			url = action.getProperty("QuizResultView");
		}catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException ignore){
			ignore.printStackTrace();
		}catch (Exception e){
			LOGGER.error(e.getMessage());
		}finally{
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
}
