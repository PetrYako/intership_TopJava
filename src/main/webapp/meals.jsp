<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<table border="1" bgcolor="#FFCC66" align="center" width="1100px">
    <caption>Meal</caption>
    <tr>
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo" />
        <tr bgcolor="${meal.excess ? "#CC6633" : "00FF66"}">
            <td><%=meal.getDateTime().format(format)%></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Edit</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}" />">Delete</a> </td>
        </tr>
    </c:forEach>
    <th><a href="meals?action=create">Create</a></th>
</table>
</body>
</html>
