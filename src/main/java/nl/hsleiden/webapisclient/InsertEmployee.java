/*
 * Copyright 2014 Hogeschool Leiden.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.hsleiden.webapisclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 *
 * @author hl
 */
public class InsertEmployee extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InsertEmployee.class.getName());
        
        Connection conn = null; 
      
        try {
            // Obtain Connection
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/apis"); // thats everything from the context.xml and from the global configuration
 
            logger.debug("Contextlookup: " + ds.toString());
            conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement("Insert into viewmedewerker values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, request.getParameter("id"));
            stmt.setString(2, request.getParameter("achternaam"));
            stmt.setString(3, request.getParameter("voornamen"));
            stmt.setString(4, request.getParameter("lijstnaam"));
            stmt.setString(5, request.getParameter("geslacht"));
            stmt.setString(6, request.getParameter("tussenvoegsel"));
            stmt.setString(7, request.getParameter("email"));
            stmt.setString(8, request.getParameter("telefoon"));
            stmt.setString(9, request.getParameter("mobiel"));
            stmt.setString(10, request.getParameter("locatie"));
            stmt.setString(11, request.getParameter("afdeling"));
            stmt.setString(12, request.getParameter("functie"));
            stmt.setString(13, request.getParameter("medewerker"));
            stmt.setString(14, request.getParameter("accountnaam"));
            stmt.setString(15, request.getParameter("organisatie"));
            
            stmt.executeQuery();
            //redirect
        } catch (Exception e) {

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsertEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' href='style.css' type='text/css'>");
            out.println("<title>Servlet InsertEmployee</title>");            
            out.println("</head>");
            out.println("<body class='formatHTML5'>");
            out.println("<h1>De medewerker is ingevoerd</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
