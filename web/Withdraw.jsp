<%-- 
    Document   : Withdraw
    Created on : 25-Oct-2018, 07:59:37
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
        <h1>Withdraw</h1>
        <p style="color: red">${message}</p>
        <h2>Balance: ${sessionScope.acc.balance}</h2>
        <br>
        <br>
        
        <form>
            Withdraw <input type="text" name="withdraw" >
            <input type="submit">
        </form><br><br>
        <a href="MyAccount.jsp">Back</a>
    </body>
</html>
