<%-- 
    Document   : insertimage
    Created on : Jul 25, 2013, 12:57:55 PM
    Author     : hl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='style.css' type='text/css'>
    </head>
    <body>
        <form method="POST" action="InsertImage" enctype="multipart/form-data" class="formatHTML5">
            File:
            <input type="text" name="studentnummer" placeholder="studentnummer" id="studentnummer"><td>
            <input type="file" name="file" id="file" /> <br/>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
    </body>
</html>
