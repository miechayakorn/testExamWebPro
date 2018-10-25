<%-- 
    Document   : Deposit
    Created on : 25-Oct-2018, 07:59:20
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
        <h1>Deposit</h1>
        <p style="color: red">${message}</p>
        <h2>Balance: ${sessionScope.acc.balance}</h2>
        <br>
        <br>
        <form>
            Deposit: <input type="text" name="deposit" >
            <input type="submit">
        </form>
        <a href="MyAccount.jsp">Back</a>
    </body>
</html>
