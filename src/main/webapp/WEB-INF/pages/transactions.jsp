<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>transaction</h1>

    <div>
        <h2>Accounts List</h2>

        <form:form method="POST" action="/accountTransactions" modelAttribute="accountForm">
            <td><form:radiobuttons path="name" items="${accounts}" itemLabel="name" itemValue="name"/></td>
            <input type="submit" value="Show transactions"/>
        </form:form>
    </div>
    <div>
        <h2>Transaction details</h2>

        <table>
            <tr><th>From</th><th>To</th><th>Amount</th>
                <c:forEach var="transaction" items="${transactions}">
                    <tr><td>${transaction.from.name}</td><td>${transaction.to.name}</td><td>${transaction.amount}</td></tr>
                </c:forEach>
        </table>
    </div>

</body>
</html>
