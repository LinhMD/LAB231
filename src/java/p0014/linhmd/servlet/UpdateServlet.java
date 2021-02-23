/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import p0014.linhmd.ultilities.UserError;

/**
 *
 * @author USER
 */
public class UpdateServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdateServlet.class);
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
        String url;
        Properties action = (Properties) this.getServletContext().getAttribute("ACTION");
        url = action.getProperty("UpdateView");
        try {
            String id = request.getParameter("id");
            String content = request.getParameter("content");
            String ansA = request.getParameter("A");
            String ansB = request.getParameter("B");
            String ansC = request.getParameter("C");
            String ansD = request.getParameter("D");
            char correct = request.getParameter("correct").charAt(0);
            String subject = request.getParameter("subject");

            UserError error = new UserError();
            this.validate(error, content, ansA, ansB, ansC, ansD);

            if (!error.isError()) {
                QuestionDAO questionDAO = new QuestionDAO();
                Question question = questionDAO.getQuestionByID(Integer.parseInt(id));
                question.setContent(content.trim());

                question.getAnswers().get(0).setContent(ansA.trim());
                question.getAnswers().get(1).setContent(ansB.trim());
                question.getAnswers().get(2).setContent(ansC.trim());
                question.getAnswers().get(3).setContent(ansD.trim());
                question.getAnswers().forEach(a -> a.setCorrect(false));
                question.getAnswers().get(correct - 'a').setCorrect(true);

                question.setSubjectID(subject);
                if (questionDAO.updateQuestion(question)) {
                    request.setAttribute("message", "Update Question successfully");
                    HttpSession session = request.getSession();
                    session.setAttribute("UPDATE_QUESTION", question);
                    List<Question> questions = (List<Question>) session.getAttribute("QUESTIONS");
                    questions.set(questions.indexOf(question), question);
                } else {
                    request.setAttribute("message", "Update Question failed");
                }
            } else {
                request.setAttribute("message", "Update Question failed");
                request.setAttribute("error", error);
            }
        }catch(Exception ex){
            LOGGER.error(ex.getMessage());
        }finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    private void validate(UserError error, String content, String ansA, String ansB, String ansC, String ansD) {
        if (content == null) {
            error.put("content", "Question content invalid");
        } else if (content.trim().length() == 0) {
            error.put("content", "Question content can not be empty");
        } else if (content.trim().length() > 2048) {
            error.put("content", "Question content to long (> 2048 char)");
        }

        List<String> answers = new ArrayList<>();
        answers.add(ansA);
        answers.add(ansB);
        answers.add(ansC);
        answers.add(ansD);

        for (int i = 0; i < answers.size(); i++) {
            String answer = answers.get(i);

            if (answer == null) {
                error.put(Character.toString((char) (i + 65)), "Answer invalid");
            } else if (answer.length() == 0) {
                error.put(Character.toString((char) (i + 65)), "Answer can not be empty");
            } else if (answer.trim().length() > 255) {
                error.put(Character.toString((char) (i + 65)), "Answer to long (> 255 chars)");
            }

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
