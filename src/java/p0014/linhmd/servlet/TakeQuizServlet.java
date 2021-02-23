/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import p0014.linhmd.dto.Answer;
import p0014.linhmd.dto.Question;
import p0014.linhmd.dto.QuizResult;

/**
 *
 * @author USER
 */
public class TakeQuizServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TakeQuizServlet.class);
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
        response.setContentType("text/html;charset=UTF-8");
        Properties action = (Properties) this.getServletContext().getAttribute("ACTION");
        String url = action.getProperty("TakeQuizView");
        try{
            HttpSession session = request.getSession(false);
            if(session != null){
                List<Question> quiz  = (List<Question>) session.getAttribute("QUIZ");
                
                if(quiz != null){
                    String index = request.getParameter("index");
                    int i = index == null || index.trim().isEmpty()? 0: Integer.parseInt(index);
                    
                    String ans = request.getParameter("ans");
                    QuizResult result = (QuizResult) session.getAttribute("QUIZ_RESULT");
                    
                    if(ans != null){
                        Question curQuestion = quiz.get(i);
                        Answer answer = curQuestion.getAnswers().get(ans.charAt(0) - 'a');
                        result.put(curQuestion, answer);
                    }
                    
                    String btn = request.getParameter("btnAction");
                    if(btn != null){
                        if(btn.equals("Next") && i < quiz.size() - 2){
                            i++;
                        }else if(btn.equals("Previous") && i > 0){
                            i--;
                        }else if(btn.equals("Submit"))
                            url = action.getProperty("Submit");
                    }
                    Question nextQuestion = quiz.get(i);
                    request.setAttribute("index", i);
                    request.setAttribute("QUESTION", nextQuestion);
                    request.setAttribute("USER_ANSWER", result.get(nextQuestion));
                }
                
            }else{
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
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
