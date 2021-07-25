/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package witc.edu.Controller;

import db.EmployeeDb;
import edu.witc.business.Employee;
import edu.witc.business.EmployeeRoleType;
import edu.witc.utility.PasswordUtil;
import edu.witc.utility.ValidatorUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maggie
 */
public class AuthServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @param url
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

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
        String url = "";
        HttpSession session = request.getSession();
        String action = request.getParameter("actionRequest");
        switch (action) {
            case "login":

                url = "/login.jsp";
                break;
            case "logout":
                session.setAttribute("authorized", false);
                url = "/login.jsp";
                break;
        }
        processRequest(request, response, url);
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
        String action = request.getParameter("actionRequest");
        switch (action) {
            case "logIn": {
                try {
                    userLogIn(request, response);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(AuthServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

        }
//        processRequest(request, response, url);

    }//doPost

    private void userLogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
        //gets email and password from login.jsp, checks to see if the user has the authority to access admin pages based on email and password
        //if the user is in the database it will return true
        //if the user is not in the database it will return false
        //if true then "true" is stored in a session 
        //if false the user is taken to the login in error page
        String username = request.getParameter("username").strip();
        String password = request.getParameter("password").strip();

        if (ValidatorUtil.hasText(username) & ValidatorUtil.hasText(password)) {
            HttpSession session = request.getSession();
            Employee employee = EmployeeDb.getEmployeeByUsername(username);
            EmployeeRoleType roleType = employee.getEmployeeRole();

            if (employee.getId() != -1) {
                if (roleType.getId() == 3 || roleType.getId() == 4) {

                    String hashed = PasswordUtil.hashPassword(employee.getSalted() + password);
                     if(hashed.equals(employee.getHashed()))
                     {
                    session.setAttribute("authorized", true);
                    String url = "/admin/adminHome.jsp";
                    processRequest(request, response, url);
                    }
                     else {
                    processRequest(request, response, "/login_error.jsp");
                    }//else

                    
                } else {
                    processRequest(request, response, "/login_error.jsp");
                }//else
            }//if
            else {
                processRequest(request, response, "/login_error.jsp");
            }
        }//if
        else {
            processRequest(request, response, "/login_error.jsp");
        }
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
