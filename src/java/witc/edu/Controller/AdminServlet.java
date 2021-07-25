/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package witc.edu.Controller;

import db.EmployeeDb;
import db.VehicleDb;
import edu.witc.business.Customer;
import edu.witc.business.Employee;
import edu.witc.business.EmployeeRoleType;
import edu.witc.business.Vehicle;
import edu.witc.utility.PasswordUtil;
import edu.witc.utility.ValidatorUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
public class AdminServlet extends HttpServlet {

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
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Cache-Control", "must-revalidate");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");

        // String username = (String)request.getSession().getAttribute("authorized"); 
        Boolean authorized = (Boolean) request.getSession().getAttribute("authorized");
        if (authorized == false) {
            request.getSession().invalidate();
            response.sendRedirect("admin");
        } else {
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
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
        HttpSession session = request.getSession();
        String action = request.getParameter("actionRequest");
        String url;
        switch (action) {
            case "home":
                url = "/admin/adminHome.jsp";
                break;

            case "list_vehicles":
            
                try {
                Vehicle.createVehicleList(request);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            url = "/admin/manage_vehicle.jsp";
            break;

            case "list_customers":
                //calls a method to create the list of customers and brings user to the manage 
                //customer page where the customers are listed
            
                try {
                Customer.createCustomerList(request);
            } catch (SQLException ex) {
                throw new ServletException("doPost/createCustomerList - Servlet: " + ex);
            }

            url = "/admin/manage_customer.jsp";
            break;
            case "employee_page": {
                try {
                    Employee.createEmployeeList(request);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            url = "/admin/employee_list.jsp";
            break;
            case "update_employee":
                int id = Integer.parseInt(request.getParameter("id"));
                Employee employee = EmployeeDb.getEmployeeById(id);
                session.setAttribute("employee", employee);
                 {
                    try {
                        EmployeeRoleType.createEmployeeRoleList(request);
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                url = "/admin/employee_update.jsp";
                break;

            case "add_employee": {
                try {
                    EmployeeRoleType.createEmployeeRoleList(request);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            url = "/admin/employee_new.jsp";
            break;

            default:
                url = "/admin/adminHome.jsp";
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

        switch (requestAction) {
            case "update_vehicle":
                double costPerDay = Double.parseDouble(request.getParameter("vehicleCost"));
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                try {
                    VehicleDb.updateVehicle(vehicleId, costPerDay);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Vehicle.createVehicleList(request);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                processRequest(request, response, "/admin/manage_vehicle.jsp");
                break;
            case "add_employee":
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                if (validateFirstName(request) & validateLastName(request)) {
                    String username = Employee.generateUserName(firstName, lastName);
                    EmployeeRoleType roleType = EmployeeRoleType.getEmployeeRoleType(request, Integer.parseInt(request.getParameter("employeeRole")));
                    int roleTypeId = roleType.getId();
                    String salted = PasswordUtil.getSalt();
                    String password = PasswordUtil.generatePassword();
                    String hashed="";
            try {
                hashed = PasswordUtil.hashPassword(salted + password);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                    Employee employee = new Employee(firstName, lastName, roleTypeId, username, salted, hashed);

                    try {
                        EmployeeDb.insertEmployee(employee);
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     Map<String, String> userMap = new HashMap<>();
                     userMap.put("username", username);
                     userMap.put("password", password);
                     userMap.put("salted", salted);
                     userMap.put("hashed", hashed);
                     
                request.setAttribute("userMap", userMap);
                   // request.setAttribute("username", username);
                  //  request.setAttribute("password", password);
                } else {
                    //runs when submit is clicked on employee_new.jsp and validateCustomerDate()determines the form is not valid.
                    //sets errorMessage
                    String errorMessage = "Please enter valid data!";
                    request.setAttribute("errorMessage", errorMessage);
                }
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);

                processRequest(request, response, "/admin/employee_new.jsp");
                break;
            case "update_employee":
                firstName = request.getParameter("firstName");
                lastName = request.getParameter("lastName");
                Boolean isActive = Boolean.parseBoolean(request.getParameter("status"));
                EmployeeRoleType roleType = EmployeeRoleType.getEmployeeRoleType(request, Integer.parseInt(request.getParameter("employeeRole")));
                int roleTypeId = roleType.getId();
                    
                if (validateFirstName(request) & validateLastName(request)) {
                    int id = Integer.parseInt(request.getParameter("id"));   
                    Employee employee = EmployeeDb.getEmployeeById(id);
                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setRoleTypeId(roleTypeId);
                    employee.setIsActive(isActive);
                    try {
                        EmployeeDb.updateEmployee(employee);
                        Employee.createEmployeeList(request);
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    processRequest(request, response, "/admin/employee_list.jsp");
                }//end if
                else{
                   //runs when submit is clicked on employee_update.jsp and the form is not valid.
                    //sets errorMessage and sets variables to request
                    String errorMessage = "Please enter valid data!";
                    request.setAttribute("errorMessage", errorMessage);
                
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("isActive", isActive);
                request.setAttribute("roleId", roleTypeId);    
                processRequest(request, response, "/admin/employee_update.jsp");
                }
                break;

        }

    }

    private Boolean validateFirstName(HttpServletRequest request) {
        //called from validateCustomerData
        Boolean isValid;
        String firstName = request.getParameter("firstName").strip();
        isValid = ValidatorUtil.isAlphabetic(firstName);
        if (isValid) {
            request.setAttribute("firstName", firstName);
        }

        return isValid;
    }

    private Boolean validateLastName(HttpServletRequest request) {
        //called from validateCustomerData
        Boolean isValid;
        String lastName = request.getParameter("lastName").strip();
        isValid = ValidatorUtil.isAlphabetic(lastName);
        if (isValid) {
            request.setAttribute("lastName", lastName);
        }
        return isValid;
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
