/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package witc.edu.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maggie
 */
public class CustomerServlet extends HttpServlet {

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
        String action = request.getParameter("actionRequest");
        String url;
        HttpSession session = request.getSession();
        switch (action) {
             case "home":
             //runs when home is clicked in the nav, brings user to home page 
                url = "/index.jsp";
                break;
                
            case "research": 
                    //runs when register is clicked in the nav          
                url = "/research.jsp";
                break;

            case "people":
                //takes the user to a page where they can search a customer by their phone number
                url = "/people.jsp";
                break;
                case "teaching":
                //takes the user to a page where they can search a customer by their phone number
                url = "/teaching.jsp";
                break;
                case "publications":
                //takes the user to a page where they can search a customer by their phone number
                url = "/publications.jsp";
                break;
            default:
                url = "/index.jsp";
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
        String requestAction;
        requestAction = request.getParameter("requestAction");

        if (requestAction != null) {
            switch (requestAction) {
                case "registerCustomer":
                   
                    break;            
        }//switch      
        }//if
    }//method


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
