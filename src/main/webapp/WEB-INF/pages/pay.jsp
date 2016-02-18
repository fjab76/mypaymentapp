<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Pay Some Person</title>
</head>
<body>
<h2>Pay</h2>
<div>${resultMessage}</div>
<br/>

<form:form method="POST" action="/pay" modelAttribute="payForm">
    <table>
        <tr>
            <td>From:</td>
            <td><form:select path="from" items="${accounts}" itemLabel="name" itemValue="name" multiple="false"/></td>
        </tr>
        <tr>
            <td>To:</td>
            <td><form:select path="to" items="${accounts}"  itemLabel="name" itemValue="name" multiple="false"/></td>
        </tr>
        <tr>
            <td>Amount:</td>
            <td><form:input path="amount"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Save Changes"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
