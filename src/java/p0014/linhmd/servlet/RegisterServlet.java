/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import p0014.linhmd.dao.UserDAO;
import p0014.linhmd.dto.User;
import p0014.linhmd.ultilities.Sha256;
import p0014.linhmd.ultilities.UserError;

/**
 *
 * @author USER
 */
public class RegisterServlet extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String url = null;
        Properties action = (Properties)this.getServletContext().getAttribute("ACTION");
        url = action.getProperty("LoginPage");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String comfirm = request.getParameter("comfirm");
        String username = request.getParameter("username");
        UserError error = new UserError();
        try  {
            this.validate(email, password, comfirm, username, error);
            
            if(error.isError()){
                request.setAttribute("registerError", error);
            }else{
                password = Sha256.encrypt(password);
                User user = new User(email, username, false);
                
                if(new UserDAO().registerNewUser(user, password)){
                    request.setAttribute("message", "Sign up successfully");
                }
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (SQLException ex) {
            if(ex.getMessage().contains("duplicate")){
                request.setAttribute("message", "Email already existed!!!");
            }else
                LOGGER.error(ex.getMessage() + " Cause by: " + ex.getCause().toString());
        }finally{
            request.setAttribute("isSignUp", true);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    private void validate(String email, String password, String comfirm, String name, UserError error) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        if (email.trim().isEmpty()) {
            error.put("email", "email can not be empty!!");
        }

        if (password.trim().isEmpty()) {
            error.put("password", "password can not be empty!!");
        } else if (!password.equals(comfirm)) {
            error.put("comfirm", "Comfirm must match password!!");
        }

        if (name.trim().isEmpty()) {
            error.put("name", "Name can not be empty!!");
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
