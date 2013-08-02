<%-- 
    Document   : toonResultaat
    Created on : 22-apr-2013, 9:11:27
    Author     : fadministrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <header class="formatHTML5">Gevonden</header>
 
        <section class="formatHTML5">
            <table class="formatHTML5">
                <thead>
                    <tr>
                        <td>Naam</td>
                    </tr>
                </thead>
                <c:if test="${not empty requestScope.person}">
                   
                    <tr>
                        <td>
                            <a href=" <c:out value='${requestScope.person.opt("uri")}' />"> 
                                <c:out value='${requestScope.person.opt("listname")}' />
                            </a>
                            
                        </td>
                    </tr>

                </c:if>
                <c:if test="${not empty requestScope.persons}">
                    <c:forEach items="${persons}" var="person">
                        <tr>
                        
                        <td>
                            <a href=" <c:out value='${person.opt("uri")}' />"> 
                            <c:out value='${person.opt("listname")}' />
                            </a>
                        </td>
                        
                    </tr>
                    </c:forEach>
                    <c:if test="${not empty requestScope.next}">
                        <a href=" <c:out value='${next}'/>">
                            Next
                        </a>
                    </c:if>
                    <c:if test="${not empty requestScope.previous}">
                        <a href=" <c:out value='${requestScope.previous}' />">
                            Previous
                        </a>
                    </c:if>   
                </c:if>
            </table>
        </section>
    </body>
</html>
