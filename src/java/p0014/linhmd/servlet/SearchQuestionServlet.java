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
import p0014.linhmd.dao.SubjectDAO;
import p0014.linhmd.dto.Question;
import p0014.linhmd.dto.Subject;
import p0014.linhmd.dto.User;

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
        String url = null;
        Properties action = (Properties)this.getServletContext().getAttribute("ACTION");
        try  {
            String subject = request.getParameter("subject");
            String content = request.getParameter("content");
            int status = request.getParameter("status") != null? 0 : 1;
            String page = request.getParameter("pageNum");
            int pageNum = page == null? 0: Integer.parseInt(page);
            String type = request.getParameter("btnAction");
            
            if(type != null){
                switch(type){
                    case "Previous":
                        if(pageNum != 0)
                            pageNum--;
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
            List<Question> questions = questionDAO.getQuestion(pageNum, subject, content, status);
            
            SubjectDAO subjectDAO = new SubjectDAO();
            List<Subject> subjects = subjectDAO.getAllSubject();
            
            HttpSession session = request.getSession(false);
            
            if(session != null){
                session.setAttribute("QUESTIONS", questions);
                session.setAttribute("SUBJECTS", subjects);
                User user = (User) session.getAttribute("USER");
                
                if(user == null){
                    url = action.getProperty("LoginPage");
                    request.setAttribute("message", "User not found, please login!");
                }else{
                    if(user.isAdmin())
                        url = action.getProperty("AdminView");
                    else
                        //TODO:
                        url = action.getProperty("LoginPage");
                }
            }else{
                url = action.getProperty("LoginPage");
                request.setAttribute("message", "Session time out!");
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage());
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
