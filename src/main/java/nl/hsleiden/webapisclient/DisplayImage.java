/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.webapisclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author hl
 */
public class DisplayImage extends HttpServlet {

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
        
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DisplayImage.class.getName());
        
        request.setCharacterEncoding("UTF-8");
        String studentnummer = request.getParameter("studentnummer");
        if (studentnummer == null || studentnummer.trim().isEmpty()) {
            throw new ServletException("Geen parameter ingevoerd");
        }

        Client c = new Client();
        Properties props = new java.util.Properties();
        
        InputStream in = Student.class.getResourceAsStream("/webapis.properties");
        props.load(in);
        
        WebResource  resource = c.resource(props.getProperty("imageurl") + "/" + studentnummer);
        WebResource.Builder builder = resource.header("Range", "Tada!");
       
        String result = builder.accept("application/json").get(String.class);
        logger.debug("Resultaat: " + result);
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(result);
        //bepaal of er 1 object is gevonden of een array van objecten 
        logger.debug(jsonObject.get("image"));
        
        request.setAttribute("image", jsonObject);
         
        
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("displayimage.jsp").forward(request, response);

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
