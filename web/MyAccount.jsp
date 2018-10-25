<%-- 
    Document   : MyAccount
    Created on : 25-Oct-2018, 09:01:05
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
        <h1>My Account</h1><hr>
        <p style="color: red">${message}</p>
        
        <h2>
            Name: ${sessionScope.acc.name}<br>
            Balance: ${sessionScope.acc.balance}
        </h2>
        <a href="Deposit">Deposit</a> <br><br>
        <a href="Withdraw">Withdraw</a> <br><br>
        <a href="History">History</a><br><br>
        <a href="Logout">Log out</a> <br><br>
    </body>
</html>
