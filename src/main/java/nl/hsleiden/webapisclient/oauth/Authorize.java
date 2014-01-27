/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.webapisclient.oauth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.hsleiden.webapisclient.Employee;
import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;

/**
 *
 * @author hl
 */
public class Authorize extends HttpServlet {

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
       
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Authorize.class.getName());
        logger.debug("In authorize servlet");
        
        response.setContentType("text/html;charset=UTF-8");
        String clientId = getInitParameter("clientId");
        String clientSecret = getInitParameter("clientSecret");

        ClientIdentifier clientIdentifier = new ClientIdentifier(clientId, clientSecret);
        log("ClientIdentifier aangemaakt" + clientId);
        final OAuth2CodeGrantFlow flow = OAuth2ClientSupport.authorizationCodeGrantFlowBuilder(
                clientIdentifier,
                "http://localhost:8080/AuthorizationServices/Consent",
                "http://localhost:8080/AuthorizationServices/v1/accesstoken").build();

        OAuthTokenHolder holder = new OAuthTokenHolder();
        holder.setFlow(flow);
        request.getSession().setAttribute("oAuthTokenHolder", holder);
        String authURI = flow.start();

        log(" Flow started. Returnvalue:  " + authURI);
        response.sendRedirect(authURI);
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
