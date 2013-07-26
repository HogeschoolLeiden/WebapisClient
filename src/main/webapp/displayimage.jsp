<%-- 
    Document   : displayimage
    Created on : Jul 26, 2013, 10:03:11 AM
    Author     : hl
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="net.sf.json.JSONObject" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='style.css' type='text/css'>
        <title>JSP Page</title>
    </head>
    <body>
        <header class="formatHTML5">Foto</header>
        <form method="post"  action="DisplayImage" class="formatHTML5">
            
            <h2>Zoek een foto: </h2>
            Voer een studentnummer in: <input type="text" name="studentnummer" placeholder="studentnummer"><br>
            <p><input type="submit" >
        </form>
        <c:if test="${not empty requestScope.image}">
        <section class="formatHTML5">
            <table class="formatHTML5">
                <thead>
                    <tr>
                        <td>Foto</td>
                    </tr>
                </thead>
                    <tr>
                        <td>
                            <c:out value='${requestScope.image.opt("studentnumber")}' />
                        </td>
                        <td>
                             
                            <img src="data:image/jpg;base64,${requestScope.image.opt("image")}"/>
                        </td>
                    </tr>
            </table>
        </section>
        </c:if>
    </body>
</html>
