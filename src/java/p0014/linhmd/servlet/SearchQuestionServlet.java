/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import p0014.linhmd.dao.QuestionDAO;
import p0014.linhmd.dto.Question;
import p0014.linhmd.dto.User;
import p0014.linhmd.singleton.SubjectList;

/**
 *
 * @author USER
 */
public class SearchQuestionServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Properties action = (Properties) this.getServletContext().getAttribute("ACTION");
        String url = action.getProperty("AdminView");
        try {
            String subject = request.getParameter("subject");
            String content = request.getParameter("content");
            int status = request.getParameter("status") != null ? 0 : 1;
            String page = request.getParameter("pageNum");
            int pageNum = page == null ? 0 : Integer.parseInt(page);
            String type = request.getParameter("btnAction");
            List<Question> questions = null;

            HttpSession session = request.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("USER");
                if (user == null) {
                    url = action.getProperty("LoginPage");
                    request.setAttribute("message", "User not found, please login!");
                } else {
                    if (user.isAdmin()) {
                        if (type != null) {
                            switch (type) {
                                case "Previous":
                                    if (pageNum != 0) {
                                        pageNum--;
                                    }
                                    break;
                                case "Next":
                                    pageNum++;
                                    break;
                                case "Search":
                                    pageNum = 0;
                                    break;
                            }
                        }
                        QuestionDAO questionDAO = new QuestionDAO();
                        questions = questionDAO.getQuestion(pageNum, subject, content, status);
                        if (questions.size() > QuestionDAO.PAGE_LENGTH - 1) {
                            request.setAttribute("hasNext", true);
                            questions.remove(questions.size() - 1);
                        }
                        url = action.getProperty("AdminView");
                        if (questions.isEmpty() && status == 0) {
                            request.setAttribute("message", "No question found!");
                        } else {
                            request.setAttribute("QUESTIONS", questions);
                        }
                        session.setAttribute("SUBJECTS", SubjectList.getInstance());
                        request.setAttribute("page", pageNum);
                    } else {
                        url = action.getProperty("LoginPage");
                    }
                }
            } else {
                url = action.getProperty("LoginPage");
                request.setAttribute("message", "Login to continued!!!");
            }

        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
