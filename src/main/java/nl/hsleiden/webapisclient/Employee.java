/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.webapisclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author fadministrator
 */
public class Employee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String achternaam = request.getParameter("achternaam");
        if (achternaam == null || achternaam.trim().isEmpty()) {
            throw new ServletException("Geen parameter ingevoerd");
        }

        Client c = new Client();
        Properties props = new java.util.Properties();
        
        InputStream in = Employee.class.getResourceAsStream("/webapis.properties");
        props.load(in);
        WebResource  resource = c.resource(props.getProperty("employeeurl") + "/" + achternaam);
        WebResource.Builder builder = resource.header("Range", "Tada!");
        
        String result = builder.accept("application/json").get(String.class);
        
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(result);
        //bepaal of er 1 object is gevonden of een array van objecten 
        if (jsonObject.get("employees") instanceof JSONObject) {
            JSONObject person = jsonObject.getJSONObject("employees");
            request.setAttribute("person", person);
        } else if (jsonObject.get("employees") instanceof JSONArray) {
            JSONArray arr = JSONArray.fromObject(jsonObject.getJSONArray("employees"));
            request.setAttribute("persons", arr);
        }
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("toonzoekresultaat.jsp").forward(request, response);

    }
}
