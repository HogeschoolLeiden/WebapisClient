/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.webapisclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;

/**
 *
 * @author hl
 */
public class Student extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InsertImage.class.getName());

        request.setCharacterEncoding("UTF-8");
        String achternaam = request.getParameter("achternaam");
        if (achternaam == null || achternaam.trim().isEmpty()) {
            throw new ServletException("Geen parameter ingevoerd");
        }

        Client c = new Client();
        Properties props = new java.util.Properties();
        InputStream in = Student.class.getResourceAsStream("/webapis.properties");
        props.load(in);

        WebResource resource = c.resource(props.getProperty("studenturl") + "/" + achternaam);
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("max", "2");
        params.add("offset", "0");

        resource.accept("application/json");
        String result = resource.queryParams(params).get(String.class);
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(result);

        //bepaal of er 1 object is gevonden of een array van objecten 
        //Result result = jsonObject.get("result");
        if (jsonObject.get("students") instanceof JSONArray) {
            JSONArray arr = (JSONArray) jsonObject.get("students");
            String next = (String) jsonObject.get("next");
            String previous = (String) jsonObject.get("previous");
            request.setAttribute("persons", arr);
            request.setAttribute("next", next);
            request.setAttribute("previous", previous);
        } else if (jsonObject.getJSONObject("students") instanceof JSONObject) {
            JSONObject person = jsonObject.getJSONObject("students");
            request.setAttribute("person", person);
        }
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("toonzoekresultaat.jsp").forward(request, response);

    }
}
