/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.User;
/**
 *
 * @author hgoyat.ir2023
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        ArrayList<User> users = new ArrayList<User>();
        Connection con = Servlet.connect();
        User.createTable(con);
        
        try {
            Map inputs = request.getParameterMap();

            String usernames[] = request.getParameterValues("username");
            String firstnames[] = request.getParameterValues("firstname");
            String lastnames[] = request.getParameterValues("lastname");
            
            for(int i=0; i<usernames.length;i++){
                try {
                    if (usernames[i]!="" && firstnames[i]!="" && lastnames[i]!=""){
                        
                        User nu = new User();
                        nu.setUsername(usernames[i]);
                        nu.setFirstname(firstnames[i]);
                        nu.setLastname(lastnames[i]);
                        nu.insertUser(con);
                    }
                    
                } catch(Exception e){
                    System.out.println(e);
                } finally {
                }
            }
            users.addAll(User.getUsers(con));

            
            request.setAttribute("parameters", users);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/reponse.jsp");
            rd.forward(request, response);
            System.out.println(inputs);
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally {        
            out.close();
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

    public static Connection connect(){
        String url = "jdbc:mysql://localhost:3306/project?zeroDateTimeBehavior=convertToNull";
        String utilisateur = "root";
        String motDePasse = "root";
        Connection connection = null;
        try {
            System.out.println( "Chargement du driver..." );
            Class.forName( "com.mysql.jdbc.Driver" );  //« "org.apache.derby.jdbc.ClientDriver«  for derby
            System.out.println( "Driver chargé !" );
        } catch ( ClassNotFoundException e ) {
            System.out.println( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath !"
                    + e.getMessage() );
        }
        try {
            connection = DriverManager.getConnection( url, utilisateur, motDePasse );
        }
        catch(SQLException e){
            System.out.println("Controller.Servlet.connect()");
            System.out.println("Erreur de connexion");
            System.out.println(e);
        }
        return connection;
    }
}
