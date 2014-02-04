<%-- 

Copyright 2014 Hogeschool Leiden.
 
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
    Document   : error
    Created on : 22-apr-2013, 15:25:27
    Author     : fadministrator
--%>

<%@ page isErrorPage = "true"%>
   
<html>
    <head>
        <link rel='stylesheet' href='style.css' type='text/css'>   
    </head>
    <body>
        <section class="formatHTML5">
        <h2>Your application has generated an error</h2>
        <h3>Please check for the error given below</h3>
        <b>Exception:</b><br> 
        <font color="red"><%= exception.toString()%></font>
        
        <br>
        
        <font color="red"><%= exception.getMessage()%></font>
        </section>
    </body>
</html>