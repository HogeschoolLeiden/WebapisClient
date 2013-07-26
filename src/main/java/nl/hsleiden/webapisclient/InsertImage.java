/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.webapisclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author hl
 */
public class InsertImage extends HttpServlet {

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
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InsertImage.class.getName());

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            int DEFAULT_BUFFER_SIZE = 0;
            // Parse the request
            List<FileItem> items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException f) {
                Logger.getLogger(InsertImage.class.getName()).log(Level.SEVERE, null, f);
            }

            DataSource ds = null;
            try {
                InitialContext initialContext = new InitialContext();
                ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/apis"); // thats everything from the context.xml and from the global configuration
            } catch (NamingException n) {
                Logger.getLogger(InsertImage.class.getName()).log(Level.SEVERE, null, n);
            }

            String studentnummer = "";
            Connection conn = null;
            PreparedStatement stmt;
            try {
                conn = ds.getConnection();
                stmt = conn.prepareStatement("Insert into images values(?,?)");

                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();

                    if (item.isFormField() && item.getFieldName().equals("studentnummer")) {
                        logger.debug("ingevoerd studentnummer: " + item.getString());
                        studentnummer = item.getString();
                        stmt.setString(1, item.getString());
                    } else if (item != null && item.getFieldName().equals("file")) {
                        stmt.setBinaryStream(2, item.getInputStream(), item.getSize());
                        DEFAULT_BUFFER_SIZE = (int) item.getSize();
                    }
                }
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(InsertImage.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InsertEmployee.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            PreparedStatement stmt2;
            ResultSet rs = null;
            try {
                conn = ds.getConnection();
                stmt2 = conn.prepareStatement("Select image from images where studentnumber = '" + studentnummer + "'");

                stmt2.executeQuery();
                rs = stmt2.getResultSet();

                response.setContentType("image/jpg");
                ServletOutputStream out = response.getOutputStream();
                InputStream imageStream = null; 
                if (rs.next()) {
                    imageStream = rs.getBinaryStream("image");
                }

                BufferedInputStream input = null;
                BufferedOutputStream output = null;

                try {
                    input = new BufferedInputStream(imageStream, DEFAULT_BUFFER_SIZE);
                    output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
                    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                    int length;
                    while ((length = input.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }

                } finally {
                    output.close();
                    input.close();
                }

            } catch (Exception e) {
                Logger.getLogger(InsertImage.class.getName()).log(Level.SEVERE, null, e);
            }
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
    }
}
