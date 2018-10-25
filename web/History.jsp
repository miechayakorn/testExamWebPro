<%-- 
    Document   : History
    Created on : 25-Oct-2018, 08:00:16
    Author     : Acer_E5
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>History</h1>
        <table>
            <tr>
                <th>#</th>
                <th>TimeStamp</th>
                <th>Method</th>
                <th>Amount</th>
                <th>Balance</th>
            </tr>
            <c:forEach  items="${sessionScope.acc.historyList}" var="list" varStatus="vs">
                <tr>
                    <th>${vs.count}</th>
                    <th>${list.time}</th>
                    <th>${list.method}</th>
                    <th>${list.amount}</th>
                    <th>${list.balance}</th>
                </tr>
            </c:forEach>
        </table>
        <a href="MyAccount.jsp">Back</a>
    </body>
</html>
