/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.webapisclient;

//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import nl.hsleiden.webapisclient.oauth.OAuthTokenHolder;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.glassfish.jersey.client.oauth2.TokenResult;
import org.glassfish.jersey.jackson.JacksonFeature;

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

        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Employee.class.getName());
        request.setCharacterEncoding("UTF-8");

        if (request.getSession().getAttribute("oAuthTokenHolder") == null && request.getSession().getAttribute("accessToken") == null) {
            logger.debug("Eerste keer in servlet, nu volgt deel 1 van de authorisatie: authoriszation grant door de gebruiker");
            response.sendRedirect("Authorize");
            response.flushBuffer();
        }

        if (request.getSession().getAttribute("accessToken") == null && request.getSession().getAttribute("oAuthTokenHolder") != null) {
            
            logger.debug("Tweede keer in de servlet, er is een authorization grant. Nu nog een accesstoken.");
            OAuthTokenHolder holder = (OAuthTokenHolder) request.getSession().getAttribute("oAuthTokenHolder");
            logger.debug("En dit is de holder: " + holder);
            final OAuth2CodeGrantFlow flow = holder.getFlow();
            String code = (String) request.getParameter("code");
            logger.debug("Code: " + code);
            logger.debug("State: " + request.getParameter("state"));
           
            final TokenResult tokenResult = flow.finish(code, request.getParameter("state"));

            logger.debug("Accesstoken: " + tokenResult.getAccessToken());
            holder.setAccessToken(tokenResult.getAccessToken());
            request.getSession().setAttribute("accessToken", holder.getAccessToken());
        }

        if (request.getSession().getAttribute("accessToken") != null) {
            String achternaam = request.getParameter("achternaam");

            Client c = ClientBuilder.newClient().register(JacksonFeature.class);
            Properties props = new java.util.Properties();

            InputStream in = Employee.class.getResourceAsStream("/webapis.properties");
            props.load(in);

            String max = request.getParameter("max");
            String offset = request.getParameter("offset");
            WebTarget target = c.target(props.getProperty("employeeurl")).path("employees");

            WebTarget queryTarget = target.queryParam("lastname", achternaam).queryParam("max", max).queryParam("offset", offset);

            logger.debug("Deze url wordt aangeroepen: " + queryTarget.getUri().toASCIIString());

            Invocation.Builder invocationBuilder = queryTarget.request(MediaType.APPLICATION_JSON_TYPE);
            Response apiresponse = invocationBuilder.get();
            String result = apiresponse.readEntity(String.class);

            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(result);
            logger.debug("Result: " + result);
            //bepaal of er 1 object is gevonden of een array van objecten 
            //Result result = jsonObject.get("result");


//        if (jsonObject.get("results") instanceof JSONArray) {
            JSONArray arr = (JSONArray) jsonObject.get("results");

            if (jsonObject.get("next") != null && !jsonObject.get("next").equals("null")) {
                String next = (String) jsonObject.get("next");
                request.setAttribute("next", next);
            }
            logger.debug("Previous: " + jsonObject.get("previous"));
            if (jsonObject.get("previous") != null && !jsonObject.get("previous").equals("null")) {
                String previous = (String) jsonObject.get("previous");
                request.setAttribute("previous", previous);
            }

            request.setAttribute("persons", arr);


//        } else if (jsonObject.getJSONObject("results") instanceof JSONObject) {
//            JSONObject person = jsonObject.getJSONObject("employees");
//            request.setAttribute("person", person);
//        }
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("toonzoekresultaat.jsp").forward(request, response);
        }
        
        else {
            logger.debug("geen accestoken");
        }
    }
}
