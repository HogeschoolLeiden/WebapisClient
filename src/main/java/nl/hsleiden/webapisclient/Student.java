/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.webapisclient;


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
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author hl
 */
public class Student extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Student.class.getName());

        request.setCharacterEncoding("UTF-8");
        String achternaam = request.getParameter("achternaam");

        Client c = ClientBuilder.newClient().register(JacksonFeature.class);
        Properties props = new java.util.Properties();
        InputStream in = Student.class.getResourceAsStream("/webapis.properties");
        props.load(in);

        String max = request.getParameter("max");
        String offset = request.getParameter("offset");
        WebTarget target = c.target(props.getProperty("studenturl")).path("students");
        WebTarget queryTarget = target.queryParam("lastname", achternaam).queryParam("max", max).queryParam("offset", offset);
          
        logger.debug("Deze url wordt aangeroepen: " + queryTarget.getUri().toASCIIString());
        
        Invocation.Builder invocationBuilder = queryTarget.request(MediaType.APPLICATION_JSON_TYPE);
        Response apiresponse = invocationBuilder.get();
        String result =  apiresponse.readEntity(String.class);
        
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(result);
        logger.debug("Result: " + result);
        //bepaal of er 1 object is gevonden of een array van objecten 
        //Result result = jsonObject.get("result");
        JSONArray arr = (JSONArray) jsonObject.get("results");
            
            if (jsonObject.get("next") != null  && !jsonObject.get("next").equals("null")) {
                String next = (String) jsonObject.get("next");
                request.setAttribute("next", next);
            }
            logger.debug("Previous: " + jsonObject.get("previous"));
            if (jsonObject.get("previous") != null && !jsonObject.get("previous").equals("null")) {
                String previous = (String) jsonObject.get("previous");
                request.setAttribute("previous", previous);
            }
            
            request.setAttribute("persons", arr);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("toonzoekresultaat.jsp").forward(request, response);

    }
}
