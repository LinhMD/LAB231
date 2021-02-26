/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import p0014.linhmd.dao.QuestionDAO;
import p0014.linhmd.dao.QuizDAO;
import p0014.linhmd.dto.Question;
import p0014.linhmd.dto.Subject;
import p0014.linhmd.dto.User;
import p0014.linhmd.singleton.SubjectList;
import p0014.linhmd.dto.QuizResult;

/**
 *
 * @author USER
 */
public class PrepareQuestionServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PrepareQuestionServlet.class);
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
        String url = "Quiz";
        try{
            String subjectID = request.getParameter("id");
            HttpSession session = request.getSession(false);
            if(session != null){
                
                List<Subject> subjects = SubjectList.getInstance();
                Subject selectedSubject = subjects.get(subjects.indexOf(new Subject(subjectID)));
                QuestionDAO dao = new QuestionDAO();
                List<Question> questions = dao.getAllQuestionFromSubject(subjectID);
                Collections.shuffle(questions);
                List<Question> quiz = new ArrayList<>();
                for (int i = 0; i < selectedSubject.getLimit(); i++) {
                    if(i < questions.size()){
                        quiz.add(questions.get(i));
                    }
                }
                
                session.setAttribute("QUIZ", quiz);
                session.setAttribute("QUIZ_SUBJECT", selectedSubject);
                request.setAttribute("QUESTION", quiz.get(0));
                
                User quizTaker = (User) session.getAttribute("USER");
                QuizResult result = new QuizResult(0,
                                            quizTaker.getEmail(),
                                            selectedSubject.getCode(),
                                            Calendar.getInstance().getTime());
                quiz.forEach(q -> result.put(q, null));
                session.setAttribute("QUIZ_RESULT", result);
                
                Calendar now = Calendar.getInstance();
                now.add(Calendar.MINUTE, selectedSubject.getTime());

                Date endTime = now.getTime();
                session.setAttribute("END_TIME", endTime.getTime());
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(60000 * selectedSubject.getTime() + 5000);
                        QuizResult quizResult = (QuizResult) session.getAttribute("QUIZ_RESULT");
                        if(quizResult != null){
                            QuizDAO quizDAO = new QuizDAO();
                            quizDAO.insertQuizResult(quizResult);
                            session.removeAttribute("QUIZ");
                            session.removeAttribute("END_TIME");
                            session.removeAttribute("QUIZ_RESULT");
                            session.removeAttribute("QUIZ_SUBJECT");
                        }
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }
                });
                thread.start();
            }else{
                url = "LoginPage";
            }
        }catch(ArrayIndexOutOfBoundsException | NullPointerException ignored){
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }finally{
            response.sendRedirect(url);
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
