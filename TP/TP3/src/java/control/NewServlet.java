/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

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
import model.bean;

/**
 *
 * @author sherm
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

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
        int nbrpara = 3;

        Map<String, String[]> hm = request.getParameterMap();
        ArrayList<Integer> numssn = new ArrayList<>();
        ArrayList<Integer> multiple = new ArrayList<>();
        ArrayList<Integer> bonus = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : hm.entrySet()) {
            if ("numssn".equals(entry.getKey())) {
                for (int i = 0; i < nbrpara; i++) {
                    try {
                        numssn.add(Integer.valueOf(entry.getValue()[i]));
                    } catch (Exception e) {
                        numssn.add(0);
                    }
                }
            }
            if ("multiple".equals(entry.getKey())) {
                for (int i = 0; i < nbrpara; i++) {
                    try {
                        multiple.add(Integer.valueOf(entry.getValue()[i]));
                        bonus.add(Integer.valueOf(entry.getValue()[i]) * 100);
                    } catch (Exception e) {
                        multiple.add(0);
                        bonus.add(0);
                    }
                }
            }
        }
        bean beans[] = new bean[nbrpara];
        for (int i = 0; i < nbrpara; i++) {
            bean b = new bean();
            b.setNumssn(numssn.get(i));
            b.setMultiple(multiple.get(i));
            b.setBonus(bonus.get(i));
            beans[i] = b;
        }
        request.setAttribute("requete", beans);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/bonus.jsp");
        rd.forward(request, response);

        /*          int number = Integer.parseInt(request.getParameter("number"));
            int multiple = Integer.parseInt(request.getParameter("multiple"));
            int bonus = multiple * 100;  
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet test</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bonus Calculation</h1>");
            out.println("<h3>Social security number: " + number + "</h3>");
            out.println("<h3>Multiplier: " + multiple + "</h3>");
            out.println("<h3>Bonus Amount: " + bonus + "</h3>");
            out.println("</body>");
            out.println("</html>");
         */
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
