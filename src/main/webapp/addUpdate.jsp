<%--
  Created by IntelliJ IDEA.
  User: peotr
  Date: 10.10.2019
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add or Update</title>
</head>
<body>
    <form method="post">
        <p>Date <input type="datetime-local" name="date" min="1987-01-01T00:00" max="2019-10-10T00:00" value="${date}"></p>
        <p>Description <input type="text" name="description" value="${description}"></p>
        <p>Calories <input type="number" name="calories" value="${calories}"></p>
        <p><input type="submit" value="submit"></p>
    </form>
</body>
</html>
