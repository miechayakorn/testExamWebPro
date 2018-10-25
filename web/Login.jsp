<%-- 
    Document   : Login
    Created on : 25-Oct-2018, 07:58:10
    Author     : Acer_E5
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1><hr>
        <p style="color: red">${message}</p><br><br>
        <form action="Login">
            ID: <input type="text" name="id" ><br><br>
            PIN: <input type="text" name="pin" ><br><br>
            <input type="submit">
        </form>
    </body>
</html>
