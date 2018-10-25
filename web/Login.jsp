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
        <h1>Login</h1>
        <p style="color: red">${message}</p>
        <form action="Login">
            id: <input type="text" name="id" >
            pin: <input type="text" name="pin" >
            <input type="submit">
        </form>
    </body>
</html>
