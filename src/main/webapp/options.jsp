<%-- 
    Document   : options.jsp
    Created on : 25-apr-2013, 10:03:14
    Author     : fadministrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <pre>
        <c:out value='${requestScope.result}' /> 
        </pre>
    </body>
</html>
